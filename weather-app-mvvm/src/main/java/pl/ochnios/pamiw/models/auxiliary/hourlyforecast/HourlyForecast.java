package pl.ochnios.pamiw.models.auxiliary.hourlyforecast;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class HourlyForecast{
    @JsonProperty("DateTime")
    public Date dateTime;
    @JsonProperty("IconPhrase")
    public String iconPhrase;
    @JsonProperty("Temperature")
    public Temperature temperature;
}


