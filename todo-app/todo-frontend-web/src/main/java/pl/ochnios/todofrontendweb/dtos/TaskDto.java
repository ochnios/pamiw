package pl.ochnios.todofrontendweb.dtos;

import lombok.Data;
import pl.ochnios.todofrontendweb.models.Task;
import pl.ochnios.todofrontendweb.models.TaskStatus;

@Data
public class TaskDto {

    private int id;
    private String title;
    private String description;
    private TaskStatus status;
    private Integer userId;
    private Integer categoryId;

    public static TaskDto mapToDto(Task task) {
        if(task == null) return null;

        TaskDto dto = new TaskDto();
        dto.id = task.getId();
        dto.title = task.getTitle();
        dto.description = task.getDescription();
        dto.status = task.getStatus();
        dto.userId = task.getAssigned() != null ? task.getAssigned().getId() : null;
        dto.categoryId = task.getCategory() != null ? task.getCategory().getId() : null;

        return dto;
    }
}
