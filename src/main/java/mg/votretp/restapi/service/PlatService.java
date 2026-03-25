package mg.votretp.restapi.service;

import mg.votretp.restapi.dto.PlatCreateDTO;
import mg.votretp.restapi.model.Category;
import mg.votretp.restapi.model.Plat;
import mg.votretp.restapi.model.Restaurant;
import mg.votretp.restapi.repository.CategoryRepository;
import mg.votretp.restapi.repository.PlatRepository;
import mg.votretp.restapi.repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class PlatService {

    private final PlatRepository platRepository;
    private final CategoryRepository categoryRepository;
    private final RestaurantRepository restaurantRepository;
    private final FileStorageService fileStorageService;

    public PlatService(PlatRepository platRepository,
                       CategoryRepository categoryRepository,
                       RestaurantRepository restaurantRepository,
                       FileStorageService fileStorageService) {
        this.platRepository = platRepository;
        this.categoryRepository = categoryRepository;
        this.restaurantRepository = restaurantRepository;
        this.fileStorageService = fileStorageService;
    }

    public Plat createPlat(PlatCreateDTO dto, MultipartFile imageFile) {
        Category category = categoryRepository.findById(dto.getIdCategorie())
                .orElseThrow(() -> new RuntimeException("Catégorie introuvable"));

        Restaurant restaurant = restaurantRepository.findById(dto.getIdRestaurant())
                .orElseThrow(() -> new RuntimeException("Restaurant introuvable"));

        String fileName = fileStorageService.saveFile(imageFile);

        Plat plat = new Plat();
        plat.setNom(dto.getNom());
        plat.setDetail(dto.getDetail());
        plat.setDispo(dto.getDispo() != null ? dto.getDispo() : true);
        plat.setImage(fileName);
        plat.setCategory(category);
        plat.setRestaurant(restaurant);

        return platRepository.save(plat);
    }

    public List<Plat> getAllPlatsCrud() {
        return platRepository.findAll();
    }

    public Plat getPlatById(Long id) {
        return platRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plat introuvable"));
    }

    public Plat updatePlat(Long id, PlatCreateDTO dto, MultipartFile imageFile) {
        Plat plat = platRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plat introuvable"));

        Category category = categoryRepository.findById(dto.getIdCategorie())
                .orElseThrow(() -> new RuntimeException("Catégorie introuvable"));

        Restaurant restaurant = restaurantRepository.findById(dto.getIdRestaurant())
                .orElseThrow(() -> new RuntimeException("Restaurant introuvable"));

        plat.setNom(dto.getNom());
        plat.setDetail(dto.getDetail());
        plat.setDispo(dto.getDispo() != null ? dto.getDispo() : true);
        plat.setCategory(category);
        plat.setRestaurant(restaurant);

        if (imageFile != null && !imageFile.isEmpty()) {
            String fileName = fileStorageService.saveFile(imageFile);
            plat.setImage(fileName);
        }

        return platRepository.save(plat);
    }

    public String deletePlat(Long id) {
        Plat plat = platRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plat introuvable"));

        platRepository.delete(plat);
        return "Plat supprimé avec succès";
    }
}