package com.openlist.app.config;

import org.springframework.beans.factory.annotation.Value; 
import org.springframework.context.annotation.Configuration; 

@Configuration
public class AppleMusicConfig {
    @Value("${applemusic.keyId}")
    private String appleMusicKeyId;

    @Value("${applemusic.teamId}")
    private String appleMusicTeamId;

    @Value("${applemusic.privateKey}")
    private String appleMusicPrivateKey;

    public String getKeyId() {
        return appleMusicKeyId;
    }

    public String getTeamId() {
        return appleMusicTeamId;
    }

    public String getPrivateKey() {
        return appleMusicPrivateKey;
    }
}
