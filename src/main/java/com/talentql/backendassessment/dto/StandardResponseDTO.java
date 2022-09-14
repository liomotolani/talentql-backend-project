package com.talentql.backendassessment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class StandardResponseDTO implements Serializable {


    @JsonProperty
    protected Status status;

    public StandardResponseDTO() {
    }

    public StandardResponseDTO(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
