package com.talentql.backendassessment.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasicResponseDTO extends StandardResponseDTO {


    private Object age;

    private String message;

    public BasicResponseDTO() {
    }

    public BasicResponseDTO(Status status) {
        this.status = status;
    }

    public BasicResponseDTO(Status status, String message) {
        super(status);
        this.message = message;
    }

    public BasicResponseDTO(Status status, Object age) {
        super(status);
        this.age = age;
    }


}
