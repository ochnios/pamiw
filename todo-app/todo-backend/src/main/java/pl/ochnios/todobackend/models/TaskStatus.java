package pl.ochnios.todobackend.models;

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
