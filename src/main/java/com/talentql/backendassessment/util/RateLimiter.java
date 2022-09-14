package com.talentql.backendassessment.util;

import io.github.bucket4j.*;
import io.github.bucket4j.distributed.proxy.ProxyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class RateLimiter {

    private final ProxyManager<String> buckets;

    @Value("${limit.value}")
    private int limit;

    public Bucket resolveBucket(String key) {
        Supplier<BucketConfiguration> configSupplier = getConfigSupplierForUser();
        return buckets.builder().build(key, configSupplier);
    }

    private Supplier<BucketConfiguration> getConfigSupplierForUser() {
        Refill refill = Refill.intervally(3, Duration.ofSeconds(1));
        Bandwidth bandwidthLimit = Bandwidth.classic(3,refill);
        return () -> (BucketConfiguration.builder()
                .addLimit(bandwidthLimit)
                .build());
    }
}
