package com.raise.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.raise.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.raise.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.raise.domain.User.class.getName());
            createCache(cm, com.raise.domain.Authority.class.getName());
            createCache(cm, com.raise.domain.User.class.getName() + ".authorities");
            createCache(cm, com.raise.domain.PersistentToken.class.getName());
            createCache(cm, com.raise.domain.User.class.getName() + ".persistentTokens");
            createCache(cm, com.raise.domain.RunInstance.class.getName());
            createCache(cm, com.raise.domain.Resource.class.getName());
            createCache(cm, com.raise.domain.Region.class.getName());
            createCache(cm, com.raise.domain.RiskGroup.class.getName());
            createCache(cm, com.raise.domain.Supply.class.getName());
            createCache(cm, com.raise.domain.Demand.class.getName());
            createCache(cm, com.raise.domain.Odmatrix.class.getName());
            createCache(cm, com.raise.domain.Allocation.class.getName());
            createCache(cm, com.raise.domain.InfectionRate.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

}
