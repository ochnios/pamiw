package pl.ochnios.todofrontend.views;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import pl.ochnios.todofrontend.viewmodels.CategoryViewModel;

public class CategoryView {
    @FXML
    TextField categoryName;

    private CategoryViewModel categoryViewModel;

    public void init(CategoryViewModel categoryViewModel) {
        this.categoryViewModel = categoryViewModel;
        categoryName.setText("Just testing");
    }
}
