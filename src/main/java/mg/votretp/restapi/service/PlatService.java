package mg.votretp.restapi.service;


import mg.votretp.restapi.dto.PlatCreateDTO;
import mg.votretp.restapi.model.Category;
import mg.votretp.restapi.model.Plat;
import mg.votretp.restapi.model.Restaurant;
import mg.votretp.restapi.repository.CategoryRepository;
import mg.votretp.restapi.repository.PlatRepository;
import mg.votretp.restapi.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

@Service
public class PlatService {

    private final PlatRepository platRepository;
    private final CategoryRepository categoryRepository;
    private final RestaurantRepository restaurantRepository;

    public PlatService(PlatRepository platRepository,
                       CategoryRepository categoryRepository,
                       RestaurantRepository restaurantRepository) {
        this.platRepository = platRepository;
        this.categoryRepository = categoryRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Plat createPlat(PlatCreateDTO dto) {
        Category category = categoryRepository.findById(dto.getIdCategorie())
                .orElseThrow(() -> new RuntimeException("Catégorie introuvable"));

        Restaurant restaurant = restaurantRepository.findAll().get(0);

        Plat plat = new Plat();
        plat.setNom(dto.getNom());
        plat.setImage(dto.getImage());
        plat.setDetail(dto.getDetail());
        plat.setDispo(dto.getDispo() != null ? dto.getDispo() : true);
        plat.setCategory(category);
        plat.setRestaurant(restaurant);

        return platRepository.save(plat);
    }
}