package pl.ochnios.todobackend.dtos;

import lombok.Data;
import pl.ochnios.todobackend.models.Category;

@Data
public class CategoryDto {

    private int id;
    private String name;

    public static CategoryDto mapToDto(Category category) {
        if(category == null) return null;

        CategoryDto dto = new CategoryDto();
        dto.id = category.getId();
        dto.name = category.getName();

        return dto;
    }
}
