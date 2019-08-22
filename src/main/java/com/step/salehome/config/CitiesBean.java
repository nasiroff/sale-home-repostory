package com.step.salehome.config;

import com.step.salehome.model.City;
import com.step.salehome.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CitiesBean {
    @Autowired
    private PostService postService;

    @Bean
    public List<City> getAllCities() {
        return postService.getAllCity();
    }
}
