package pl.ochnios.todofrontend.viewmodels;

import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import lombok.Getter;
import pl.ochnios.todofrontend.models.CategoryModel;
import pl.ochnios.todofrontend.models.dtos.CategoryDto;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CategoryViewModel {
    private final CategoryModel categoryModel;
    private final ListProperty<CategoryDto> categories;

    public CategoryViewModel(CategoryModel categoryModel) {
        this.categoryModel = categoryModel;
        this.categories = new SimpleListProperty<>(FXCollections.observableList(new ArrayList<>()));

        categoryModel.addListener("categories", this::updateCategories);
    }

    public void search(String id, String name) {
        categoryModel.fetch(id, name);
    }

    public void create(String name) {
        categoryModel.create(name);
    }

    public void update(String id, String name) {
        categoryModel.update(id, name);
    }

    public void delete(String id) {
        categoryModel.delete(id);
    }

    @SuppressWarnings("unchecked")
    private void updateCategories(PropertyChangeEvent evt) {
        Platform.runLater(() -> categories.setValue(FXCollections.observableList((List<CategoryDto>) evt.getNewValue())));
    }
}
