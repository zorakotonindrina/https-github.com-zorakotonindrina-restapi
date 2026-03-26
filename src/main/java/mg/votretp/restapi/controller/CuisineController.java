package mg.votretp.restapi.controller;

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
@PreAuthorize("hasAnyRole('CUISINIER','ADMIN')")
public class CuisineController {

    private final CuisineService cuisineService;

    public CuisineController(CuisineService cuisineService) {
        this.cuisineService = cuisineService;
    }

    @GetMapping("/commandes")
    public List<CommandeCuisineDTO> getCommandesCuisine() {
        return cuisineService.getCommandesCuisine();
    }

    @GetMapping("/commandes/{id}")
    public CommandeCuisineDTO getCommandeCuisineById(@PathVariable Long id) {
        return cuisineService.getCommandeCuisineById(id);
    }

    @PutMapping("/commandes/status")
    public Map<String, String> updateStatusCuisine(@RequestBody UpdateCommandeCuisineStatusDTO dto) {
        return Map.of("message", cuisineService.updateStatusCuisine(dto));
    }

    @GetMapping("/commandes/statistiques-jour")
    public CommandeJourStatDTO getStatutsCommandesDuJour() {
        return cuisineService.getStatutsCommandesDuJour();
    }
}