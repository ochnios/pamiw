package pl.ochnios.pamiw.models.location;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Country {
    @JsonProperty("LocalizedName")
    public String localizedName;
}
