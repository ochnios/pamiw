package pl.ochnios.pamiw.models.hourlyforecast;

 import com.fasterxml.jackson.annotation.JsonProperty;

 import java.util.Date;

public class HourlyForecast{
    @JsonProperty("DateTime")
    public Date dateTime;
    @JsonProperty("EpochDateTime")
    public int epochDateTime;
    @JsonProperty("WeatherIcon")
    public int weatherIcon;
    @JsonProperty("IconPhrase")
    public String iconPhrase;
    @JsonProperty("HasPrecipitation")
    public boolean hasPrecipitation;
    @JsonProperty("PrecipitationType")
    public String precipitationType;
    @JsonProperty("PrecipitationIntensity")
    public String precipitationIntensity;
    @JsonProperty("IsDaylight")
    public boolean isDaylight;
    @JsonProperty("Temperature")
    public Temperature temperature;
    @JsonProperty("PrecipitationProbability")
    public int precipitationProbability;
    @JsonProperty("MobileLink")
    public String mobileLink;
    @JsonProperty("Link")
    public String link;
}


