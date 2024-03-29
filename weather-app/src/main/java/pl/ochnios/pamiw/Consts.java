package pl.ochnios.pamiw;

public class Consts {
    public static final String APIKEY = "${accuweather.key}";
    public static final String LANGUAGE = "en-US";
    public static final String METRIC = "true";
    public static final String BASE_URL = "http://dataservice.accuweather.com/"; // http://localhost:8080/
    public static final String LOCATIONS_EP = BASE_URL + "locations/v1/cities/autocomplete";
    public static final String CURRENT_CONDITIONS_EP = BASE_URL + "currentconditions/v1";
    public static final String INDICES_EP = BASE_URL + "indices/v1/daily/1day";
    public static final String HOURLY_12_HOUR_EP = BASE_URL + "forecasts/v1/hourly/12hour";
    public static final String DAILY_1_DAY_EP = BASE_URL + "forecasts/v1/daily/1day";
    public static final String NOT_FOUND_TEXT = "not found";
    public static final String STH_WENT_WRONG_TEXT = "something went wrong";
    public static final String CELCIUS_SYMBOL = "°C";
    public static final char NEWLINE_CHARACTER = '\n';
    public static final char TAB_CHARACTER = '\t';
    public static final char SPACE_CHARACTER = ' ';
    public static final int HTTP_OK = 200;
    public static final String INDEX_DETAILS = "true";
    public static final int DRIVING_INDEX_KEY = 40;
}
