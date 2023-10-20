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
        if(name == null) {
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
}
