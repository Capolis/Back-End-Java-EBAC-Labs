package br.com.streaming.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.streaming.domain.dto.MovieResponseDTO;
import br.com.streaming.service.MovieService;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/available/{userId}")
    public ResponseEntity<List<MovieResponseDTO>> getAvailableMovies(@PathVariable Long userId) {
        List<MovieResponseDTO> movies = movieService.getAvailableMoviesForUser(userId);
        return ResponseEntity.ok(movies);
    }
}