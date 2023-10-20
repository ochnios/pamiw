package pl.ochnios.pamiw.models.auxiliary.dailyforecast;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DailyForecast {
    @JsonProperty("Temperature")
    public Temperature temperature;
    @JsonProperty("Day")
    public Day day;
    @JsonProperty("Night")
    public Night night;
}
