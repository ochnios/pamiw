package pl.ochnios.todofrontend.models;

import com.google.inject.Inject;
import pl.ochnios.todofrontend.models.dtos.CategoryDto;
import pl.ochnios.todofrontend.models.services.CategoryService;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class CategoryModel {
    private List<CategoryDto> categories;
    private final CategoryService categoryService;
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    @Inject
    public CategoryModel(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public void addListener(String name, PropertyChangeListener listener) {
        if (name == null) {
            changeSupport.addPropertyChangeListener(listener);
        } else {
            changeSupport.addPropertyChangeListener(name, listener);
        }
    }

    public void fetch(String id, String name) {
        List<CategoryDto> results;

        if (!id.isEmpty()) {
            results = categoryService.getById(id);
        } else if (!name.isEmpty()) {
            results = categoryService.getByName(name);
        } else {
            results = categoryService.getAll();
        }

        changeSupport.firePropertyChange("categories", categories, results);
        categories = results;
    }

    public void create(String name) {
        List<CategoryDto> created = categoryService.create(name);

        changeSupport.firePropertyChange("categories", categories, created);

        categories = created;
    }

    public void update(String id, String name) {
        List<CategoryDto> updated = categoryService.update(id, name);

        changeSupport.firePropertyChange("categories", categories, updated);

        categories = updated;
    }

    public void delete(String id) {
        categoryService.delete(id);
        changeSupport.firePropertyChange("categories", null, categories);
    }
}
