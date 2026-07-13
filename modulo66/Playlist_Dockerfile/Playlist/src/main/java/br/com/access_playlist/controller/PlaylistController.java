package br.com.access_playlist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.access_playlist.dto.PlaylistDto;
import br.com.access_playlist.service.PlaylistService;

@RestController
@RequestMapping("/api/v1/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping("/spotify")
    public ResponseEntity<PlaylistDto> getSpotifyPlaylist() {
        PlaylistDto playlist = playlistService.getSpotifyPlaylist();
        return ResponseEntity.ok(playlist);
    }
}