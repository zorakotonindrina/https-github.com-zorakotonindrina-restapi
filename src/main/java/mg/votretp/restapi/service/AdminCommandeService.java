package mg.votretp.restapi.service;

import mg.votretp.restapi.dto.CommandeAdminDTO;
import mg.votretp.restapi.dto.CommandeAdminLigneDTO;
import mg.votretp.restapi.model.Commande;
import mg.votretp.restapi.model.ListeCommande;
import mg.votretp.restapi.model.PrixPlat;
import mg.votretp.restapi.model.Recu;
import mg.votretp.restapi.repository.CommandeRepository;
import mg.votretp.restapi.repository.ListeCommandeRepository;
import mg.votretp.restapi.repository.PrixPlatRepository;
import mg.votretp.restapi.repository.RecuRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminCommandeService {

    private final CommandeRepository commandeRepository;
    private final ListeCommandeRepository listeCommandeRepository;
    private final RecuRepository recuRepository;
    private final PrixPlatRepository prixPlatRepository;

    public AdminCommandeService(CommandeRepository commandeRepository,
                                ListeCommandeRepository listeCommandeRepository,
                                RecuRepository recuRepository,
                                PrixPlatRepository prixPlatRepository) {
        this.commandeRepository = commandeRepository;
        this.listeCommandeRepository = listeCommandeRepository;
        this.recuRepository = recuRepository;
        this.prixPlatRepository = prixPlatRepository;
    }

    public List<CommandeAdminDTO> getCommandesAValider() {

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
}