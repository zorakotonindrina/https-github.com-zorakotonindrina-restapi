package mg.votretp.restapi.service;

import mg.votretp.restapi.dto.*;
import mg.votretp.restapi.model.*;
import mg.votretp.restapi.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;



@Service
public class PanierService {

    private final CommandeRepository commandeRepository;
    private final ListeCommandeRepository listeCommandeRepository;
    private final PlatRepository platRepository;

    private final PrixPlatRepository prixPlatRepository;

    private final ModePaiementRepository modePaiementRepository;

    private final MailValidationCommandeRepository mailValidationCommandeRepository;
    private final MailService mailService;

    private final RecuRepository recuRepository;
    private final CommandeConfirmerRepository commandeConfirmerRepository;
    private final UserRepository userRepository;

    public PanierService(CommandeRepository commandeRepository,
                         ListeCommandeRepository listeCommandeRepository,
                         PlatRepository platRepository,
                         PrixPlatRepository prixPlatRepository,
                         ModePaiementRepository modePaiementRepository,
                         MailValidationCommandeRepository mailValidationCommandeRepository,
                         MailService mailService,
                         RecuRepository recuRepository,
                         CommandeConfirmerRepository commandeConfirmerRepository,
                         UserRepository userRepository) {
        this.commandeRepository = commandeRepository;
        this.listeCommandeRepository = listeCommandeRepository;
        this.platRepository = platRepository;
        this.prixPlatRepository = prixPlatRepository;
        this.modePaiementRepository = modePaiementRepository;
        this.mailValidationCommandeRepository = mailValidationCommandeRepository;
        this.mailService = mailService;
        this.recuRepository = recuRepository;
        this.commandeConfirmerRepository = commandeConfirmerRepository;
        this.userRepository = userRepository;
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


    public CommandeValideeResponseDTO validerCommande(ValiderCommandeDTO dto) {
        if (dto.getEmailClient() == null || dto.getEmailClient().isBlank()) {
            throw new RuntimeException("Email client obligatoire");
        }

        if (dto.getIdModePaiement() == null) {
            throw new RuntimeException("Mode de paiement obligatoire");
        }

        Commande panier = commandeRepository.findByEmailClientAndStatus(dto.getEmailClient(), "PANIER")
                .orElseThrow(() -> new RuntimeException("Panier introuvable"));

        if (dto.getNumClient() != null && !dto.getNumClient().isBlank()) {
            panier.setNumClient(dto.getNumClient());
        }

        if (listeCommandeRepository.findByCommande_IdCommande(panier.getIdCommande()).isEmpty()) {
            throw new RuntimeException("Impossible de valider une commande vide");
        }

        ModePaiement modePaiement = modePaiementRepository.findById(dto.getIdModePaiement())
                .orElseThrow(() -> new RuntimeException("Mode de paiement introuvable"));

        int numeroCommande = genererNumeroCommandeDuJour();
        String code = String.valueOf(java.util.concurrent.ThreadLocalRandom.current().nextInt(100000, 999999));

        panier.setNumCommande(numeroCommande);
        panier.setModePaiement(modePaiement);
        panier.setStatus("EN_VALIDATION_EMAIL");

        commandeRepository.save(panier);

        MailValidationCommande validation = new MailValidationCommande();
        validation.setEmailClient(dto.getEmailClient());
        validation.setCode(code);
        validation.setExpireAt(java.time.LocalDateTime.now().plusMinutes(10));
        validation.setCommande(panier);

        mailValidationCommandeRepository.save(validation);

        return new CommandeValideeResponseDTO(
                panier.getIdCommande(),
                panier.getNumCommande(),
                panier.getStatus(),
                panier.getEmailClient(),
                panier.getNumClient(),
                panier.getModePaiement().getNom(),
                "Code de validation généré avec succès",
                code
        );
    }


    public ValidationMailResponseDTO confirmerEmail(ConfirmerEmailDTO dto) {
        if (dto.getEmailClient() == null || dto.getEmailClient().isBlank()) {
            throw new RuntimeException("Email client obligatoire");
        }

        if (dto.getCode() == null || dto.getCode().isBlank()) {
            throw new RuntimeException("Code obligatoire");
        }

        MailValidationCommande validation = mailValidationCommandeRepository
                .findTopByEmailClientAndCodeOrderByDateCreationDesc(dto.getEmailClient(), dto.getCode())
                .orElseThrow(() -> new RuntimeException("Code invalide"));

        if (validation.getExpireAt() != null && validation.getExpireAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Code expiré");
        }

        Commande commande = validation.getCommande();
        commande.setStatus("EN_ATTENTE");
        commandeRepository.save(commande);

        return new ValidationMailResponseDTO(
                commande.getIdCommande(),
                commande.getStatus(),
                commande.getEmailClient(),
                "Commande confirmée avec succès"
        );
    }

    public RecuResponseDTO soumettrePaiement(SoumettrePaiementDTO dto) {
        if (dto.getEmailClient() == null || dto.getEmailClient().isBlank()) {
            throw new RuntimeException("Email client obligatoire");
        }

        if (dto.getRefPaiement() == null || dto.getRefPaiement().isBlank()) {
            throw new RuntimeException("Référence de paiement obligatoire");
        }

        Commande commande = commandeRepository.findByEmailClientAndStatus(dto.getEmailClient(), "EN_ATTENTE")
                .orElseThrow(() -> new RuntimeException("Commande en attente introuvable"));

        if (recuRepository.findByCommande_IdCommande(commande.getIdCommande()).isPresent()) {
            throw new RuntimeException("Un reçu existe déjà pour cette commande");
        }

        var lignes = listeCommandeRepository.findByCommande_IdCommande(commande.getIdCommande());

        if (lignes.isEmpty()) {
            throw new RuntimeException("Commande vide");
        }

        BigDecimal totalGeneral = lignes.stream().map(ligne -> {
            BigDecimal prixUnitaire = prixPlatRepository
                    .findTopByPlat_IdPlatOrderByDatePrixDesc(ligne.getPlat().getIdPlat())
                    .map(prix -> prix.getPrix())
                    .orElse(BigDecimal.ZERO);

            return prixUnitaire.multiply(BigDecimal.valueOf(ligne.getQuantite()));
        }).reduce(BigDecimal.ZERO, BigDecimal::add);

        Recu recu = new Recu();
        recu.setCommande(commande);
        recu.setRefPayament(dto.getRefPaiement());
        recu.setPrixTotal(totalGeneral);

        recuRepository.save(recu);

        commande.setStatus("A_VALIDER_RESTAURANT");
        commandeRepository.save(commande);

        return new RecuResponseDTO(
                commande.getIdCommande(),
                commande.getNumCommande(),
                commande.getEmailClient(),
                recu.getRefPayament(),
                recu.getPrixTotal(),
                commande.getStatus(),
                "Paiement soumis avec succès, en attente de validation du restaurant"
        );
    }


    public CommandeConfirmeeResponseDTO validerRestaurant(ValiderRestaurantDTO dto, String numeroUtilisateur) {
        if (dto.getIdCommande() == null) {
            throw new RuntimeException("Id commande obligatoire");
        }

        if (numeroUtilisateur == null || numeroUtilisateur.isBlank()) {
            throw new RuntimeException("Utilisateur non authentifié");
        }

        User user = userRepository.findByNumero(numeroUtilisateur)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        Commande commande = commandeRepository.findById(dto.getIdCommande())
                .orElseThrow(() -> new RuntimeException("Commande introuvable"));

        if (!"A_VALIDER_RESTAURANT".equals(commande.getStatus())) {
            throw new RuntimeException("Cette commande n'est pas en attente de validation restaurant");
        }

        if (commandeConfirmerRepository.findByCommande_IdCommande(commande.getIdCommande()).isPresent()) {
            throw new RuntimeException("Cette commande a déjà été confirmée");
        }

        commande.setStatus("CONFIRMEE");
        commandeRepository.save(commande);

        CommandeConfirmer confirmation = new CommandeConfirmer();
        confirmation.setCommande(commande);
        confirmation.setUser(user);
        commandeConfirmerRepository.save(confirmation);

        String confirmePar = user.getNom() + " " + user.getPrenom();

        return new CommandeConfirmeeResponseDTO(
                commande.getIdCommande(),
                commande.getNumCommande(),
                commande.getStatus(),
                commande.getEmailClient(),
                commande.getNumClient(),
                confirmePar,
                "Commande confirmée par le restaurant"
        );
    }

    private Integer genererNumeroCommandeDuJour() {
        java.time.LocalDate today = java.time.LocalDate.now();
        java.time.LocalDateTime debutJour = today.atStartOfDay();
        java.time.LocalDateTime finJour = today.plusDays(1).atStartOfDay();

        Integer maxNum = commandeRepository.findMaxNumCommandeByDay(debutJour, finJour);

        return maxNum + 1;
    }
}