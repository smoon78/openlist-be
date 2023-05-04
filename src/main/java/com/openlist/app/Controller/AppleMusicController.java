package com.openlist.app.Controller;

import com.openlist.app.Service.AppleMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
public class AppleMusicController {
    @Autowired
    private AppleMusicService appleMusicService;

    @PostMapping("/api/apple/playlist")
    public List<String> getPlaylistSongs(@RequestBody Map<String, String> payload) {
        String playlistId = payload.get("playlistId");
        return appleMusicService.getSongsFromPlaylist(playlistId);
    }
}
