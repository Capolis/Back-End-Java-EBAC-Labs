package br.com.streaming.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.streaming.domain.dto.MovieResponseDTO;
import br.com.streaming.domain.entities.Movie;
import br.com.streaming.domain.entities.User;
import br.com.streaming.domain.enums.PlanType;
import br.com.streaming.domain.enums.ProfileType;
import br.com.streaming.repository.MovieRepository;
import br.com.streaming.repository.UserRepository;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    public MovieService(MovieRepository movieRepository, UserRepository userRepository) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    public List<MovieResponseDTO> getAvailableMoviesForUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        List<Movie> allMovies = movieRepository.findAll();

        return allMovies.stream()
                .filter(movie -> hasAgePermission(user, movie))
                .filter(movie -> hasPlanPermission(user, movie))
                .map(movie -> mapToDTO(user, movie))
                .collect(Collectors.toList());
    }

    private boolean hasAgePermission(User user, Movie movie) {
        if (user.getProfileType() == ProfileType.CHILD && movie.getAgeRating() > 12) {
            return false;
        }
        if (user.getProfileType() == ProfileType.TEEN && movie.getAgeRating() == 18) {
            return user.getAllowsAdultContent(); // Só assiste +18 se tiver permissão
        }
        return true; // Adultos assistem tudo
    }

    private boolean hasPlanPermission(User user, Movie movie) {
        if (user.getPlanType() == PlanType.BASIC && movie.getMinimumPlan() != PlanType.BASIC) {
            return false; // Basic só vê Basic
        }
        if (user.getPlanType() == PlanType.STANDARD && movie.getMinimumPlan() == PlanType.PREMIUM) {
            return false; // Standard não vê Premium
        }
        return true; // Premium vê tudo
    }

    private MovieResponseDTO mapToDTO(User user, Movie movie) {
        // Oculta o minimumPlan da resposta se o usuário for PREMIUM
        PlanType displayPlan = (user.getPlanType() == PlanType.PREMIUM) ? null : movie.getMinimumPlan();
        
        return new MovieResponseDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getAgeRating(),
                displayPlan,
                movie.getGenres()
        );
    }
}