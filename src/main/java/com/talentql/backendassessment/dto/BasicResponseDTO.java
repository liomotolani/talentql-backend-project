package com.talentql.backendassessment.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasicResponseDTO {

    private Status status;

    private Object age;

    private String message;

    public BasicResponseDTO() {
    }

    public BasicResponseDTO(Status status) {
        this.status = status;
    }

    public BasicResponseDTO(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public BasicResponseDTO(Status status, Object age) {
        this.status = status;
        this.age = age;
    }

    public BasicResponseDTO(Status status, Object age, String message) {
        this.status = status;
        this.age = age;
        this.message = message;
    }
}
