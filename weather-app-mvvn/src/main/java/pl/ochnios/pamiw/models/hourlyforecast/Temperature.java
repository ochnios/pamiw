package pl.ochnios.pamiw.models.hourlyforecast;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Temperature {
    @JsonProperty("Value")
    public double value;
}
