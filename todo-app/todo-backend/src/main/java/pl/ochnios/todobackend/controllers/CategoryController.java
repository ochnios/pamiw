package pl.ochnios.todobackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
    public CategoryDto get(@PathVariable int id) {
        return CategoryDto.mapToDto(categoryService.getCategory(id));
    }

    @GetMapping
    public List<CategoryDto> getAll() {
        List<CategoryDto> categories = new ArrayList<>();

        categoryService.getAllCategories().forEach((x) -> categories.add(CategoryDto.mapToDto(x)));

        return categories;
    }

    @PostMapping
    public CategoryDto create(@RequestBody CategoryDto dto) {
        return CategoryDto.mapToDto(categoryService.createCategory(categoryService.mapFromDto(dto)));
    }

    @PatchMapping("/{id}")
    public CategoryDto update(@PathVariable int id, @RequestBody CategoryDto dto) {
        return CategoryDto.mapToDto(categoryService.updateCategory(id, categoryService.mapFromDto(dto)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        categoryService.deleteCategory(id);
    }
}
