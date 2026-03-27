package mg.votretp.restapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import mg.votretp.restapi.dto.CommandeCuisineDTO;
import mg.votretp.restapi.dto.CommandeJourStatDTO;
import mg.votretp.restapi.dto.UpdateCommandeCuisineStatusDTO;
import mg.votretp.restapi.service.CuisineService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cuisine")
@Tag(
        name = "GESTION COTE CUISINIER",
        description = "Endpoints réservés au cuisinier et à l’administrateur pour consulter les commandes de cuisine, modifier leur état et afficher les statistiques journalières."
)
@SecurityRequirement(name = "bearerAuth")
@PreAuthorize("hasAnyRole('CUISINIER','ADMIN')")
public class CuisineController {

    private final CuisineService cuisineService;

    public CuisineController(CuisineService cuisineService) {
        this.cuisineService = cuisineService;
    }

    @Operation(
            summary = "Lister les commandes pour la cuisine",
            description = "Retourne la liste des commandes visibles côté cuisine. "
                    + "En pratique, cette liste contient les commandes à traiter par le cuisinier."
    )
    @GetMapping("/commandes")
    public List<CommandeCuisineDTO> getCommandesCuisine() {
        return cuisineService.getCommandesCuisine();
    }

    @Operation(
            summary = "Afficher le détail d’une commande côté cuisine",
            description = "Retourne le détail complet d’une commande précise pour affichage dans l’interface cuisine."
    )
    @GetMapping("/commandes/{id}")
    public CommandeCuisineDTO getCommandeCuisineById(
            @Parameter(description = "Identifiant unique de la commande", example = "1")
            @PathVariable Long id
    ) {
        return cuisineService.getCommandeCuisineById(id);
    }

    @Operation(
            summary = "Modifier le statut d’une commande côté cuisine",
            description = "Permet au cuisinier de faire évoluer l’état d’une commande. "
                    + "Exemples de transitions attendues : CONFIRMEE -> EN_PREPARATION, puis EN_PREPARATION -> FINIE."
    )
    @PutMapping("/commandes/status")
    public Map<String, String> updateStatusCuisine(@RequestBody UpdateCommandeCuisineStatusDTO dto) {
        return Map.of("message", cuisineService.updateStatusCuisine(dto));
    }

    @Operation(
            summary = "Afficher les statistiques de commandes du jour côté cuisine",
            description = "Retourne le nombre de commandes du jour par état métier suivi côté cuisine, "
                    + "par exemple en attente, en préparation et finies."
    )
    @GetMapping("/commandes/statistiques-jour")
    public CommandeJourStatDTO getStatutsCommandesDuJour() {
        return cuisineService.getStatutsCommandesDuJour();
    }
}