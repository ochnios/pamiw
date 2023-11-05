package pl.ochnios.todofrontend.views;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.ochnios.todofrontend.models.dtos.CategoryDto;
import pl.ochnios.todofrontend.viewmodels.CategoryViewModel;

public class CategoryView {
    @FXML
    TextField categoryIdField;
    @FXML
    TextField categoryNameField;
    @FXML
    private TableView<CategoryDto> categoriesTable;
    @FXML
    private TableColumn<CategoryDto, Integer> categoryIdColumn;
    @FXML
    private TableColumn<CategoryDto, String> categoryNameColumn;
    @FXML
    Label infoLabel;

    private CategoryViewModel categoryViewModel;

    public void init(CategoryViewModel categoryViewModel) {
        this.categoryViewModel = categoryViewModel;

        categoryIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        categoryNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        categoriesTable.itemsProperty().bind(categoryViewModel.getCategories());
    }

    @FXML
    protected void onSearchButtonClick() {
        categoryViewModel.search(categoryIdField.getText(), categoryNameField.getText());
        System.out.println(categoryViewModel.getCategories().size());
    }

    @FXML
    protected void onCreateButtonClick() {
    }

    @FXML
    protected void onUpdateButtonClick() {
    }

    @FXML
    protected void onDeleteButtonClick() {
    }
}
