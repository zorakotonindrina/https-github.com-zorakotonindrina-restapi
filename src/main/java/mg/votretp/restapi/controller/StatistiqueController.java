package mg.votretp.restapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mg.votretp.restapi.dto.TopPlatDTO;
import mg.votretp.restapi.dto.TotalParModePaiementDTO;
import mg.votretp.restapi.service.StatistiqueService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Statistiques", description = "Endpoints d'agrégation et statistiques")
@RequestMapping("/api/statistiques")
public class StatistiqueController {

    private final StatistiqueService statistiqueService;

    public StatistiqueController(StatistiqueService statistiqueService) {
        this.statistiqueService = statistiqueService;
    }

    @Operation(summary = "Afficher les plats les plus commandés")
    @GetMapping("/top-plats")
    public List<TopPlatDTO> getTopPlats() {
        return statistiqueService.getTopPlats();
    }

    @Operation(summary = "Afficher le total des commandes par mode de paiement")
    @PreAuthorize("hasAnyRole('ADMIN','RESTAURANT_OWNER')")
    @GetMapping("/total-par-mode-paiement")
    public List<TotalParModePaiementDTO> getTotalParModePaiement() {
        return statistiqueService.getTotalParModePaiement();
    }
}