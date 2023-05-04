package com.openlist.app.Utils;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Component
public class UrlCleaner {
    public String extractApplePlaylistId(String url) {
        // Extract using regex
        Pattern pattern = Pattern.compile("pl\\.u-([a-zA-Z0-9]+)");
        Matcher matcher = pattern.matcher(url);

        if(matcher.find()) {
            return matcher.group(0);
        } else {
            throw new IllegalArgumentException("Invalid Apple Music playlist URL");
        }
    }
}
