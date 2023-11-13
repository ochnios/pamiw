package pl.ochnios.todofrontendweb.services;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.ochnios.todofrontendweb.Consts;
import pl.ochnios.todofrontendweb.dtos.CategoryDto;
import pl.ochnios.todofrontendweb.models.Category;
import pl.ochnios.todofrontendweb.repositories.CategoryRepository;

import java.util.Set;

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

    public Iterable<Category> getAllCategories(int page) {
        return categoryRepository.findAll(PageRequest.of(page, Consts.PAGE_SIZE));
    }

    public Category createCategory(Category category) {
        Set<ConstraintViolation<Category>> violations = validator.validate(category);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        return categoryRepository.save(category);
    }

    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }

    public Category updateCategory(int id, Category category) {
        Category categoryToUpdate = categoryRepository.findById(id).orElse(null);

        if (categoryToUpdate == null) {
            throw new IllegalArgumentException(String.format("The category with id = %d was not found - failed to update", id));
        } else if (category == null) {
            throw new IllegalArgumentException("The updating category must not be null");
        }

        categoryToUpdate.setName(category.getName());

        Set<ConstraintViolation<Category>> violations = validator.validate(categoryToUpdate);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        return categoryRepository.save(categoryToUpdate);
    }

    public Category mapFromDto(CategoryDto dto) {
        return dto == null ? null : Category.builder().name(dto.getName()).build();
    }
}
