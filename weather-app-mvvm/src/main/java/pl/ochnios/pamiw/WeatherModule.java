package pl.ochnios.pamiw;

import com.google.inject.AbstractModule;
import pl.ochnios.pamiw.models.services.LocationService;
import pl.ochnios.pamiw.models.services.WeatherService;

public class WeatherModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(LocationService.class);
        bind(WeatherService.class);
    }
}