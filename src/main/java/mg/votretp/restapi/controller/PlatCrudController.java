package mg.votretp.restapi.controller;

import mg.votretp.restapi.dto.PlatCreateDTO;
import mg.votretp.restapi.model.Plat;
import mg.votretp.restapi.service.PlatService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/plats")
public class PlatCrudController {

    private final PlatService platService;

    public PlatCrudController(PlatService platService) {
        this.platService = platService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','RESTAURANT_OWNER')")
    @PostMapping(consumes = "multipart/form-data")
    public Plat createPlat(@RequestParam("nom") String nom,
                           @RequestParam("detail") String detail,
                           @RequestParam(value = "dispo", required = false) Boolean dispo,
                           @RequestParam("idCategorie") Long idCategorie,
                           @RequestParam("idRestaurant") Long idRestaurant,
                           @RequestParam("imageFile") MultipartFile imageFile) {

        PlatCreateDTO dto = new PlatCreateDTO();
        dto.setNom(nom);
        dto.setDetail(detail);
        dto.setDispo(dispo);
        dto.setIdCategorie(idCategorie);
        dto.setIdRestaurant(idRestaurant);

        return platService.createPlat(dto, imageFile);
    }

    @GetMapping
    public List<Plat> getAllPlatsCrud() {
        return platService.getAllPlatsCrud();
    }

    @GetMapping("/{id}")
    public Plat getPlatById(@PathVariable Long id) {
        return platService.getPlatById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','RESTAURANT_OWNER')")
    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public Plat updatePlat(@PathVariable Long id,
                           @RequestParam("nom") String nom,
                           @RequestParam("detail") String detail,
                           @RequestParam(value = "dispo", required = false) Boolean dispo,
                           @RequestParam("idCategorie") Long idCategorie,
                           @RequestParam("idRestaurant") Long idRestaurant,
                           @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {

        PlatCreateDTO dto = new PlatCreateDTO();
        dto.setNom(nom);
        dto.setDetail(detail);
        dto.setDispo(dispo);
        dto.setIdCategorie(idCategorie);
        dto.setIdRestaurant(idRestaurant);

        return platService.updatePlat(id, dto, imageFile);
    }

    @PreAuthorize("hasAnyRole('ADMIN','RESTAURANT_OWNER')")
    @DeleteMapping("/{id}")
    public Map<String, String> deletePlat(@PathVariable Long id) {
        return Map.of("message", platService.deletePlat(id));
    }
}