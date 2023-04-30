package com.openlist.app.controller;

import com.openlist.app.service.AppleMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.openlist.app.model.Song;

import java.util.List;
import java.util.Map;

@RestController
public class AppleMusicController {
    @Autowired
    private AppleMusicService appleMusicService;

    @PostMapping("/api/apple/playlist")
    public List<Song> getPlaylistSongs(@RequestBody Map<String, String> payload) {
        String playlistId = payload.get("playlistId");
        return appleMusicService.getSongsFromPlaylist(playlistId);
    }
}
