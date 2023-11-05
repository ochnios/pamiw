package pl.ochnios.todofrontend.models;

import com.google.inject.Inject;
import pl.ochnios.todofrontend.models.services.CategoryService;

public class CategoryModel {
    private final CategoryService categoryService;

    @Inject
    public CategoryModel(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
