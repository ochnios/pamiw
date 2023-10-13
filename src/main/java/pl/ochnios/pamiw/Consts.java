package pl.ochnios.pamiw;

public class Consts {
    public static final String APIKEY = "${accuweather.apikey}";
    public static final String LANGUAGE = "en-US";
    public static final String BASE_URL = "http://localhost:8080/";
    public static final String LOCATIONS_EP = BASE_URL + "locations/v1/cities/autocomplete";
    public static final String CURRENT_CONDITIONS_EP = BASE_URL + "currentconditions/v1";
    public static final String INDICES_EP = BASE_URL + "indices/v1/daily/1day/";
    public static final String HOURLY_1_HOUR_EP = BASE_URL + "forecasts/v1/hourly/1hour";
    public static final String DAILY_1_DAY_EP = BASE_URL + "forecasts/v1/daily/1day";
    public static final String DAILY_5_DAY_EP = BASE_URL + "forecasts/v1/daily/5day";
}
