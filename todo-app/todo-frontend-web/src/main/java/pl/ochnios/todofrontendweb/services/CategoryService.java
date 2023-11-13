package pl.ochnios.todofrontendweb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.ochnios.todofrontendweb.dtos.CategoryDto;
import pl.ochnios.todofrontendweb.dtos.ResultsPage;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CategoryService {

    private final WebClient webClient;

    @Autowired
    public CategoryService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080/api/categories").build();
    }

    public Mono<CategoryDto> getCategory(int id) {
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(CategoryDto.class);
    }

    public Mono<ResultsPage<CategoryDto>> getPaginatedCategories(int pageNumber, int pageSize, String sortField, String sortDirection) {
        return webClient.get()
                .uri("?pageNumber={pageNumber}&pageSize={pageSize}&sortField={sortField}&sortDirection={sortDirection}",
                        pageNumber, pageSize, sortField, sortDirection)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ResultsPage<CategoryDto>>() {
                });
    }

    public Mono<List<CategoryDto>> getALlCategories() {
        return webClient.get()
                .uri("/all")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<CategoryDto>>() {
                });
    }

    public Mono<CategoryDto> createCategory(CategoryDto category) {
        return webClient.post()
                .uri("")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(category), CategoryDto.class)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CategoryDto>() {
                });
    }

    public void deleteCategory(int id) {
        webClient.delete()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CategoryDto>() {
                })
                .subscribe();
    }

    public Mono<CategoryDto> updateCategory(int id, CategoryDto category) {
        return webClient.patch()
                .uri("/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(category), CategoryDto.class)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CategoryDto>() {
                });
    }
}
