package pl.ochnios.todofrontend.models.services;

import pl.ochnios.todofrontend.models.dtos.CategoryDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryService {
    private final List<CategoryDto> categories = new ArrayList<>();

    public CategoryService() {
        categories.add(new CategoryDto(1, "Category 1"));
        categories.add(new CategoryDto(2, "Category 2"));
    }

    public List<CategoryDto> getAll() {
        return categories;
    }

    public List<CategoryDto> getById(String id) {
        return new ArrayList<>(Collections.singletonList(categories.get(Integer.parseInt(id))));
    }

    public List<CategoryDto> getByName(String name) {
        return categories.stream()
                .filter(n -> name.equals(n.getName()))
                .collect(Collectors.toList());
    }
}
