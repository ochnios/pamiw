package pl.ochnios.pamiw;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.ochnios.pamiw.core.WeatherViewHandler;

import java.io.IOException;

public class WeatherApplication extends Application {
    public static final String STAGE_TITLE = "Weather application";

    @Override
    public void start(Stage stage) throws IOException {
        WeatherViewHandler viewHandler = new WeatherViewHandler();
        Scene scene = viewHandler.getWeatherScene();

        stage.setTitle(STAGE_TITLE);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}