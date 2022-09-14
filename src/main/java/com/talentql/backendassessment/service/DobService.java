package com.talentql.backendassessment.service;

import com.talentql.backendassessment.dto.BasicResponseDTO;
import com.talentql.backendassessment.dto.Status;
import com.talentql.backendassessment.util.RateLimiter;
import io.github.bucket4j.Bucket;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class DobService {

    private final RateLimiter rateLimiter;


    public BasicResponseDTO calculateAge(String dateOfBirth) {
        if(dateOfBirth == null || dateOfBirth.equals("null")){
            return new BasicResponseDTO(Status.BAD_REQUEST,"Invalid value");
        }
        String newDateOfBirth = getDateOfBirth(dateOfBirth);
        LocalDate dob = LocalDate.parse(newDateOfBirth);
        LocalDate currentDate = LocalDate.now();
        Bucket bucket = rateLimiter.resolveBucket(newDateOfBirth);

        if((dob != null) && (currentDate != null)) {
            if(bucket.tryConsume(3)) {
                long age = ChronoUnit.YEARS.between(dob,currentDate);
                return new BasicResponseDTO(Status.SUCCESS,age);
            }
            return new BasicResponseDTO(Status.TOO_MANY_REQUESTS,"Rate limit exceeded, retry again in 1 second");
        }
        return new BasicResponseDTO(Status.BAD_REQUEST);
    }

    private String getDateOfBirth(String dateOfBirth) {
        String datePattern = "\\d{4}-\\d{1,2}-\\d{1,2}";
        Pattern pattern = Pattern.compile(datePattern);
        Matcher matcher = pattern.matcher(dateOfBirth);
        while(matcher.find()){
            dateOfBirth = matcher.group();
        }
        return dateOfBirth;
    }
}
