package com.sky;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("amazon")
public class AmazonProperties {
    public String getAssoiateId() {
        return assoiateId;
    }

    public void setAssoiateId(String assoiateId) {
        this.assoiateId = assoiateId;
    }

    private String assoiateId;



}
