package pl.ochnios.pamiw.models.auxiliary.currentconditions;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Metric {
    @JsonProperty("Value")
    public double value;
}
