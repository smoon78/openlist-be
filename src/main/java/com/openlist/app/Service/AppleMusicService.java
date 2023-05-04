package com.openlist.app.service; 

import com.openlist.app.util.AppleMusicTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.openlist.app.model.Song;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.ArrayList;

@Service 
public class AppleMusicService {
    @Autowired
    private AppleMusicTokenGenerator appleMusicTokenGenerator;
    
    public List<Song> getSongsFromPlaylist(String playlistId) {
        // Use the Apple Music API to fetch playlist information
        // You will need the generated token from the AppleMusicTokenGenerator
        String token = appleMusicTokenGenerator.generateToken();

        // Use the token to authorize the request to Apple Music API
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(playlistId, HttpMethod.GET, entity, String.class);

        // System.out.println(response.getBody()); // Uncomment to see the playlist data returned

        // This is just a placeholder, we should replace it with actual implementation
        return new ArrayList<Song>();
    }
}
