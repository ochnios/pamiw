package pl.ochnios.pamiw.models.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministrativeArea {

    @JsonProperty("LocalizedName")
    private String localizedName;
}
