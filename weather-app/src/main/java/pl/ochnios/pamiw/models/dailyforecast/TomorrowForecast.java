package pl.ochnios.pamiw.models.dailyforecast;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class TomorrowForecast {
    @JsonProperty("Headline")
    public Headline headline;
    @JsonProperty("DailyForecasts")
    public ArrayList<DailyForecast> dailyForecasts;
}

