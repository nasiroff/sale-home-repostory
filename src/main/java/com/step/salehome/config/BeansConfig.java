package com.step.salehome.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BeansConfig {
    @Value("${file.upload.path.win}")
    private String imagePathWin;
    @Value("${file.upload.path.mac}")
    private String imagePathMac;

    @Bean
    public String getImagePath() {
        if (System.getProperty("os.name").toLowerCase().contains("win")) return imagePathWin;
        else return imagePathMac;
    }
}
