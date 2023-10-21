package pl.ochnios.pamiw.models;

import com.google.inject.Inject;
import pl.ochnios.pamiw.models.services.LocationService;
import pl.ochnios.pamiw.models.services.WeatherService;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class WeatherModel {
    private List<String> locations;
    private String currentConditions;
    private String drivingIndex;
    private String next5HoursForecast;
    private String tomorrowForecast;
    private final LocationService locationService;
    private final WeatherService weatherService;
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    @Inject
    public WeatherModel(LocationService locationService, WeatherService weatherService) {
        this.locationService = locationService;
        this.weatherService = weatherService;
    }

    public void addListener(String name, PropertyChangeListener listener) {
        if (name == null) {
            changeSupport.addPropertyChangeListener(listener);
        } else {
            changeSupport.addPropertyChangeListener(name, listener);
        }
    }

    public void fetchLocations(String searchPhrase) throws Exception {
        List<String> results = locationService.searchLocations(searchPhrase);
        changeSupport.firePropertyChange("locations", locations, results);
        locations = results;
    }

    public void fetchCurrentConditions(int cityIndex) throws Exception {
        String cityKey = locationService.getCityKeyForIndex(cityIndex);
        String result = weatherService.getCurrentConditions(cityKey);
        changeSupport.firePropertyChange("currentConditions", currentConditions, result);
        currentConditions = result;
    }

    public void fetchDrivingIndex(int cityIndex) throws Exception {
        String cityKey = locationService.getCityKeyForIndex(cityIndex);
        String result = weatherService.getCurrentDrivingIndex(cityKey);
        changeSupport.firePropertyChange("drivingIndex", drivingIndex, result);
        drivingIndex = result;
    }

    public void fetchNext5HoursForecast(int cityIndex) throws Exception {
        String cityKey = locationService.getCityKeyForIndex(cityIndex);
        String result = weatherService.getForecastForNext5Hours(cityKey);
        changeSupport.firePropertyChange("next5HoursForecast", next5HoursForecast, result);
        next5HoursForecast = result;
    }

    public void fetchTomorrowForecast(int cityIndex) throws Exception {
        String cityKey = locationService.getCityKeyForIndex(cityIndex);
        String result = weatherService.getForecastForTomorrow(cityKey);
        changeSupport.firePropertyChange("tomorrowForecast", tomorrowForecast, result);
        tomorrowForecast = result;
    }
}
