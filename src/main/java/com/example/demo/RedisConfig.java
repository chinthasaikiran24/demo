package com.example.demo;

import java.time.Duration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;

@Configuration
@EnableCaching
public class RedisConfig {

    @Bean
    public RedisCacheManager cacheManager() {

        RedisCacheConfiguration config =
                RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(Duration.ofMinutes(10));

        return RedisCacheManager.builder()
                .cacheDefaults(config)
                .build();
    }
}

/*✅ Redis basics
✅ @Cacheable
✅ @CachePut
✅ @CacheEvict
✅ TTL
✅ Cache consistency
✅ DTO caching
✅ JSON serialization
✅ Cache key design
✅ Multiple-cache invalidation
✅ Redis + Microservices
✅ Cache Stampede*/