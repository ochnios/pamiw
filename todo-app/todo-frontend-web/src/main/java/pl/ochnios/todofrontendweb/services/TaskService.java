package pl.ochnios.todofrontendweb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.ochnios.todofrontendweb.conf.Endpoints;
import pl.ochnios.todofrontendweb.dtos.CategoryDto;
import pl.ochnios.todofrontendweb.dtos.ResultsPage;
import pl.ochnios.todofrontendweb.dtos.TaskDto;
import pl.ochnios.todofrontendweb.dtos.UserDto;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class TaskService {
    private final WebClient webClient;
    private final UserService userService;
    private final CategoryService categoryService;

    @Autowired
    public TaskService(WebClient.Builder webClientBuilder, UserService userService, CategoryService categoryService, Endpoints endpoints) {
        this.webClient = webClientBuilder.baseUrl(endpoints.getBaseUrl() + endpoints.getTasksPath()).build();
        this.userService = userService;
        this.categoryService = categoryService;
    }

    public Mono<TaskDto> getTask(int id) {
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(TaskDto.class);
    }

    public Mono<ResultsPage<TaskDto>> getPaginatedTasks(int pageNumber, int pageSize, String sortField, String sortDirection) {
        return webClient.get()
                .uri("?pageNumber={pageNumber}&pageSize={pageSize}&sortField={sortField}&sortDirection={sortDirection}",
                        pageNumber, pageSize, sortField, sortDirection)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ResultsPage<TaskDto>>() {
                });
    }

    public Mono<List<TaskDto>> getALlTasks() {
        return webClient.get()
                .uri("/all")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<TaskDto>>() {
                });
    }

    public Mono<TaskDto> createTask(TaskDto task) {
        return webClient.post()
                .uri("")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(task), TaskDto.class)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<TaskDto>() {
                });
    }

    public void deleteTask(int id) {
        webClient.delete()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<TaskDto>() {
                })
                .subscribe();
    }

    public Mono<TaskDto> updateTask(int id, TaskDto task) {
        return webClient.patch()
                .uri("/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(task), TaskDto.class)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<TaskDto>() {
                });
    }

    public Mono<List<UserDto>> getAllUsers() {
        return userService.getALlUsers();
    }

    public Mono<List<CategoryDto>> getAllCategories() {
        return categoryService.getALlCategories();
    }
}
