package pl.ochnios.todofrontendweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.ochnios.todofrontendweb.conf.Consts;
import pl.ochnios.todofrontendweb.dtos.CategoryDto;
import pl.ochnios.todofrontendweb.dtos.ResultsPage;
import pl.ochnios.todofrontendweb.services.CategoryService;

import java.util.List;

@RequestMapping("/categories")
@Controller
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getPaginated(@RequestParam(required = false) Integer pageNumber,
                               @RequestParam(required = false) String sortField,
                               @RequestParam(required = false) String sortDirection,
                               Model model) {

        pageNumber = pageNumber != null && pageNumber >= 1 ? pageNumber : 1;
        sortField = sortField != null ? sortField : "id";
        sortDirection = sortDirection != null ? sortDirection : "asc";

        ResultsPage page = categoryService.getPaginatedCategories(pageNumber, Consts.PAGE_SIZE, sortField, sortDirection).block();
        List<CategoryDto> categories = page.getResults();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDir", sortDirection.equals("asc") ? "desc" : "asc");

        model.addAttribute("categories", categories);

        return "/categories/categories.html";
    }

    @GetMapping("/create")
    public String getCreationForm(Model model) {
        CategoryDto category = new CategoryDto();
        model.addAttribute("category", category);
        return "/categories/create.html";
    }

    @GetMapping("/update/{id}")
    public String getUpdateForm(@PathVariable int id, Model model) {
        CategoryDto category = categoryService.getCategory(id).block();
        model.addAttribute("category", category);
        return "/categories/update.html";
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("category") CategoryDto category) {
        if (category.getId() == 0) {
            // todo: fix error when categories with the same name is added
            categoryService.createCategory(category).subscribe();
        } else {
            categoryService.updateCategory(category.getId(), category).subscribe();
        }

        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") int id) {
        // todo: fix error when we want to delete category which is assigned to existing task
        this.categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}
