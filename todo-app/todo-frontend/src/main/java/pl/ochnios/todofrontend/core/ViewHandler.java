package pl.ochnios.todofrontend.core;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import pl.ochnios.todofrontend.TodoApplication;
import pl.ochnios.todofrontend.views.CategoryView;
import pl.ochnios.todofrontend.views.MainView;
import pl.ochnios.todofrontend.views.TaskView;

import java.io.IOException;

public class ViewHandler {
    private static final String MAIN_VIEW_FILE = "main-view.fxml";
    private static final int SCENE_WIDTH = 600;
    private static final int SCENE_HEIGHT = 400;
    private final ViewModelFactory vmf;

    public ViewHandler(ViewModelFactory vmf) {
        this.vmf = vmf;
    }

    public Scene getAppScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TodoApplication.class.getResource(MAIN_VIEW_FILE));
        Scene scene = new Scene(fxmlLoader.load(), SCENE_WIDTH, SCENE_HEIGHT);

        MainView mainView = fxmlLoader.getController();

        CategoryView categoryView = mainView.getCategoryView();
        categoryView.init(vmf.getCategoryViewModel());

        TaskView taskView = mainView.getTaskView();
        taskView.init(vmf.getTaskViewModel());

        return scene;
    }
}
