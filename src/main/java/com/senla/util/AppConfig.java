package com.senla.util;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "com.senla")
@PropertySource(value = "classpath:config.properties")
public class AppConfig {

}
