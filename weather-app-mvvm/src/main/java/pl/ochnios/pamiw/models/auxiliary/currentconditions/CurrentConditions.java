package pl.ochnios.pamiw.models.auxiliary.currentconditions;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrentConditions {
    @JsonProperty("WeatherText")
    public String weatherText;
    @JsonProperty("Temperature")
    public Temperature temperature;
}


