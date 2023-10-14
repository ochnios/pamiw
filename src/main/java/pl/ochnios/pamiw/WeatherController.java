package pl.ochnios.pamiw;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import pl.ochnios.pamiw.services.LocationService;

public class WeatherController {
    private final LocationService locationService;

    @FXML
    private TextField searchField;

    @FXML
    private ListView<String> resultsList;

    public WeatherController(LocationService locationService) {
        this.locationService = locationService;
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
}