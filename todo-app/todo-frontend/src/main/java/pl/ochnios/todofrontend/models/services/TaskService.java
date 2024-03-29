package pl.ochnios.todofrontend.models.services;

import pl.ochnios.todofrontend.core.EndpointConfig;
import pl.ochnios.todofrontend.models.dtos.TaskDto;
import pl.ochnios.todofrontend.models.services.shared.HttpClientUtil;
import pl.ochnios.todofrontend.models.services.shared.ObjectMapperUtil;

import java.net.URI;
import java.util.List;

public class TaskService {
    public List<TaskDto> getAll() throws Exception {
        String resultJson = HttpClientUtil.makeGetRequest(new URI(EndpointConfig.getTasksAddr()));
        TaskDto[] result = ObjectMapperUtil.getObjectMapper().readValue(resultJson, TaskDto[].class);

        return List.of(result);
    }

    public List<TaskDto> getById(String id) throws Exception {
        String resultJson = HttpClientUtil.makeGetRequest(new URI(EndpointConfig.getTasksAddr() + "/" + id));
        TaskDto result = ObjectMapperUtil.getObjectMapper().readValue(resultJson, TaskDto.class);

        return List.of(result);
    }

    public List<TaskDto> getByTitle(String title) {
        throw new UnsupportedOperationException();
    }

    public List<TaskDto> create(String title, String description, String status) throws Exception {
        TaskDto task = TaskDto.builder().title(title).description(description).status(status).build();
        String taskJson = ObjectMapperUtil.getObjectMapper().writeValueAsString(task);

        String resultJson = HttpClientUtil.makePostRequest(new URI(EndpointConfig.getTasksAddr()), taskJson);
        TaskDto result = ObjectMapperUtil.getObjectMapper().readValue(resultJson, TaskDto.class);

        return List.of(result);
    }

    public List<TaskDto> update(String id, String title, String description, String status) throws Exception {
        TaskDto task = TaskDto.builder().title(title).description(description).status(status).build();
        String taskJson = ObjectMapperUtil.getObjectMapper().writeValueAsString(task);

        String resultJson = HttpClientUtil.makePatchRequest(new URI(EndpointConfig.getTasksAddr() + "/" + id), taskJson);
        TaskDto result = ObjectMapperUtil.getObjectMapper().readValue(resultJson, TaskDto.class);

        return List.of(result);
    }

    public void delete(String id) throws Exception {
        String result = HttpClientUtil.makeDeleteRequest(new URI(EndpointConfig.getTasksAddr() + "/" + id));
    }
}
