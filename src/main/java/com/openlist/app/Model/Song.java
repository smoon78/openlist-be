package com.openlist.app.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Song {
    private String id;
    private String title;
    private String artist;
    private String album;
    private String artworkUrl;
}
