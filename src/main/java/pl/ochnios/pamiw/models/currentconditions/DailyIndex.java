package pl.ochnios.pamiw.models.currentconditions;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class DailyIndex{
    @JsonProperty("Name")
    public String name;
    @JsonProperty("ID")
    public int iD;
    @JsonProperty("Ascending")
    public boolean ascending;
    @JsonProperty("LocalDateTime")
    public Date localDateTime;
    @JsonProperty("EpochDateTime")
    public int epochDateTime;
    @JsonProperty("Value")
    public double value;
    @JsonProperty("Category")
    public String category;
    @JsonProperty("CategoryValue")
    public int categoryValue;
    @JsonProperty("Text")
    public String text;
    @JsonProperty("MobileLink")
    public String mobileLink;
    @JsonProperty("Link")
    public String link;
}
