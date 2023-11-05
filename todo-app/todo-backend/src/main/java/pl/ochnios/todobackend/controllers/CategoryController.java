package pl.ochnios.todobackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ochnios.todobackend.dtos.CategoryDto;
import pl.ochnios.todobackend.services.CategoryService;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/categories")
@RestController
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
