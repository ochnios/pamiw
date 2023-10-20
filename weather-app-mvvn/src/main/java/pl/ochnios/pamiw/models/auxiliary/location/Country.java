package pl.ochnios.pamiw.models.auxiliary.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Country {
    @JsonProperty("LocalizedName")
    private String localizedName;
}
