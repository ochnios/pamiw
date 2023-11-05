package pl.ochnios.todofrontend.models.services;

import pl.ochnios.todofrontend.models.dtos.CategoryDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryService {
    private final List<CategoryDto> categories = new ArrayList<>();

    public CategoryService() {
        categories.add(new CategoryDto(0, "Category 1"));
        categories.add(new CategoryDto(1, "Category 2"));
    }

    public List<CategoryDto> getAll() {
        return categories;
    }

    public List<CategoryDto> getById(String id) {
        return categories.stream()
                .filter(x -> id.equals(Integer.toString(x.getId())))
                .collect(Collectors.toList());
    }

    public List<CategoryDto> getByName(String name) {
        return categories.stream()
                .filter(x -> name.equals(x.getName()))
                .collect(Collectors.toList());
    }

    public List<CategoryDto> create(String name) {
        CategoryDto category = new CategoryDto(categories.size(), name);
        categories.add(category);
        return new ArrayList<>(Collections.singletonList(category));
    }

    public List<CategoryDto> update(String id, String name) {
        for (CategoryDto dto : categories) {
            if (id.equals(Integer.toString(dto.getId()))) {
                dto.setName(name);
                return new ArrayList<>(Collections.singletonList(dto));
            }
        }
        return null;
    }

    public void delete(String id) {
        categories.remove(Integer.parseInt(id));
    }
}
