package pl.ochnios.pamiw.views;

import lombok.Getter;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import pl.ochnios.pamiw.core.Consts;
import pl.ochnios.pamiw.models.WeatherModel;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

@Getter
public class WeatherViewModel {
    private final WeatherModel weatherModel;
    private final ListProperty<String> locations;
    private final StringProperty currentConditions;
    private final StringProperty drivingIndex;
    private final StringProperty next5HoursForecast;
    private final StringProperty tomorrowForecast;

    public WeatherViewModel(WeatherModel weatherModel) {
        this.weatherModel = weatherModel;

        locations = new SimpleListProperty<>(FXCollections.observableList(new ArrayList<>()));
        currentConditions = new SimpleStringProperty(Consts.EMPTY_STRING);
        drivingIndex = new SimpleStringProperty(Consts.EMPTY_STRING);
        next5HoursForecast = new SimpleStringProperty(Consts.EMPTY_STRING);
        tomorrowForecast = new SimpleStringProperty(Consts.EMPTY_STRING);

        weatherModel.addListener("locations", this::updateSearchResults);
        weatherModel.addListener("currentConditions", this::updateCurrentConditions);
        weatherModel.addListener("drivingIndex", this::updateDrivingIndex);
        weatherModel.addListener("next5HoursForecast", this::updateNext5HoursForecast);
        weatherModel.addListener("tomorrowForecast", this::updateTomorrowForecast);
    }

    public void searchLocations(String searchPhrase) throws Exception {
        if (searchPhrase == null || searchPhrase.isEmpty())
            return;

        weatherModel.fetchLocations(searchPhrase);
    }

    public void updateWeather(int cityIndex) throws Exception {
        if (cityIndex == -1)
            return;

        weatherModel.fetchCurrentConditions(cityIndex);
        weatherModel.fetchDrivingIndex(cityIndex);
        weatherModel.fetchNext5HoursForecast(cityIndex);
        weatherModel.fetchTomorrowForecast(cityIndex);
    }

    private void updateSearchResults(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            locations.setValue(FXCollections.observableList((List<String>) evt.getNewValue()));
        });
    }

    private void updateCurrentConditions(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            currentConditions.setValue((String) evt.getNewValue());
        });
    }

    private void updateDrivingIndex(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            drivingIndex.setValue((String) evt.getNewValue());
        });
    }

    private void updateNext5HoursForecast(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            next5HoursForecast.setValue((String) evt.getNewValue());
        });
    }

    private void updateTomorrowForecast(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            tomorrowForecast.setValue((String) evt.getNewValue());
        });
    }
}
