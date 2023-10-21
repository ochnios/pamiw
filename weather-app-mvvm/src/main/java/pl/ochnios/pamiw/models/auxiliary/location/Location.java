package pl.ochnios.pamiw.models.auxiliary.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Location {
    @JsonProperty("Key")
    private String key;
    @JsonProperty("LocalizedName")
    private String localizedName;
    @JsonProperty("Country")
    private Country country;
    @JsonProperty("AdministrativeArea")
    private AdministrativeArea administrativeArea;
}