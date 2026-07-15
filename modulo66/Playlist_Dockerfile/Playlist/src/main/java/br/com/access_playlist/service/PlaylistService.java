package br.com.access_playlist.service;

import org.springframework.stereotype.Service;

import br.com.access_playlist.dto.PlaylistDto;

@Service
public class PlaylistService {

    public PlaylistDto getSpotifyPlaylist() {

        return new PlaylistDto(
                "Dev Focus Playlist",
                "Rock's music for deep work and coding sessions.",
                "https://open.spotify.com/playlist/0Ka9lnk0gpqBtr8pQr7k5n?si=bd185ceb086a47c8");
    }
}