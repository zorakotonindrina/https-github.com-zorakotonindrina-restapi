package mg.votretp.restapi.service;

import jakarta.transaction.Transactional;
import mg.votretp.restapi.dto.CommandeAdminDTO;
import mg.votretp.restapi.dto.CommandeAdminLigneDTO;
import mg.votretp.restapi.model.Commande;
import mg.votretp.restapi.model.ListeCommande;
import mg.votretp.restapi.model.PrixPlat;
import mg.votretp.restapi.model.Recu;
import mg.votretp.restapi.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AdminCommandeService {

    private final CommandeRepository commandeRepository;
    private final ListeCommandeRepository listeCommandeRepository;
    private final RecuRepository recuRepository;
    private final PrixPlatRepository prixPlatRepository;

    private final MailValidationCommandeRepository mailValidationCommandeRepository;

    public AdminCommandeService(CommandeRepository commandeRepository,
                                ListeCommandeRepository listeCommandeRepository,
                                RecuRepository recuRepository,
                                PrixPlatRepository prixPlatRepository,
                                MailValidationCommandeRepository mailValidationCommandeRepository) {
        this.commandeRepository = commandeRepository;
        this.listeCommandeRepository = listeCommandeRepository;
        this.recuRepository = recuRepository;
        this.prixPlatRepository = prixPlatRepository;
        this.mailValidationCommandeRepository = mailValidationCommandeRepository;
    }

    public List<CommandeAdminDTO> getCommandesAValider() {

        expirerCommandesNonValidees();

        //nettoyerCommandesExpirees();
        System.out.println("Commandes expirées supprimées ");

        List<Commande> commandes = commandeRepository
                .findByStatusOrderByDateCommandeAsc("A_VALIDER_RESTAURANT");

        return commandes.stream().map(this::mapToDTO).toList();
    }

    private CommandeAdminDTO mapToDTO(Commande commande) {

        List<ListeCommande> lignes = listeCommandeRepository
                .findByCommande_IdCommande(commande.getIdCommande());

        List<CommandeAdminLigneDTO> lignesDTO = lignes.stream().map(ligne -> {

            Optional<PrixPlat> dernierPrix = prixPlatRepository
                    .findTopByPlat_IdPlatOrderByDatePrixDesc(ligne.getPlat().getIdPlat());

            double prix = dernierPrix
                    .map(p -> p.getPrix().doubleValue())
                    .orElse(0.0);

            return new CommandeAdminLigneDTO(
                    ligne.getPlat().getNom(),
                    ligne.getQuantite(),
                    prix
            );

        }).toList();

        Optional<Recu> recu = recuRepository.findByCommande_IdCommande(commande.getIdCommande());
        String refPaiement = recu
                .map(r -> r.getRefPayament())
                .orElse(null);

        double total = lignesDTO.stream()
                .mapToDouble(l -> l.getQuantite() * l.getPrixUnitaire())
                .sum();

        return new CommandeAdminDTO(
                commande.getIdCommande(),
                commande.getNumCommande(),
                commande.getEmailClient(),
                commande.getNumClient(),
                commande.getStatus(),
                commande.getDateCommande(),
                refPaiement,
                total,
                lignesDTO
        );
    }



    private void expirerCommandesNonValidees() {
        List<Commande> commandes = commandeRepository.findByStatusIn(
                java.util.List.of("EN_VALIDATION_EMAIL", "EN_ATTENTE")
        );

        java.time.LocalDateTime maintenant = java.time.LocalDateTime.now();

        for (Commande commande : commandes) {
            if (commande.getDateCommande() != null &&
                    commande.getDateCommande().plusMinutes(30).isBefore(maintenant)) {
                commande.setStatus("EXPIREE");
                commandeRepository.save(commande);
            }
        }
    }




}