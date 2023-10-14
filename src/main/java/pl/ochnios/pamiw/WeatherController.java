package pl.ochnios.pamiw;

import pl.ochnios.pamiw.services.WeatherService;
import pl.ochnios.pamiw.services.LocationService;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class WeatherController {
    private final LocationService locationService;
    private final WeatherService weatherService;

    @FXML
    private TextField searchField;

    @FXML
    private ListView<String> resultsList;

    public WeatherController(LocationService locationService, WeatherService weatherService) {
        this.locationService = locationService;
        this.weatherService = weatherService;
    }

    @FXML
    protected void onSearchButtonClick() {
        String searchPhrase = searchField.getText();
        try {
            String[] searchResults = locationService.searchLocations(searchPhrase);
            resultsList.getItems().clear();
            resultsList.getItems().addAll(searchResults);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onResultsListClick() {
        int cityIndex = resultsList.getSelectionModel().getSelectedIndex();
        if(cityIndex != -1) {
            String cityKey = locationService.getCityKeyForIndex(cityIndex);
            System.out.println(cityKey);
        }
    }
}