package pl.ochnios.pamiw.core;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import pl.ochnios.pamiw.WeatherApplication;
import pl.ochnios.pamiw.views.WeatherView;

import java.io.IOException;

public class ViewHandler {
    private static final String VIEW_FILE = "weather-view.fxml";
    private static final int SCENE_WIDTH = 600;
    private static final int SCENE_HEIGHT = 400;
    private final ViewModelFactory vmf;

    public ViewHandler(ViewModelFactory vmf) {
        this.vmf = vmf;
    }

    public Scene getWeatherScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WeatherApplication.class.getResource(VIEW_FILE));
        Scene scene = new Scene(fxmlLoader.load(), SCENE_WIDTH, SCENE_HEIGHT);

        WeatherView weatherView = fxmlLoader.getController();
        weatherView.init(vmf.getWeatherViewModel());

        return scene;
    }
}
