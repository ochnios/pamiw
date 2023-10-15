package pl.ochnios.pamiw.models.location;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdministrativeArea {
    @JsonProperty("ID")
    public String iD;

    @JsonProperty("LocalizedName")
    public String localizedName;
}
