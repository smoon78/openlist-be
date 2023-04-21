// src/main/java/com/example/demo/WebConfig.java
package com.example.springboot;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
          .allowedOrigins("http://localhost:5173") // TODO: Update when ready to add to production
          .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
          .allowCredentials(true)
          .maxAge(3600);
    }
}
