package com.openlist.app.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.openlist.app.Utils.AppleMusicTokenGenerator;
import com.openlist.app.Utils.UrlCleaner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

@Service 
public class AppleMusicService {
    @Autowired
    private AppleMusicTokenGenerator appleMusicTokenGenerator;
    @Autowired
    private UrlCleaner urlCleaner;
    
    public List<String> getSongsFromPlaylist(String playlistUrl) {
        // Use the Apple Music API to fetch playlist information
        // You will need the generated token from the AppleMusicTokenGenerator
        String token = appleMusicTokenGenerator.generateToken();
        String playlistId = urlCleaner.extractApplePlaylistId(playlistUrl);

        // Use the token to authorize the request to Apple Music API
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        RestTemplate restTemplate = new RestTemplate();

        // TODO: Future enhancement here could be to make the storefront mutable to support outside of the US
        String storefront = "us";
        String appleMusicApiUrl = "https://api.music.apple.com/v1/catalog/" + storefront + "/playlists/" + playlistId + "/tracks";

        ResponseEntity<String> response = restTemplate.exchange(appleMusicApiUrl, HttpMethod.GET, entity, String.class);

        // Now, we clean the data and return only the ISRC codes
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> isrcCodes = new ArrayList<>();

        try {
            JsonNode rootNode = objectMapper.readTree(response.getBody());
            JsonNode dataArrayNode= rootNode.get("data");

            if(dataArrayNode.isArray()) {
                for( JsonNode songNode : dataArrayNode ) {
                    JsonNode attributesNode = songNode.get("attributes");
                    String isrc = attributesNode.get("isrc").asText();
                    isrcCodes.add(isrc);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        System.out.println(isrcCodes); // Uncomment to see the playlist data returned

        // This is just a placeholder, we should replace it with actual implementation
        return isrcCodes;
    }
}
