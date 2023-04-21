package com.example.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.example.springboot.SpotifyConfig;

import java.util.Map;

@RestController
public class SpotifyAuthController {
  @Autowired
  private SpotifyConfig spotifyConfig;

  @PostMapping("/api/auth/spotify")
  public ResponseEntity<?> authenticateSpotify(@RequestBody Map<String, String> payload) {
    String code = payload.get("authCode");

    RestTemplate restTemplate = new RestTemplate(); 

    HttpHeaders headers = new HttpHeaders(); 
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();

    requestBody.add("grant_type", "authorization_code");
    requestBody.add("code", code);
    requestBody.add("redirect_uri", spotifyConfig.getRedirectUri());
    requestBody.add("client_id", spotifyConfig.getClientId());
    requestBody.add("client_secret", spotifyConfig.getClientSecret());

    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestBody, headers);

    ResponseEntity<Map> response = restTemplate.postForEntity(
      "https://accounts.spotify.com/api/token",
      request,
      Map.class
    );

    // TODO: Store access_token and refresh_token in the user's session

    return ResponseEntity.ok(response.getBody());

  }
}