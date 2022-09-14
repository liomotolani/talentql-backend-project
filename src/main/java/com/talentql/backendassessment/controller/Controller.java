package com.talentql.backendassessment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talentql.backendassessment.dto.StandardResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class Controller {
    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpServletRequest request;

    <T extends StandardResponseDTO> T responseWithUpdatedHttpStatus(T responseDTO) {
        switch (responseDTO.getStatus()) {
            case SUCCESS:
                response.setStatus(HttpStatus.OK.value());
                break;
            case TOO_MANY_REQUESTS:
                response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                break;
            default:
                response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
        return responseDTO;
    }

}
