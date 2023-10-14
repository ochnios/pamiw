package pl.ochnios.pamiw;

public class Consts {
    public static final String APIKEY = "asdf";
    public static final String LANGUAGE = "en-US";
    public static final String METRIC = "true";
    public static final String BASE_URL = "http://localhost:8080/"; // http://dataservice.accuweather.com
    public static final String LOCATIONS_EP = BASE_URL + "locations/v1/cities/autocomplete";
    public static final String CURRENT_CONDITIONS_EP = BASE_URL + "currentconditions/v1";
    public static final String INDICES_EP = BASE_URL + "indices/v1/daily/1day/";
    public static final String HOURLY_12_HOUR_EP = BASE_URL + "forecasts/v1/hourly/12hour";
    public static final String DAILY_1_DAY_EP = BASE_URL + "forecasts/v1/daily/1day";
    public static final String DAILY_5_DAY_EP = BASE_URL + "forecasts/v1/daily/5day";
    public static final String NOT_FOUND_TEXT = "not found";
    public static final String STH_WENT_WRONG_TEXT = "something went wrong";
    public static final String CELCIUS_SYMBOL = "°C";
    public static final int HTTP_OK = 200;
}
