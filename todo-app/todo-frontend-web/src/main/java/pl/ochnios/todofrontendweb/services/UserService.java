package pl.ochnios.todofrontendweb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.ochnios.todofrontendweb.conf.Endpoints;
import pl.ochnios.todofrontendweb.dtos.ResultsPage;
import pl.ochnios.todofrontendweb.dtos.UserDto;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class UserService {

    private final WebClient webClient;

    @Autowired
    public UserService(WebClient.Builder webClientBuilder, Endpoints endpoints) {
        this.webClient = webClientBuilder.baseUrl(endpoints.getBaseUrl() + endpoints.getUsersPath()).build();
    }

    public Mono<UserDto> getUser(int id) {
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(UserDto.class);
    }

    public Mono<ResultsPage<UserDto>> getPaginatedUsers(int pageNumber, int pageSize, String sortField, String sortDirection) {
        return webClient.get()
                .uri("?pageNumber={pageNumber}&pageSize={pageSize}&sortField={sortField}&sortDirection={sortDirection}",
                        pageNumber, pageSize, sortField, sortDirection)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ResultsPage<UserDto>>() {
                });
    }

    public Mono<List<UserDto>> getALlUsers() {
        return webClient.get()
                .uri("/all")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<UserDto>>() {
                });
    }

    public Mono<UserDto> createUser(UserDto user) {
        return webClient.post()
                .uri("")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), UserDto.class)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<UserDto>() {
                });
    }

    public void deleteUser(int id) {
        webClient.delete()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<UserDto>() {
                })
                .subscribe();
    }

    public Mono<UserDto> updateUser(int id, UserDto user) {
        return webClient.patch()
                .uri("/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), UserDto.class)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<UserDto>() {
                });
    }
}
