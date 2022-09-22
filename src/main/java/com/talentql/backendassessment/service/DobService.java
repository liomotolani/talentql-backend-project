package com.talentql.backendassessment.service;

import com.talentql.backendassessment.dto.BasicResponseDTO;
import com.talentql.backendassessment.dto.Status;
import com.talentql.backendassessment.util.RateLimiter;
import io.github.bucket4j.Bucket;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class DobService {

    private final RateLimiter rateLimiter;


    public BasicResponseDTO calculateAge(String dateOfBirth) {
        long dob = dateOfBirth.equals("undefined") ? 0 : Long.parseLong(dateOfBirth);
        Date currentDate = new Date();
        long currentEpoch = currentDate.getTime();
        Bucket bucket = rateLimiter.resolveBucket(dateOfBirth);
        if(bucket.tryConsume(3)) {
            long age = getDateOfBirth(currentEpoch) - getDateOfBirth(dob);
            return new BasicResponseDTO(Status.SUCCESS,age);
        }
        return new BasicResponseDTO(Status.TOO_MANY_REQUESTS,"Rate limit exceeded, retry again in 1 second");
    }

    private long getDateOfBirth(long epoch) {
        Timestamp timestamp = new Timestamp(epoch);
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        LocalDate localDate = localDateTime.toLocalDate();
        return localDate.getYear();
    }
}
