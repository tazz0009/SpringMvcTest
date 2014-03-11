package com.tistory.tazz009.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tistory.tazz009.controller.IndexController;

@Configuration
public class WebMvcContextConfiguration {

	@Bean
    public IndexController indexController() {
        return new IndexController();
    }
	
}
