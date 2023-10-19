package pl.ochnios.pamiw.models.dailyforecast;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Headline {
    @JsonProperty("Text")
    public String text;
}
