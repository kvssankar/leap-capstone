package com.fidelity;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@SpringBootApplication
////tell Spring Boot where to scan for annotated components
//@ComponentScan(basePackages={"com.fidelity.integration", "com.fidelity.services", "com.fidelity.controllers"})
//////tell MyBatis where to scan for mapping interface files
//@MapperScan(basePackages="com.fidelity.integration.mapper")
public class TradeHubApplication {
    public static void main(String[] args) {
        SpringApplication.run(TradeHubApplication.class, args);
    }
    @Bean
    @Scope("prototype")
    Logger createLogger(InjectionPoint ip) {
        Class<?> classThatWantsALogger = ip.getField().getDeclaringClass();
        return LoggerFactory.getLogger(classThatWantsALogger);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
    @Bean
    public RestTemplate buildHttpClient() {
        return new RestTemplate();
    }

}