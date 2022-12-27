package com.example.demo;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableCaching
@PropertySource("/cache.properties")
@EnableScheduling
public class AppConfig {

  @CacheEvict(allEntries = true, value = {"${cache.keys.categoriesList}"})
  @Scheduled(fixedDelay = 15000, initialDelay = 5000)
  public void reportCacheEvict() {
    System.out.println("Cache cleared by key");
  }
}