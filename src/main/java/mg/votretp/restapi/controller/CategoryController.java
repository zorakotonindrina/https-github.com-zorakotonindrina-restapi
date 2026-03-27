package mg.votretp.restapi.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import mg.votretp.restapi.dto.CategoryCreateDTO;
import mg.votretp.restapi.model.Category;
import mg.votretp.restapi.service.CategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "Categories", description = "CRUD")
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','RESTAURANT_OWNER')")
    @PostMapping
    public Category createCategory(@RequestBody CategoryCreateDTO dto) {
        return categoryService.createCategory(dto);
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','RESTAURANT_OWNER')")
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody CategoryCreateDTO dto) {
        return categoryService.updateCategory(id, dto);
    }

    @PreAuthorize("hasAnyRole('ADMIN','RESTAURANT_OWNER')")
    @DeleteMapping("/{id}")
    public Map<String, String> deleteCategory(@PathVariable Long id) {
        return Map.of("message", categoryService.deleteCategory(id));
    }
}