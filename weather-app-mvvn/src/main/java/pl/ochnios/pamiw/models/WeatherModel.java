package pl.ochnios.pamiw.models;

import lombok.Getter;
import lombok.Setter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

@Getter
@Setter
public class WeatherModel implements Model {
    private ArrayList<String> locations;
    private String currentConditions;
    private String drivingIndex;
    private String next5HoursForecast;
    private String tomorrowForecast;

    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    @Override
    public void addListener(String name, PropertyChangeListener listener) {
        if(name == null) {
            changeSupport.addPropertyChangeListener(listener);
        } else {
            changeSupport.addPropertyChangeListener(name, listener);
        }
    }
}
