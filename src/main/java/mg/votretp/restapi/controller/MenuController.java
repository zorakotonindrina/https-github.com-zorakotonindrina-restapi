package mg.votretp.restapi.controller;


import mg.votretp.restapi.dto.CategoryWithPlatsDTO;
import mg.votretp.restapi.service.PlatMenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private final PlatMenuService platMenuService;

    public MenuController(PlatMenuService platMenuService) {
        this.platMenuService = platMenuService;
    }

    @GetMapping("/plats-groupes")
    public List<CategoryWithPlatsDTO> getPlatsGroupesParCategorie() {
        return platMenuService.getPlatsGroupesParCategorie();
    }
}