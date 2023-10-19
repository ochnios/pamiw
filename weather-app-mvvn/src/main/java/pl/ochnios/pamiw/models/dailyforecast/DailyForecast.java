package pl.ochnios.pamiw.models.dailyforecast;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;

public class DailyForecast {
    @JsonProperty("Temperature")
    public Temperature temperature;
    @JsonProperty("Day")
    public Day day;
    @JsonProperty("Night")
    public Night night;
}
