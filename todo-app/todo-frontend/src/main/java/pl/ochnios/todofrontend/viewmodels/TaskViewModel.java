package pl.ochnios.todofrontend.viewmodels;

import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import lombok.Getter;
import pl.ochnios.todofrontend.models.TaskModel;
import pl.ochnios.todofrontend.models.dtos.TaskDto;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

@Getter
public class TaskViewModel {
    private final TaskModel taskModel;
    private final ListProperty<TaskDto> tasks;

    public TaskViewModel(TaskModel taskModel) {
        this.taskModel = taskModel;
        this.tasks = new SimpleListProperty<>(FXCollections.observableList(new ArrayList<>()));

        taskModel.addListener("tasks", this::updateTasks);
    }

    public void search(String id, String title) throws Exception {
        taskModel.fetch(id, title);
    }

    public void create(String title, String description, String status) throws Exception {
        taskModel.create(title, description, status);
    }

    public void update(String id, String title, String description, String status) throws Exception {
        taskModel.update(id, title, description, status);
    }

    public void delete(String id) throws Exception {
        taskModel.delete(id);
    }

    @SuppressWarnings("unchecked")
    private void updateTasks(PropertyChangeEvent evt) {
        Platform.runLater(() -> tasks.setValue(FXCollections.observableList((List<TaskDto>) evt.getNewValue())));
    }
}
