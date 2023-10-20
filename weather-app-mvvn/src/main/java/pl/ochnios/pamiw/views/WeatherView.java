package pl.ochnios.pamiw.views;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

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
    }

    @FXML
    protected void onSearchButtonClick() {

    }

    @FXML
    protected void onResultsListClick() {

    }
}