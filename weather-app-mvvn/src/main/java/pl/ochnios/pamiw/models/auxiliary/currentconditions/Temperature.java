package pl.ochnios.pamiw.models.auxiliary.currentconditions;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Temperature {
    @JsonProperty("Metric")
    public Metric metric;
}
