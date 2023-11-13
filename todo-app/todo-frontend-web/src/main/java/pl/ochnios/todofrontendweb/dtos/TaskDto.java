package pl.ochnios.todofrontendweb.dtos;

import lombok.Data;

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
}
