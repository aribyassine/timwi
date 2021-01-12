package com.marvel.app;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "marvel")
@Data
public class ApiConfiguration {
    private String apiUrl;
    private String publicKey;
    private String privateKey;

    public String getTimestamp() {
        return String.valueOf(System.currentTimeMillis());
    }
}
