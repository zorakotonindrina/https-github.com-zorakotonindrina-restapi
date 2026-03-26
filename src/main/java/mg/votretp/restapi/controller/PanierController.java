package mg.votretp.restapi.controller;


import mg.votretp.restapi.dto.*;
import mg.votretp.restapi.repository.PrixPlatRepository;
import mg.votretp.restapi.service.PanierService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Map;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/panier")
@Tag(name = "Panier & Commande", description = "Gestion du panier, validation, paiement et confirmation")
public class PanierController {


    private final PanierService panierService;

    public PanierController(PanierService panierService) {
        this.panierService = panierService;
    }

    @Operation(summary = "Passer Commande")
    @PostMapping("/client/commander")
    public PasserCommandeResponseDTO passerCommande(@RequestBody PasserCommandeDTO dto) {
        PasserCommandeResponseDTO response = panierService.passerCommande(dto);

        response.add(linkTo(methodOn(PanierController.class)
                .passerCommande(dto)).withSelfRel());

        response.add(linkTo(methodOn(PanierController.class)
                .confirmerEmail(null)).withRel("confirmer-email"));

        response.add(linkTo(methodOn(PanierController.class)
                .soumettrePaiement(null)).withRel("soumettre-paiement"));
        return response;
    }



    @Operation(summary = "Afficher le panier courant")
    @GetMapping("/client")
    public PanierResponseDTO afficherPanier(@RequestParam String emailClient) {
        return panierService.afficherPanier(emailClient);
    }

    @Operation(summary = "Valider la commande et envouyer un code email")
    @PostMapping("/client/valider")
    public CommandeValideeResponseDTO validerCommande(@RequestBody ValiderCommandeDTO dto) {
        CommandeValideeResponseDTO response = panierService.validerCommande(dto);

        response.add(linkTo(methodOn(PanierController.class)
                .validerCommande(dto)).withSelfRel());

        response.add(linkTo(methodOn(PanierController.class)
                .afficherPanier(dto.getEmailClient())).withRel("voir-panier"));

        response.add(linkTo(methodOn(PanierController.class)
                .confirmerEmail(null)).withRel("confirmer-email"));

        response.add(linkTo(methodOn(PanierController.class)
                .soumettrePaiement(null)).withRel("soumettre-paiement"));

        return response;
    }

    @Operation(summary = "Confirmer la commande via code email")
    @PostMapping("/client/confirmer-email")
    public ValidationMailResponseDTO confirmerEmail(@RequestBody ConfirmerEmailDTO dto) {
        ValidationMailResponseDTO response = panierService.confirmerEmail(dto);

        response.add(linkTo(methodOn(PanierController.class)
                .confirmerEmail(dto)).withSelfRel());

        response.add(linkTo(methodOn(PanierController.class)
                .soumettrePaiement(null)).withRel("soumettre-paiement"));


        return response;
    }

    @PostMapping("/client/renvoyer-code")
    public RenvoyerCodeResponseDTO renvoyerCode(@RequestBody RenvoyerCodeDTO dto) {
        return panierService.renvoyerCode(dto);
    }

    @Operation(summary = "Soumettre la reference de paiement")
    @PostMapping("/client/soumettre-paiement")
    public RecuResponseDTO soumettrePaiement(@RequestBody SoumettrePaiementDTO dto) {
        RecuResponseDTO response = panierService.soumettrePaiement(dto);

        response.add(linkTo(methodOn(PanierController.class)
                .soumettrePaiement(dto)).withSelfRel());


        return response;
    }

    @Operation(summary = "Valider la commande cote restaurant")
    @PreAuthorize("hasAnyRole('ADMIN','RESTAURANT_OWNER')")
    @PostMapping("/valider-restaurant")
    public CommandeConfirmeeResponseDTO validerRestaurant(@RequestBody ValiderRestaurantDTO dto,
                                                          Authentication authentication) {
        String numeroUtilisateur = authentication.getName();
        CommandeConfirmeeResponseDTO response = panierService.validerRestaurant(dto, numeroUtilisateur);

        response.add(linkTo(methodOn(PanierController.class)
                .validerRestaurant(dto, authentication)).withSelfRel());

        response.add(linkTo(methodOn(StatistiqueController.class)
                .getTopPlats()).withRel("top-plats"));

        response.add(linkTo(methodOn(StatistiqueController.class)
                .getTotalParModePaiement()).withRel("stats-mode-paiement"));

        return response;
    }

    @PostMapping("/annuler")
    public AnnulerCommandeResponseDTO annulerCommande(@RequestBody AnnulerCommandeDTO dto) {
        return panierService.annulerCommande(dto);
    }

    @GetMapping("/detail/{numCommande}")
    public CommandeClientDetailDTO getDetailCommande(@PathVariable Integer numCommande) {
        return panierService.getDetailCommande(numCommande);
    }
}