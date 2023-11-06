package pl.ochnios.todofrontend;

import com.google.inject.AbstractModule;
import pl.ochnios.todofrontend.models.services.CategoryService;
import pl.ochnios.todofrontend.models.services.TaskService;

public class TodoModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(TaskService.class);
        bind(CategoryService.class);
    }
}
