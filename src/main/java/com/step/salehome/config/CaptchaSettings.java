package com.step.salehome.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "google.recaptcha")
public class CaptchaSettings {
    private String url;
    private String key;
    private String secret;
}