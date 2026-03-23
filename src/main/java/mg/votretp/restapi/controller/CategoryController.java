package mg.votretp.restapi.controller;

import mg.votretp.restapi.dto.CategoryCreateDTO;
import mg.votretp.restapi.model.Category;
import mg.votretp.restapi.service.CategoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public Category createCategory(@RequestBody CategoryCreateDTO dto) {
        return categoryService.createCategory(dto);
    }
}