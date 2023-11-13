package pl.ochnios.todofrontendweb.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TaskStatus {
    @JsonProperty
    New,
    @JsonProperty
    InProgress,
    @JsonProperty
    Completed,
    @JsonProperty
    Cancelled
}
