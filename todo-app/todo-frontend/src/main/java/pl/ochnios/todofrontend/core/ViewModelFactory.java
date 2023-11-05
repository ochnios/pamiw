package pl.ochnios.todofrontend.core;

import pl.ochnios.todofrontend.viewmodels.CategoryViewModel;

public class ViewModelFactory {
    private final CategoryViewModel categoryViewModel;

    public ViewModelFactory(ModelFactory modelFactory) {
        categoryViewModel = new CategoryViewModel(modelFactory.getCategoryModel());
    }

    public CategoryViewModel getCategoryViewModel() {
        return categoryViewModel;
    }
}
