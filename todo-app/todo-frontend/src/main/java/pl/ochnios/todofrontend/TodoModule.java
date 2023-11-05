package pl.ochnios.todofrontend;

import com.google.inject.AbstractModule;
import pl.ochnios.todofrontend.models.services.CategoryService;

public class TodoModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(CategoryService.class);
    }
}
