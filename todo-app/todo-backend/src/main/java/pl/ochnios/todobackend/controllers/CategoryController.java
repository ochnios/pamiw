package pl.ochnios.todobackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.ochnios.todobackend.dtos.CategoryDto;
import pl.ochnios.todobackend.dtos.ResultsPage;
import pl.ochnios.todobackend.models.Category;
import pl.ochnios.todobackend.services.CategoryService;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/categories")
@RestController
@CrossOrigin
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> get(@PathVariable int id) {
        CategoryDto category = CategoryDto.mapToDto(categoryService.getCategory(id));
        return category != null ? ResponseEntity.ok(category) : ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<ResultsPage<CategoryDto>> getPaginated(@RequestParam(required = false) Integer pageNumber,
                                                                 @RequestParam(required = false) Integer pageSize,
                                                                 @RequestParam(required = false) String sortField,
                                                                 @RequestParam(required = false) String sortDirection,
                                                                 Model model) {

        pageNumber = pageNumber != null && pageNumber >= 1 ? pageNumber : 1;
        pageSize = pageSize != null && pageSize >= 1 ? pageSize : 5;
        sortField = sortField != null ? sortField : "id";
        sortDirection = sortDirection != null ? sortDirection : "asc";

        Page<Category> page = categoryService.getPaginatedCategories(pageNumber, pageSize, sortField, sortDirection);

        List<CategoryDto> categories = new ArrayList<>();
        page.getContent().forEach((x) -> categories.add(CategoryDto.mapToDto(x)));

        ResultsPage results = new ResultsPage(categories, page.getNumber() + 1, page.getTotalPages(), page.getTotalElements());

        return !categories.isEmpty() ? ResponseEntity.ok(results) : ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryDto>> getAll() {
        List<CategoryDto> categories = new ArrayList<>();
        categoryService.getAllCategories().forEach((x) -> categories.add(CategoryDto.mapToDto(x)));

        return !categories.isEmpty() ? ResponseEntity.ok(categories) : ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<CategoryDto> create(@RequestBody CategoryDto dto) {
        CategoryDto createdCategory = CategoryDto.mapToDto(categoryService.createCategory(categoryService.mapFromDto(dto)));
        return new ResponseEntity<CategoryDto>(createdCategory, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryDto> update(@PathVariable int id, @RequestBody CategoryDto dto) {
        CategoryDto updatedCategory = CategoryDto.mapToDto(categoryService.updateCategory(id, categoryService.mapFromDto(dto)));
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }
}
