package com.koombea.shopifyintegrator;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShopifyintegratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopifyintegratorApplication.class, args);
    }

    @Bean
    public DB dbMakerTemplate() {
        return DBMaker.fileDB("database.db").make();
    }

}
