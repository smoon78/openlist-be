package com.example.springboot;

import org.springframework.beans.factory.annotation.Value; 
import org.springframework.context.annotation.Configuration; 

@Configuration
public class SpotifyConfig {
    @Value("${spotify.clientId}")
    private String clientId;

    @Value("${spotify.clientSecret}")
    private String clientSecret;

    @Value("${spotify.redirectUri}")
    private String redirectUri;

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getRedirectUri() {
        return redirectUri;
    }
}
