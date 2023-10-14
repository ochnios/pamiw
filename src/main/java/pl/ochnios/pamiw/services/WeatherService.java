package pl.ochnios.pamiw.services;

import pl.ochnios.pamiw.Consts;
import pl.ochnios.pamiw.models.currentconditions.CurrentConditions;
import pl.ochnios.pamiw.models.hourlyforecast.HourlyForecast;
import pl.ochnios.pamiw.services.shared.HttpClientUtil;
import pl.ochnios.pamiw.services.shared.ObjectMapperUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;

public class WeatherService {
    private CurrentConditions[] currentConditions;
    private HourlyForecast[] forecast12h;

    public String getCurrentConditions(String cityKey) throws Exception {
        URI currentConditionsURI = createWeatherForecastURI(Consts.CURRENT_CONDITIONS_EP, cityKey);
        String currentConditionsJson = HttpClientUtil.makeHttpRequest(currentConditionsURI);

        currentConditions = ObjectMapperUtil.getObjectMapper().readValue(currentConditionsJson, CurrentConditions[].class);

        return getCurrentConditionsText(currentConditions);
    }

    public String getForecastForNext5Hours(String cityKey) throws Exception {
        URI forecast12hURI = createWeatherForecastURI(Consts.HOURLY_12_HOUR_EP, cityKey);
        String forecast12hJson = HttpClientUtil.makeHttpRequest(forecast12hURI);

        forecast12h = ObjectMapperUtil.getObjectMapper().readValue(forecast12hJson, HourlyForecast[].class);

        return getForecastForNext5HoursText();
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
        if (conditionsLength == 1) {
            return currentConditions[0].weatherText + ", "
                    + currentConditions[0].temperature.metric.value + Consts.CELCIUS_SYMBOL;
        } else {
            return Consts.STH_WENT_WRONG_TEXT;
        }
    }

    private String getForecastForNext5HoursText() {
        int forecast12hLength = forecast12h.length;
        if (forecast12hLength == 12) {
            Calendar calendar = Calendar.getInstance();
            StringBuilder forecastBuilder = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                HourlyForecast fh = forecast12h[i];
                calendar.setTime(fh.dateTime);
                forecastBuilder
                        .append(calendar.get(Calendar.HOUR_OF_DAY)).append(":00\t")
                        .append(fh.temperature.value).append(Consts.CELCIUS_SYMBOL).append('\t')
                        .append(fh.iconPhrase).append("\n");
            }
            return forecastBuilder.toString();
        } else {
            return Consts.STH_WENT_WRONG_TEXT;
        }
    }
}
