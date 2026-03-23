package mg.votretp.restapi.service;

import mg.votretp.restapi.dto.CategoryCreateDTO;
import mg.votretp.restapi.model.Category;
import mg.votretp.restapi.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(CategoryCreateDTO dto) {
        Category category = new Category();
        category.setNom(dto.getNom());

        return categoryRepository.save(category);
    }
}