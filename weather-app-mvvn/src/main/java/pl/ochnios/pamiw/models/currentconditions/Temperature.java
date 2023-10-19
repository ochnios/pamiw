package pl.ochnios.pamiw.models.currentconditions;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Temperature {
    @JsonProperty("Metric")
    public Metric metric;
}
