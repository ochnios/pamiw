package pl.ochnios.todobackend.dtos;

import lombok.Data;
import pl.ochnios.todobackend.models.Task;
import pl.ochnios.todobackend.models.TaskStatus;

@Data
public class TaskDto {

    private int id;
    private String title;
    private String description;
    private TaskStatus status;
    private Integer userId;
    private String user;
    private Integer categoryId;
    private String category;

    public static TaskDto mapToDto(Task task) {
        if (task == null) return null;

        TaskDto dto = new TaskDto();
        dto.id = task.getId();
        dto.title = task.getTitle();
        dto.description = task.getDescription();
        dto.status = task.getStatus();
        dto.userId = task.getAssigned() != null ? task.getAssigned().getId() : null;
        dto.user = task.getAssigned() != null ? task.getAssigned().getSurname() + " " + task.getAssigned().getName() : null;
        dto.categoryId = task.getCategory() != null ? task.getCategory().getId() : null;
        dto.category = task.getCategory() != null ? task.getCategory().getName() : null;

        return dto;
    }
}
