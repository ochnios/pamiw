package pl.ochnios.pamiw.models.currentconditions;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class CurrentConditions {
    @JsonProperty("WeatherText")
    public String weatherText;
    @JsonProperty("Temperature")
    public Temperature temperature;
}


