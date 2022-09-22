package com.talentql.backendassessment.controller;

import com.talentql.backendassessment.dto.BasicResponseDTO;
import com.talentql.backendassessment.service.DobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/howold")
@RequiredArgsConstructor
@Slf4j
public class DobController extends Controller{

    private final DobService dobService;

    @GetMapping()
    public BasicResponseDTO calculateAge(@RequestParam(name = "dob") String dateOfBirth){
        log.info("Date of birth format {}", dateOfBirth);
        return responseWithUpdatedHttpStatus(dobService.calculateAge(dateOfBirth));
    }

}
