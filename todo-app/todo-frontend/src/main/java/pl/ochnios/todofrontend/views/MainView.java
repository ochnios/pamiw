package pl.ochnios.todofrontend.views;

import javafx.fxml.FXML;

public class MainView {
    @FXML
    private TaskView taskController;
    @FXML
    private CategoryView categoryController;

    public TaskView getTaskView() {
        return taskController;
    }

    public CategoryView getCategoryView() {
        return categoryController;
    }
}
