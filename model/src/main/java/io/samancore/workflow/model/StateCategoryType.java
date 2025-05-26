package io.samancore.workflow.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StateCategoryType {
    INITIAL("INITIAL"),
    PENDING("PENDING"),
    IN_PROGRESS("IN_PROGRESS"),
    COMPLETED("COMPLETED"),
    EXCLUDED("EXCLUDED");

    private final String value;

    StateCategoryType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
