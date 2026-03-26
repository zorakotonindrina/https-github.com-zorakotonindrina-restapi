package mg.votretp.restapi.service;

import mg.votretp.restapi.dto.CommandeCuisineDTO;
import mg.votretp.restapi.dto.CommandeCuisineLigneDTO;
import mg.votretp.restapi.dto.CommandeJourStatDTO;
import mg.votretp.restapi.dto.UpdateCommandeCuisineStatusDTO;
import mg.votretp.restapi.model.Commande;
import mg.votretp.restapi.model.ListeCommande;
import mg.votretp.restapi.repository.CommandeRepository;
import mg.votretp.restapi.repository.ListeCommandeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CuisineService {

    private final CommandeRepository commandeRepository;
    private final ListeCommandeRepository listeCommandeRepository;

    public CuisineService(CommandeRepository commandeRepository,
                          ListeCommandeRepository listeCommandeRepository) {
        this.commandeRepository = commandeRepository;
        this.listeCommandeRepository = listeCommandeRepository;
    }

    public List<CommandeCuisineDTO> getCommandesCuisine() {
        List<Commande> commandes = commandeRepository.findByStatusInOrderByDateCommandeAsc(
                List.of("CONFIRMEE", "EN_PREPARATION")
        );

        return commandes.stream().map(this::mapToDto).toList();
    }

    public CommandeCuisineDTO getCommandeCuisineById(Long idCommande) {
        Commande commande = commandeRepository.findById(idCommande)
                .orElseThrow(() -> new RuntimeException("Commande introuvable"));

        return mapToDto(commande);
    }

    public String updateStatusCuisine(UpdateCommandeCuisineStatusDTO dto) {
        if (dto.getIdCommande() == null) {
            throw new RuntimeException("Id commande obligatoire");
        }

        if (dto.getStatus() == null || dto.getStatus().isBlank()) {
            throw new RuntimeException("Status obligatoire");
        }

        Commande commande = commandeRepository.findById(dto.getIdCommande())
                .orElseThrow(() -> new RuntimeException("Commande introuvable"));

        String nouveauStatus = dto.getStatus().trim().toUpperCase();

        boolean transitionValide =
                ("CONFIRMEE".equals(commande.getStatus()) && "EN_PREPARATION".equals(nouveauStatus))
                        || ("EN_PREPARATION".equals(commande.getStatus()) && "FINIE".equals(nouveauStatus));

        if (!transitionValide) {
            throw new RuntimeException("Transition de statut invalide");
        }

        commande.setStatus(nouveauStatus);
        commandeRepository.save(commande);

        return "Statut cuisine mis à jour avec succès";
    }

    private CommandeCuisineDTO mapToDto(Commande commande) {
        List<ListeCommande> lignes = listeCommandeRepository.findByCommande_IdCommande(commande.getIdCommande());

        List<CommandeCuisineLigneDTO> lignesDto = lignes.stream()
                .map(ligne -> new CommandeCuisineLigneDTO(
                        ligne.getPlat().getIdPlat(),
                        ligne.getPlat().getNom(),
                        ligne.getQuantite()
                ))
                .toList();

        return new CommandeCuisineDTO(
                commande.getIdCommande(),
                commande.getNumCommande(),
                commande.getEmailClient(),
                commande.getNumClient(),
                commande.getStatus(),
                commande.getDateCommande(),
                lignesDto
        );
    }

    public CommandeJourStatDTO getStatutsCommandesDuJour() {
        LocalDate today = LocalDate.now();
        LocalDateTime debutJour = today.atStartOfDay();
        LocalDateTime finJour = today.plusDays(1).atStartOfDay();

        long enAttente = commandeRepository.countByStatusAndDateCommandeBetween("CONFIRMEE", debutJour, finJour);
        long enPreparation = commandeRepository.countByStatusAndDateCommandeBetween("EN_PREPARATION", debutJour, finJour);
        long finie = commandeRepository.countByStatusAndDateCommandeBetween("FINIE", debutJour, finJour);

        return new CommandeJourStatDTO(enAttente, enPreparation, finie);
    }
}