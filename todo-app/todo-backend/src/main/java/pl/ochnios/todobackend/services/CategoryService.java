package pl.ochnios.todobackend.services;

import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ochnios.todobackend.models.Category;
import pl.ochnios.todobackend.repositories.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final Validator validator;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, Validator validator) {
        this.categoryRepository = categoryRepository;
        this.validator = validator;
    }

    public Category getCategory(int id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
