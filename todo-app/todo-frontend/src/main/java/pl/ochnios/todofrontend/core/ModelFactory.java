package pl.ochnios.todofrontend.core;

import com.google.inject.Guice;
import com.google.inject.Injector;
import pl.ochnios.todofrontend.TodoModule;
import pl.ochnios.todofrontend.models.CategoryModel;
import pl.ochnios.todofrontend.models.TaskModel;

public class ModelFactory {

    private final Injector injector;
    private TaskModel taskModel;
    private CategoryModel categoryModel;

    public ModelFactory() {
        injector = Guice.createInjector(new TodoModule());
    }

    public TaskModel getTaskModel() {
        if (taskModel == null)
            taskModel = injector.getInstance(TaskModel.class);
        return taskModel;
    }

    public CategoryModel getCategoryModel() {
        if (categoryModel == null)
            categoryModel = injector.getInstance(CategoryModel.class);
        return categoryModel;
    }
}
