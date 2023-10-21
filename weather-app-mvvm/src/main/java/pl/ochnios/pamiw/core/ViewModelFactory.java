package pl.ochnios.pamiw.core;

import lombok.Getter;
import pl.ochnios.pamiw.views.WeatherViewModel;

@Getter
public class ViewModelFactory {
    private final WeatherViewModel weatherViewModel;

    public ViewModelFactory(ModelFactory modelFactory) {
        weatherViewModel = new WeatherViewModel(modelFactory.getWeatherModel());
    }
}
