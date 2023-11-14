package pl.ochnios.todofrontendweb.conf;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("endpoints")
@Getter
@Setter
public class Endpoints {
    private String baseUrl;
    private String tasksPath;
    private String categoriesPath;
    private String usersPath;
}
