package com.senla.util.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Neil Alishev
 */
@Configuration
@ComponentScan("com.senla")
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;

    @Autowired
    public WebConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

}

