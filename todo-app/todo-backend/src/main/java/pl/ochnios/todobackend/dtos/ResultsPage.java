package pl.ochnios.todobackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResultsPage<T> {
    private List<T> results;
    private int currentPage;
    private int totalPages;
    private long totalElements;
}
