package pl.ochnios.todofrontend.views;

import javafx.fxml.FXML;

public class MainView {
    @FXML
    private CategoryView categoryController;

    public CategoryView getCategoryView() {
        return categoryController;
    }
}
