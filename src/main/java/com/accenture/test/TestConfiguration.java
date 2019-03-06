package com.accenture.test;

import org.assertj.core.api.SoftAssertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.databind.ObjectMapper;

@ComponentScan
@EnableAutoConfiguration
@SpringBootConfiguration
@PropertySource("classpath:application.properties")
public class TestConfiguration {

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

    @Bean
    @Scope("prototype")
    public Logger logger(InjectionPoint injectionPoint) {
        return LoggerFactory.getLogger(injectionPoint.getMethodParameter()
                                                     .getContainingClass());
    }

    @Bean
    public SoftAssertions softAssertions() {
        return new SoftAssertions();
    }

}
