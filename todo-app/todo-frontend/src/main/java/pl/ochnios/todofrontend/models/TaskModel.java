package pl.ochnios.todofrontend.models;

import com.google.inject.Inject;
import pl.ochnios.todofrontend.models.dtos.TaskDto;
import pl.ochnios.todofrontend.models.services.TaskService;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class TaskModel {
    private List<TaskDto> tasks;
    private final TaskService taskService;
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    @Inject
    public TaskModel(TaskService taskService) {
        this.taskService = taskService;
    }

    public void addListener(String title, PropertyChangeListener listener) {
        if (title == null) {
            changeSupport.addPropertyChangeListener(listener);
        } else {
            changeSupport.addPropertyChangeListener(title, listener);
        }
    }

    public void fetch(String id, String title) throws Exception {
        List<TaskDto> results;

        if (!id.isEmpty()) {
            results = taskService.getById(id);
        } else if (!title.isEmpty()) {
            results = taskService.getByTitle(title);
        } else {
            results = taskService.getAll();
        }

        changeSupport.firePropertyChange("tasks", tasks, results);
        tasks = results;
    }

    public void create(String title, String description, String status) throws Exception {
        List<TaskDto> created = taskService.create(title, description, status);

        changeSupport.firePropertyChange("tasks", tasks, created);

        tasks = created;
    }

    public void update(String id, String title, String description, String status) throws Exception {
        List<TaskDto> updated = taskService.update(id, title, description, status);

        changeSupport.firePropertyChange("tasks", tasks, updated);

        tasks = updated;
    }

    public void delete(String id) throws Exception {
        taskService.delete(id);
        changeSupport.firePropertyChange("tasks", null, tasks);
    }
}
