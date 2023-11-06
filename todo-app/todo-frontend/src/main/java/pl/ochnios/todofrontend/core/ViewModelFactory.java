package pl.ochnios.todofrontend.core;

import lombok.Getter;
import pl.ochnios.todofrontend.viewmodels.CategoryViewModel;
import pl.ochnios.todofrontend.viewmodels.TaskViewModel;

@Getter
public class ViewModelFactory {
    private final TaskViewModel taskViewModel;
    private final CategoryViewModel categoryViewModel;

    public ViewModelFactory(ModelFactory modelFactory) {
        taskViewModel = new TaskViewModel(modelFactory.getTaskModel());
        categoryViewModel = new CategoryViewModel(modelFactory.getCategoryModel());
    }
}
