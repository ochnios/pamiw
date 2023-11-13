package pl.ochnios.todofrontendweb.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResultsPage<T> {
    private List<T> results;
    private int totalPages;
    private long totalElements;
}
