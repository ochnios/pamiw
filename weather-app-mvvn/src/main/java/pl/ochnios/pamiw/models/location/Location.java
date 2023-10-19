package pl.ochnios.pamiw.models.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.ochnios.pamiw.models.location.AdministrativeArea;
import pl.ochnios.pamiw.models.location.Country;

public class Location{
    @JsonProperty("Key")
    public String key;
    @JsonProperty("LocalizedName")
    public String localizedName;
    @JsonProperty("Country")
    public Country country;
    @JsonProperty("AdministrativeArea")
    public AdministrativeArea administrativeArea;
}