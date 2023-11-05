package pl.ochnios.todofrontend;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.ochnios.todofrontend.core.ModelFactory;
import pl.ochnios.todofrontend.core.ViewHandler;
import pl.ochnios.todofrontend.core.ViewModelFactory;

import java.io.IOException;

public class TodoApplication extends Application {
    public static final String STAGE_TITLE = "Todo Application";

    @Override
    public void start(Stage stage) throws IOException {
        ModelFactory mf = new ModelFactory();
        ViewModelFactory vmf = new ViewModelFactory(mf);
        ViewHandler vh = new ViewHandler(vmf);

        Scene scene = vh.getAppScene();

        stage.setTitle(STAGE_TITLE);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}