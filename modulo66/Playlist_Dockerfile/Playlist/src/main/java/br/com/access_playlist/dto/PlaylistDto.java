package br.com.access_playlist.dto;

public class PlaylistDto {
    private String name;
    private String description;
    private String spotifyUrl;

    public PlaylistDto(String name, String description, String spotifyUrl) {
        this.name = name;
        this.description = description;
        this.spotifyUrl = spotifyUrl;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpotifyUrl() {
        return spotifyUrl;
    }

    public void setSpotifyUrl(String spotifyUrl) {
        this.spotifyUrl = spotifyUrl;
    }

}