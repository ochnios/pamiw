package pl.ochnios.pamiw.views;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class WeatherView {
    @FXML
    private TextField searchField;

    @FXML
    private ListView<String> resultsList;

    @FXML
    private Label currentConditions;

    @FXML
    private Label drivingIndex;

    @FXML
    private Label next5HoursForecast;

    @FXML
    private Label tomorrowForecast;

    private WeatherViewModel weatherViewModel;

    public void init(WeatherViewModel weatherViewModel) {
        this.weatherViewModel = weatherViewModel;

        resultsList.itemsProperty().bind(weatherViewModel.getLocations());
        currentConditions.textProperty().bind(weatherViewModel.getCurrentConditions());
        drivingIndex.textProperty().bind(weatherViewModel.getDrivingIndex());
        next5HoursForecast.textProperty().bind(weatherViewModel.getNext5HoursForecast());
        tomorrowForecast.textProperty().bind(weatherViewModel.getTomorrowForecast());
    }

    @FXML
    protected void onSearchButtonClick() {
        try {
            weatherViewModel.searchLocations(searchField.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onResultsListClick() {
        int cityIndex = resultsList.getSelectionModel().getSelectedIndex();
        try {
            weatherViewModel.updateWeather(cityIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}