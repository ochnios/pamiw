package pl.ochnios.todofrontend.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryDto {
    private int id;
    private String name;
}
