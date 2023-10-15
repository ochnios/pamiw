package pl.ochnios.pamiw.models.dailyforecast;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;

public class DailyForecast {
    @JsonProperty("Date")
    public Date date;
    @JsonProperty("EpochDate")
    public int epochDate;
    @JsonProperty("Temperature")
    public Temperature temperature;
    @JsonProperty("Day")
    public Day day;
    @JsonProperty("Night")
    public Night night;
    @JsonProperty("Sources")
    public ArrayList<String> sources;
    @JsonProperty("MobileLink")
    public String mobileLink;
    @JsonProperty("Link")
    public String link;
}
