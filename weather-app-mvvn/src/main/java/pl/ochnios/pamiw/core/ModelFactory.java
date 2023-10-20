package pl.ochnios.pamiw.core;

import com.google.inject.Guice;
import com.google.inject.Injector;
import pl.ochnios.pamiw.WeatherModule;
import pl.ochnios.pamiw.models.WeatherModel;

public class ModelFactory {

    private final Injector injector;
    private WeatherModel weatherModel;

    public ModelFactory() {
        injector = Guice.createInjector(new WeatherModule());
    }

    public WeatherModel getWeatherModel() {
        if(weatherModel == null)
            weatherModel = injector.getInstance(WeatherModel.class);

        return weatherModel;
    }
}
