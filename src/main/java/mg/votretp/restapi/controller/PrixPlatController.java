package mg.votretp.restapi.controller;

import mg.votretp.restapi.dto.PrixPlatCreateDTO;
import mg.votretp.restapi.model.PrixPlat;
import mg.votretp.restapi.service.PrixPlatService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/prix-plats")
public class PrixPlatController {

    private final PrixPlatService prixPlatService;

    public PrixPlatController(PrixPlatService prixPlatService) {
        this.prixPlatService = prixPlatService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','RESTAURANT_OWNER')")
    @PostMapping
    public PrixPlat createPrixPlat(@RequestBody PrixPlatCreateDTO dto) {
        return prixPlatService.createPrixPlat(dto);
    }

    @GetMapping
    public List<PrixPlat> getAllPrixPlats() {
        return prixPlatService.getAllPrixPlats();
    }

    @GetMapping("/{id}")
    public PrixPlat getPrixPlatById(@PathVariable Long id) {
        return prixPlatService.getPrixPlatById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','RESTAURANT_OWNER')")
    @PutMapping("/{id}")
    public PrixPlat updatePrixPlat(@PathVariable Long id, @RequestBody PrixPlatCreateDTO dto) {
        return prixPlatService.updatePrixPlat(id, dto);
    }

    @PreAuthorize("hasAnyRole('ADMIN','RESTAURANT_OWNER')")
    @DeleteMapping("/{id}")
    public Map<String, String> deletePrixPlat(@PathVariable Long id) {
        return Map.of("message", prixPlatService.deletePrixPlat(id));
    }
}