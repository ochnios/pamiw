package pl.ochnios.todofrontend.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    @JsonProperty
    private int id;
    @JsonProperty
    private String title;
    @JsonProperty
    private String description;
    @JsonProperty
    private String status;
    @JsonProperty
    private int userId;
    @JsonProperty
    private int categoryId;
}
