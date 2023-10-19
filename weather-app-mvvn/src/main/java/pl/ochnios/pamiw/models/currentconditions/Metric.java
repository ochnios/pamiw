package pl.ochnios.pamiw.models.currentconditions;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Metric {
    @JsonProperty("Value")
    public double value;
}
