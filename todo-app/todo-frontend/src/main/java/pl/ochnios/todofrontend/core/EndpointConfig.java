package pl.ochnios.todofrontend.core;

import lombok.Getter;
import lombok.Setter;
import pl.ochnios.todofrontend.models.services.shared.ObjectMapperUtil;

import java.io.File;
import java.io.IOException;

@Getter
@Setter
class Endpoints {
    private String baseUrl;
    private String tasks;
    private String users;
    private String categories;
}

public class EndpointConfig {
    private static Endpoints endpoints;

    static {
        try {
            endpoints = ObjectMapperUtil.getObjectMapper()
                    .readValue(new File("src/main/resources/pl/ochnios/todofrontend/endpoints.json"), Endpoints.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getTasksAddr() {
        return endpoints.getBaseUrl() + endpoints.getTasks();
    }

    public static String getUsersAddr() {
        return endpoints.getBaseUrl() + endpoints.getUsers();
    }

    public static String getCategoriesAddr() {
        return endpoints.getBaseUrl() + endpoints.getCategories();
    }
}
