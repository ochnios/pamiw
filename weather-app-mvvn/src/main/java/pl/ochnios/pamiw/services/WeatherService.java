package pl.ochnios.pamiw.services;

import pl.ochnios.pamiw.core.Consts;
import pl.ochnios.pamiw.models.currentconditions.CurrentConditions;
import pl.ochnios.pamiw.models.currentconditions.DailyIndex;
import pl.ochnios.pamiw.models.dailyforecast.DailyForecast;
import pl.ochnios.pamiw.models.dailyforecast.TomorrowForecast;
import pl.ochnios.pamiw.models.hourlyforecast.HourlyForecast;
import pl.ochnios.pamiw.services.shared.HttpClientUtil;
import pl.ochnios.pamiw.services.shared.ObjectMapperUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;

public class WeatherService {
    private static final String CURRENT_CONDITIONS_EP = Consts.BASE_URL + "currentconditions/v1";
    private static final String INDICES_EP = Consts.BASE_URL + "indices/v1/daily/1day";
    private static final String HOURLY_12_HOUR_EP = Consts.BASE_URL + "forecasts/v1/hourly/12hour";
    private static final String DAILY_1_DAY_EP = Consts.BASE_URL + "forecasts/v1/daily/1day";
    private static final String CELSIUS_SYMBOL = "°C";
    private static final String INDEX_DETAILS = "true";
    private static final int DRIVING_INDEX_KEY = 40;

    private CurrentConditions[] currentConditions;
    private DailyIndex[] currentDrivingIndex;
    private HourlyForecast[] forecast12h;
    private TomorrowForecast tomorrowForecast;

    public String getCurrentConditions(String cityKey) throws Exception {
        URI currentConditionsURI = createWeatherForecastURI(CURRENT_CONDITIONS_EP, cityKey);
        String currentConditionsJson = HttpClientUtil.makeHttpRequest(currentConditionsURI);

        currentConditions = ObjectMapperUtil.getObjectMapper().readValue(currentConditionsJson, CurrentConditions[].class);

        return prepareCurrentConditionsText();
    }

    public String getCurrentDrivingIndex(String cityKey) throws Exception {
        URI currentDrivingIndexURI = createDailyIndexURI(INDICES_EP, cityKey, DRIVING_INDEX_KEY);
        String currentDrivingIndexJson = HttpClientUtil.makeHttpRequest(currentDrivingIndexURI);

        currentDrivingIndex = ObjectMapperUtil.getObjectMapper().readValue(currentDrivingIndexJson, DailyIndex[].class);

        return prepareCurrentDrivingIndexText();
    }

    public String getForecastForNext5Hours(String cityKey) throws Exception {
        URI forecast12hURI = createWeatherForecastURI(HOURLY_12_HOUR_EP, cityKey);
        String forecast12hJson = HttpClientUtil.makeHttpRequest(forecast12hURI);

        forecast12h = ObjectMapperUtil.getObjectMapper().readValue(forecast12hJson, HourlyForecast[].class);

        return prepareForecastForNext5HoursText();
    }

    public String getForecastForTomorrow(String cityKey) throws Exception {
        URI tomorrowForecastURI = createWeatherForecastURI(DAILY_1_DAY_EP, cityKey);
        String tomorrowForecastJson = HttpClientUtil.makeHttpRequest(tomorrowForecastURI);

        tomorrowForecast = ObjectMapperUtil.getObjectMapper().readValue(tomorrowForecastJson, TomorrowForecast.class);

        return prepareForecastForTomorrow();
    }

    private URI createWeatherForecastURI(String endpoint, String cityKey) throws URISyntaxException {
        String encodedCityKey = URLEncoder.encode(cityKey, StandardCharsets.UTF_8);
        String uri = endpoint + "/" + encodedCityKey
                + "?language=" + Consts.LANGUAGE
                + "&metric=" + Consts.METRIC
                + "&apikey=" + Consts.APIKEY;

        return new URI(uri);
    }

    private URI createDailyIndexURI(String endpoint, String cityKey, int index) throws  URISyntaxException {
        String encodedCityKey = URLEncoder.encode(cityKey, StandardCharsets.UTF_8);
        String uri = endpoint + "/" + encodedCityKey + "/" + index
                + "?language=" + Consts.LANGUAGE
                + "&details=" + INDEX_DETAILS
                + "&apikey=" + Consts.APIKEY;

        return new URI(uri);
    }

    private String prepareCurrentConditionsText() {
        if (currentConditions.length == 1) {
            return currentConditions[0].weatherText + ", "
                    + currentConditions[0].temperature.metric.value + CELSIUS_SYMBOL;
        } else {
            return Consts.STH_WENT_WRONG_TEXT;
        }
    }

    private String prepareCurrentDrivingIndexText() {
        if (currentDrivingIndex.length == 1) {
            return currentDrivingIndex[0].text;
        } else {
            return Consts.STH_WENT_WRONG_TEXT;
        }
    }

    private String prepareForecastForNext5HoursText() {
        int forecast12hLength = forecast12h.length;
        if (forecast12hLength == 12) {
            Calendar calendar = Calendar.getInstance();
            StringBuilder forecastBuilder = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                HourlyForecast fh = forecast12h[i];
                calendar.setTime(fh.dateTime);
                forecastBuilder
                        .append(calendar.get(Calendar.HOUR_OF_DAY)).append(":00").append(Consts.TAB_CHARACTER)
                        .append(fh.temperature.value).append(CELSIUS_SYMBOL).append(Consts.TAB_CHARACTER)
                        .append(fh.iconPhrase).append(Consts.NEWLINE_CHARACTER);
            }
            return forecastBuilder.toString();
        } else {
            return Consts.STH_WENT_WRONG_TEXT;
        }
    }

    private String prepareForecastForTomorrow() {
        if (tomorrowForecast != null && !tomorrowForecast.dailyForecasts.isEmpty()) {
            DailyForecast tomorrow = tomorrowForecast.dailyForecasts.get(0);
            return tomorrowForecast.headline.text + Consts.NEWLINE_CHARACTER
                    + "Day: " + tomorrow.temperature.maximum.value + CELSIUS_SYMBOL + Consts.SPACE_CHARACTER
                    + tomorrow.day.iconPhrase.toLowerCase() + Consts.NEWLINE_CHARACTER
                    + "Night: " + tomorrow.temperature.minimum.value + CELSIUS_SYMBOL + Consts.SPACE_CHARACTER
                    + tomorrow.night.iconPhrase.toLowerCase();
        } else {
            return Consts.STH_WENT_WRONG_TEXT;
        }
    }
}
