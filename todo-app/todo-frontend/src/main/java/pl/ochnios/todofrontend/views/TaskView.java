package pl.ochnios.todofrontend.views;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.ochnios.todofrontend.core.Consts;
import pl.ochnios.todofrontend.models.dtos.TaskDto;
import pl.ochnios.todofrontend.viewmodels.TaskViewModel;

public class TaskView {
    @FXML
    TextField taskIdField;
    @FXML
    TextField taskTitleField;
    @FXML
    ChoiceBox<String> taskStatusBox;
    @FXML
    TextArea taskDescriptionArea;
    @FXML
    private TableView<TaskDto> tasksTable;
    @FXML
    private TableColumn<TaskDto, Integer> taskIdColumn;
    @FXML
    private TableColumn<TaskDto, String> taskTitleColumn;
    @FXML
    private TableColumn<TaskDto, String> taskStatusColumn;
    @FXML
    private TableColumn<TaskDto, Integer> taskUserColumn;
    @FXML
    private TableColumn<TaskDto, Integer> taskCategoryColumn;
    @FXML
    Label infoLabel;

    private TaskViewModel taskViewModel;

    public void init(TaskViewModel taskViewModel) {
        this.taskViewModel = taskViewModel;

        populateChoiceBox();

        taskIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        taskTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        taskStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        taskUserColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        taskCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("categoryId"));

        tasksTable.itemsProperty().bind(taskViewModel.getTasks());
    }

    @FXML
    protected void onSearchButtonClick() {
        try {
            taskViewModel.search(taskIdField.getText(), taskTitleField.getText());
        } catch (Exception e) {
            e.printStackTrace();
            infoLabel.setText(e.getMessage());
        }
    }

    @FXML
    protected void onCreateButtonClick() {
        try {
            taskViewModel.create(taskTitleField.getText(), taskDescriptionArea.getText(), taskStatusBox.getValue());
            infoLabel.setText("Created successfully");
        } catch (Exception e) {
            e.printStackTrace();
            infoLabel.setText(e.getMessage());
        }
    }

    @FXML
    protected void onUpdateButtonClick() {
        try {
            taskViewModel.update(taskIdField.getText(), taskTitleField.getText(), taskDescriptionArea.getText(), taskStatusBox.getValue());
            infoLabel.setText("Updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            infoLabel.setText(e.getMessage());
        }
    }

    @FXML
    protected void onDeleteButtonClick() {
        try {
            taskViewModel.delete(taskIdField.getText());
            infoLabel.setText("Deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            infoLabel.setText(e.getMessage());
        }
    }

    @FXML
    protected void onTableClick() {
        TaskDto selected = tasksTable.getSelectionModel().getSelectedItem();
        taskIdField.setText(Integer.toString(selected.getId()));
        taskTitleField.setText(selected.getTitle());
        taskDescriptionArea.setText(selected.getDescription());
        taskStatusBox.setValue(selected.getStatus());
    }

    @FXML
    protected void onClearButtonClick() {
        clearInput();
    }

    private void clearInput() {
        taskIdField.setText(Consts.EMPTY_STRING);
        taskTitleField.setText(Consts.EMPTY_STRING);
        taskDescriptionArea.setText(Consts.EMPTY_STRING);
        taskStatusBox.setValue(Consts.EMPTY_STRING);
    }

    private void populateChoiceBox() {
        taskStatusBox.getItems().add("New");
        taskStatusBox.getItems().add("InProgress");
        taskStatusBox.getItems().add("Completed");
        taskStatusBox.getItems().add("Cancelled");
        taskStatusBox.setValue("New");
    }
}
