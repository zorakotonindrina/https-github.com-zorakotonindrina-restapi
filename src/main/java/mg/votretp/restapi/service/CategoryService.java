package mg.votretp.restapi.service;

import mg.votretp.restapi.dto.CategoryCreateDTO;
import mg.votretp.restapi.model.Category;
import mg.votretp.restapi.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Catégorie introuvable"));
    }

    public Category updateCategory(Long id, CategoryCreateDTO dto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Catégorie introuvable"));

        category.setNom(dto.getNom());
        return categoryRepository.save(category);
    }

    public String deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Catégorie introuvable"));

        categoryRepository.delete(category);
        return "Catégorie supprimée avec succès";
    }
}