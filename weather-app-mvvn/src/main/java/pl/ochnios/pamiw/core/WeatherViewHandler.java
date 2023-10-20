package pl.ochnios.pamiw.core;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import pl.ochnios.pamiw.WeatherApplication;
import pl.ochnios.pamiw.models.services.LocationService;
import pl.ochnios.pamiw.models.services.WeatherService;

import java.io.IOException;
import java.lang.reflect.Constructor;

public class WeatherViewHandler {
    public static final String VIEW_FILE = "weather-view.fxml";
    public static final int SCENE_WIDTH = 600;
    public static final int SCENE_HEIGHT = 400;

    public Scene getWeatherScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WeatherApplication.class.getResource(VIEW_FILE));
        injectDependencies(fxmlLoader);
        return new Scene(fxmlLoader.load(), SCENE_WIDTH, SCENE_HEIGHT);
    }

    private void injectDependencies(FXMLLoader fxmlLoader) {
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
    }
}
