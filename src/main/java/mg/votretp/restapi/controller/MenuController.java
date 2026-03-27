package mg.votretp.restapi.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mg.votretp.restapi.dto.CategoryWithPlatsDTO;
import mg.votretp.restapi.service.PlatMenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@Tag(name = "Menu", description = "Consultation du menu du restaurant")
public class MenuController {

    private final PlatMenuService platMenuService;

    public MenuController(PlatMenuService platMenuService) {
        this.platMenuService = platMenuService;
    }

    @Operation(summary = "Afficher les plats groupés par catégorie")
    @GetMapping("/plats-groupes")
    public List<CategoryWithPlatsDTO> getPlatsGroupesParCategorie() {
        return platMenuService.getPlatsGroupesParCategorie();
    }



}