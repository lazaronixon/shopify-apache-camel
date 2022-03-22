package com.koombea.shopifyintegrator.processors;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.ConcurrentMap;
import org.apache.camel.Header;
import org.mapdb.DB;
import org.springframework.beans.factory.annotation.Autowired;


public class ConfigurationProcessor {
    
    private static final int DAYS_TO_FETCH = 45;
    
    @Autowired private DB dbMakerTemplate;
    
    public LocalDateTime getProductsRefreshedAt() {
        return (LocalDateTime) configuration().getOrDefault("productsRefreshedAt", defaultLastRefresh());
    }

    public void setProductsRefreshedAt(@Header("CamelTimerFiredTime") Date camelTimerFiredTime ) {
        configuration().put("productsRefreshedAt", toLocalDateTime(camelTimerFiredTime));
    }
    
    private LocalDateTime defaultLastRefresh() {
        return LocalDateTime.now().minusDays(DAYS_TO_FETCH);
    }
    
    private LocalDateTime toLocalDateTime(Date dateToConvert) {
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }   
    
    private ConcurrentMap configuration() {
        return dbMakerTemplate.hashMap("configuration").createOrOpen();
    }
    
}
