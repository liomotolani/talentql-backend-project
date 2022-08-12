package com.talentql.backendassessment.config;


import io.github.bucket4j.distributed.proxy.ProxyManager;
import io.github.bucket4j.grid.jcache.JCacheProxyManager;
import org.redisson.config.Config;
import org.redisson.jcache.configuration.RedissonConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


import javax.cache.CacheManager;
import javax.cache.Caching;

@Configuration
public class RedisConfig {

    @Bean
    public Config redisConfiguration() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
        return config;
    }

    @Bean
    public CacheManager cacheManager(Config config) {
        CacheManager cacheManager = Caching.getCachingProviders().iterator().next().getCacheManager();
        cacheManager.createCache("cache", RedissonConfiguration.fromConfig(config));
        return cacheManager;
    }

    @Bean
    ProxyManager<String> proxyManager(CacheManager cacheManager) {
        return new JCacheProxyManager<>(cacheManager.getCache("cache"));
    }

}
