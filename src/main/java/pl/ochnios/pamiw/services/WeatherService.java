package pl.ochnios.pamiw.services;

import pl.ochnios.pamiw.Consts;
import pl.ochnios.pamiw.models.currentconditions.CurrentConditions;
import pl.ochnios.pamiw.services.shared.HttpClientUtil;
import pl.ochnios.pamiw.services.shared.ObjectMapperUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WeatherService {
    private CurrentConditions[] currentConditions;

    public String getCurrentConditions(String cityKey) throws Exception {
        URI currentConditionsURI = createWeatherForecastURI(Consts.CURRENT_CONDITIONS_EP, cityKey);
        String currentConditionsJson = HttpClientUtil.makeHttpRequest(currentConditionsURI);

        currentConditions = ObjectMapperUtil.getObjectMapper().readValue(currentConditionsJson, CurrentConditions[].class);

        return getCurrentConditionsText(currentConditions);
    }

    private URI createWeatherForecastURI(String endpoint, String cityKey) throws URISyntaxException {
        String encodedCityKey = URLEncoder.encode(cityKey, StandardCharsets.UTF_8);
        String uri = endpoint + "/" + encodedCityKey
                + "?language=" + Consts.LANGUAGE
                + "&metric=" + Consts.METRIC
                + "&apikey=" + Consts.APIKEY;

        return new URI(uri);
    }

    private String getCurrentConditionsText(CurrentConditions[] conditions) {
        int conditionsLength = conditions.length;
        if (conditionsLength > 0) {
            return currentConditions[0].weatherText + ", "
                    + currentConditions[0].temperature.metric.value + Consts.CELCIUS_SYMBOL;
        } else {
            return Consts.NOT_FOUND_TEXT;
        }
    }
}
