package com.talentql.backendassessment.util;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


@Component
@AllArgsConstructor
public class RateLimiter {

    private final Map<String, Bucket> bucket = new HashMap<>();

    public Bucket resolveBucket(String key) {
        return bucket.computeIfAbsent(key, this::setUpBucket);
    }

    private Bucket setUpBucket(String s) {
        return Bucket4j.builder()
                .addLimit(Bandwidth.classic(3, Refill.intervally(3, Duration.ofSeconds(1)))).build();
    }
}
