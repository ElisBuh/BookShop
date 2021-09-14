package util;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.web.WebAppConfiguration;

@Configuration
@ComponentScan(basePackages = "com.senla" )
//@WebAppConfiguration
//@ComponentScan(basePackages = "com.senla.dao")
public class Config {
}
