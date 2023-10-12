package pl.ochnios.pamiw;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class WeatherController {
    private final WeatherService weatherService;

    @FXML
    private TextField searchField;

    @FXML
    private ListView<String> resultsList;

    public  WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @FXML
    protected void onSearchButtonClick() {
        String searchPhrase = searchField.getText();
        String result = weatherService.getLocation(searchPhrase);

        resultsList.getItems().add(result);
    }
}