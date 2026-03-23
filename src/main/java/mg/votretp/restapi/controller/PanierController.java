package mg.votretp.restapi.controller;


import mg.votretp.restapi.dto.AddToCartDTO;
import mg.votretp.restapi.dto.PanierResponseDTO;
import mg.votretp.restapi.dto.RemoveFromCartDTO;
import mg.votretp.restapi.service.PanierService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import mg.votretp.restapi.dto.UpdateCartQuantityDTO;

@RestController
@RequestMapping("/api/panier")
public class PanierController {

    private final PanierService panierService;

    public PanierController(PanierService panierService) {
        this.panierService = panierService;
    }

    @PostMapping("/ajouter")
    public Map<String, String> ajouterAuPanier(@RequestBody AddToCartDTO dto) {
        String message = panierService.ajouterAuPanier(dto);
        return Map.of("message", message);
    }


    @PutMapping("/modifier-quantite")
    public Map<String, String> modifierQuantite(@RequestBody UpdateCartQuantityDTO dto) {
        String message = panierService.modifierQuantite(dto);
        return Map.of("message", message);
    }

    @GetMapping
    public PanierResponseDTO afficherPanier(@RequestParam String emailClient) {
        return panierService.afficherPanier(emailClient);
    }

    @DeleteMapping("/supprimer")
    public Map<String, String> supprimerDuPanier(@RequestBody RemoveFromCartDTO dto) {
        String message = panierService.supprimerDuPanier(dto);
        return Map.of("message", message);
    }
}