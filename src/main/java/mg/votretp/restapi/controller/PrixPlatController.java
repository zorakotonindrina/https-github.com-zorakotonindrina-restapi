package mg.votretp.restapi.controller;


import mg.votretp.restapi.dto.PrixPlatCreateDTO;
import mg.votretp.restapi.model.PrixPlat;
import mg.votretp.restapi.service.PrixPlatService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prix-plats")
public class PrixPlatController {

    private final PrixPlatService prixPlatService;

    public PrixPlatController(PrixPlatService prixPlatService) {
        this.prixPlatService = prixPlatService;
    }

    @PostMapping
    public PrixPlat createPrixPlat(@RequestBody PrixPlatCreateDTO dto) {
        return prixPlatService.createPrixPlat(dto);
    }
}