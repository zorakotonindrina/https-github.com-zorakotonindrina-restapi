package mg.votretp.restapi.service;


import mg.votretp.restapi.dto.CategoryWithPlatsDTO;
import mg.votretp.restapi.dto.PlatDetailDTO;
import mg.votretp.restapi.model.Category;
import mg.votretp.restapi.model.Plat;
import mg.votretp.restapi.model.PrixPlat;
import mg.votretp.restapi.repository.CategoryRepository;
import mg.votretp.restapi.repository.PlatRepository;
import mg.votretp.restapi.repository.PrixPlatRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlatMenuService {

    private final CategoryRepository categoryRepository;
    private final PlatRepository platRepository;
    private final PrixPlatRepository prixPlatRepository;

    public PlatMenuService(CategoryRepository categoryRepository,
                           PlatRepository platRepository,
                           PrixPlatRepository prixPlatRepository) {
        this.categoryRepository = categoryRepository;
        this.platRepository = platRepository;
        this.prixPlatRepository = prixPlatRepository;
    }

    public List<CategoryWithPlatsDTO> getPlatsGroupesParCategorie() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryWithPlatsDTO> result = new ArrayList<>();

        for (Category category : categories) {
            List<Plat> plats = platRepository.findByCategory_IdCategorieAndDispoTrue(category.getIdCategorie());

            List<PlatDetailDTO> platsDto = plats.stream()
                    .map(this::mapPlatToDto)
                    .toList();

            result.add(new CategoryWithPlatsDTO(
                    category.getIdCategorie(),
                    category.getNom(),
                    platsDto
            ));
        }

        return result;
    }

    private PlatDetailDTO mapPlatToDto(Plat plat) {
        BigDecimal prixActuel = prixPlatRepository
                .findTopByPlat_IdPlatOrderByDatePrixDesc(plat.getIdPlat())
                .map(PrixPlat::getPrix)
                .orElse(BigDecimal.ZERO);

        return new PlatDetailDTO(
                plat.getIdPlat(),
                plat.getNom(),
                plat.getImage(),
                plat.getDetail(),
                plat.getDispo(),
                prixActuel
        );
    }
}