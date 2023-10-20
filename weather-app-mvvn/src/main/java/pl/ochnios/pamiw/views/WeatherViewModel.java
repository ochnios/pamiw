package pl.ochnios.pamiw.views;

import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import pl.ochnios.pamiw.models.WeatherModel;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

public class WeatherViewModel {
    private final WeatherModel weatherModel;
    private final ListProperty<String> locations;

    public WeatherViewModel(WeatherModel weatherModel) {
        this.weatherModel = weatherModel;

        locations = new SimpleListProperty<>(FXCollections.observableList(new ArrayList<>()));

        weatherModel.addListener("locations", this::updateSearchResults);
    }

    public void searchLocations(String searchPhrase) throws Exception{
        if(searchPhrase == null || searchPhrase.isEmpty())
            return;

        weatherModel.fetchLocations(searchPhrase);
    }

    private void updateSearchResults(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            locations.setValue(FXCollections.observableList((List<String>) evt.getNewValue()));
        });
    }

    public ListProperty<String> getLocationsProperty() {
        return locations;
    }

}
