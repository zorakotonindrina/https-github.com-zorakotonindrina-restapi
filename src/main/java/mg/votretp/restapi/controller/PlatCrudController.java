package mg.votretp.restapi.controller;

import mg.votretp.restapi.dto.PlatCreateDTO;
import mg.votretp.restapi.model.Plat;
import mg.votretp.restapi.service.PlatService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/plats")
public class PlatCrudController {

    private final PlatService platService;

    public PlatCrudController(PlatService platService) {
        this.platService = platService;
    }

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
}