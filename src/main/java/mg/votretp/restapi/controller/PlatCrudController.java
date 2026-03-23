package mg.votretp.restapi.controller;


import mg.votretp.restapi.dto.PlatCreateDTO;
import mg.votretp.restapi.model.Plat;
import mg.votretp.restapi.service.PlatService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/plats")
public class PlatCrudController {

    private final PlatService platService;

    public PlatCrudController(PlatService platService) {
        this.platService = platService;
    }

    @PostMapping
    public Plat createPlat(@RequestBody PlatCreateDTO dto) {
        return platService.createPlat(dto);
    }


}