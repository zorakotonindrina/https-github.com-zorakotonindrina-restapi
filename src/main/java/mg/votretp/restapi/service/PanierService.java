package mg.votretp.restapi.service;

import mg.votretp.restapi.dto.*;
import mg.votretp.restapi.model.Commande;
import mg.votretp.restapi.model.ListeCommande;
import mg.votretp.restapi.model.Plat;
import mg.votretp.restapi.repository.CommandeRepository;
import mg.votretp.restapi.repository.ListeCommandeRepository;
import mg.votretp.restapi.repository.PlatRepository;
import mg.votretp.restapi.repository.PrixPlatRepository;
import org.springframework.stereotype.Service;

import mg.votretp.restapi.model.PrixPlat;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PanierService {

    private final CommandeRepository commandeRepository;
    private final ListeCommandeRepository listeCommandeRepository;
    private final PlatRepository platRepository;

    private final PrixPlatRepository prixPlatRepository;

    public PanierService(CommandeRepository commandeRepository,
                         ListeCommandeRepository listeCommandeRepository,
                         PlatRepository platRepository,
                         PrixPlatRepository prixPlatRepository) {
        this.commandeRepository = commandeRepository;
        this.listeCommandeRepository = listeCommandeRepository;
        this.platRepository = platRepository;
        this.prixPlatRepository = prixPlatRepository;
    }

    public String ajouterAuPanier(AddToCartDTO dto) {
        if (dto.getEmailClient() == null || dto.getEmailClient().isBlank()) {
            throw new RuntimeException("Email client obligatoire");
        }

        if (dto.getQuantite() == null || dto.getQuantite() <= 0) {
            throw new RuntimeException("La quantité doit être supérieure à 0");
        }

        Plat plat = platRepository.findById(dto.getIdPlat())
                .orElseThrow(() -> new RuntimeException("Plat introuvable"));

        if (Boolean.FALSE.equals(plat.getDispo())) {
            throw new RuntimeException("Ce plat n'est pas disponible");
        }

        Commande panier = commandeRepository.findByEmailClientAndStatus(dto.getEmailClient(), "PANIER")
                .orElseGet(() -> {
                    Commande nouvelleCommande = new Commande();
                    nouvelleCommande.setEmailClient(dto.getEmailClient());
                    nouvelleCommande.setNumClient(dto.getNumClient());
                    nouvelleCommande.setStatus("PANIER");
                    return commandeRepository.save(nouvelleCommande);
                });

        ListeCommande ligne = listeCommandeRepository
                .findByCommande_IdCommandeAndPlat_IdPlat(panier.getIdCommande(), plat.getIdPlat())
                .orElse(null);

        if (ligne != null) {
            ligne.setQuantite(ligne.getQuantite() + dto.getQuantite());
            listeCommandeRepository.save(ligne);
        } else {
            ListeCommande nouvelleLigne = new ListeCommande();
            nouvelleLigne.setCommande(panier);
            nouvelleLigne.setPlat(plat);
            nouvelleLigne.setQuantite(dto.getQuantite());
            listeCommandeRepository.save(nouvelleLigne);
        }

        return "Plat ajouté au panier avec succès";
    }

    public String modifierQuantite(UpdateCartQuantityDTO dto) {
        if (dto.getEmailClient() == null || dto.getEmailClient().isBlank()) {
            throw new RuntimeException("Email client obligatoire");
        }

        if (dto.getQuantite() == null || dto.getQuantite() <= 0) {
            throw new RuntimeException("La quantité doit être supérieure à 0");
        }

        Commande panier = commandeRepository.findByEmailClientAndStatus(dto.getEmailClient(), "PANIER")
                .orElseThrow(() -> new RuntimeException("Panier introuvable"));

        ListeCommande ligne = listeCommandeRepository
                .findByCommande_IdCommandeAndPlat_IdPlat(panier.getIdCommande(), dto.getIdPlat())
                .orElseThrow(() -> new RuntimeException("Plat introuvable dans le panier"));

        ligne.setQuantite(dto.getQuantite());
        listeCommandeRepository.save(ligne);

        return "Quantité modifiée avec succès";
    }


    public PanierResponseDTO afficherPanier(String emailClient) {
        if (emailClient == null || emailClient.isBlank()) {
            throw new RuntimeException("Email client obligatoire");
        }

        Commande panier = commandeRepository.findByEmailClientAndStatus(emailClient, "PANIER")
                .orElseThrow(() -> new RuntimeException("Panier introuvable"));

        List<ListeCommande> lignesCommande = listeCommandeRepository.findByCommande_IdCommande(panier.getIdCommande());

        List<PanierLigneDTO> lignes = lignesCommande.stream().map(ligne -> {
            BigDecimal prixUnitaire = prixPlatRepository
                    .findTopByPlat_IdPlatOrderByDatePrixDesc(ligne.getPlat().getIdPlat())
                    .map(PrixPlat::getPrix)
                    .orElse(BigDecimal.ZERO);

            BigDecimal sousTotal = prixUnitaire.multiply(BigDecimal.valueOf(ligne.getQuantite()));

            return new PanierLigneDTO(
                    ligne.getPlat().getIdPlat(),
                    ligne.getPlat().getNom(),
                    ligne.getPlat().getImage(),
                    ligne.getQuantite(),
                    prixUnitaire,
                    sousTotal
            );
        }).toList();

        BigDecimal totalGeneral = lignes.stream()
                .map(PanierLigneDTO::getSousTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new PanierResponseDTO(
                panier.getIdCommande(),
                panier.getEmailClient(),
                panier.getNumClient(),
                panier.getStatus(),
                lignes,
                totalGeneral
        );
    }

    public String supprimerDuPanier(RemoveFromCartDTO dto) {
        if (dto.getEmailClient() == null || dto.getEmailClient().isBlank()) {
            throw new RuntimeException("Email client obligatoire");
        }

        Commande panier = commandeRepository.findByEmailClientAndStatus(dto.getEmailClient(), "PANIER")
                .orElseThrow(() -> new RuntimeException("Panier introuvable"));

        ListeCommande ligne = listeCommandeRepository
                .findByCommande_IdCommandeAndPlat_IdPlat(panier.getIdCommande(), dto.getIdPlat())
                .orElseThrow(() -> new RuntimeException("Plat introuvable dans le panier"));

        listeCommandeRepository.delete(ligne);

        if (listeCommandeRepository.findByCommande_IdCommande(panier.getIdCommande()).isEmpty()) {
            commandeRepository.delete(panier);
        }

        return "Plat supprimé du panier avec succès";
    }
}