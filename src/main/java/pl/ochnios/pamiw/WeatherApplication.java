package pl.ochnios.pamiw;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.ochnios.pamiw.services.WeatherService;
import pl.ochnios.pamiw.services.LocationService;

import java.io.IOException;
import java.lang.reflect.Constructor;

public class WeatherApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WeatherApplication.class.getResource("weather-view.fxml"));

        // manual dependency injection
        LocationService locationService = new LocationService();
        WeatherService weatherService = new WeatherService();
        fxmlLoader.setControllerFactory((Class<?> type) -> {
            try {
                // look for constructor taking my services as parameters
                for (Constructor<?> c : type.getConstructors()) {
                    if (c.getParameterCount() == 2) {
                        Class<?>[] parameterTypes = c.getParameterTypes();
                        if (parameterTypes[0] == LocationService.class
                                && parameterTypes[1] == WeatherService.class) {
                            return c.newInstance(locationService, weatherService);
                        }
                    }
                }
                // didn't find appropriate constructor, just use default constructor:
                return type.getDeclaredConstructor().newInstance();
            } catch (Exception exc) {
                throw new RuntimeException(exc);
            }
        });

        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Weather application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}