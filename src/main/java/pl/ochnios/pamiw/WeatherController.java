package pl.ochnios.pamiw;

import pl.ochnios.pamiw.services.CurrentConditionsService;
import pl.ochnios.pamiw.services.LocationService;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;

public class WeatherController {
    private final LocationService locationService;
    private final CurrentConditionsService currentConditionsService;

    @FXML
    private TextField searchField;

    @FXML
    private ListView<String> resultsList;

    public WeatherController(LocationService locationService, CurrentConditionsService currentConditionsService) {
        this.locationService = locationService;
        this.currentConditionsService = currentConditionsService;
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