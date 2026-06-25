package br.com.streaming.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.streaming.domain.dto.WatchHistoryResponseDTO;
import br.com.streaming.domain.dto.WatchRequestDTO;
import br.com.streaming.domain.entities.Movie;
import br.com.streaming.domain.entities.User;
import br.com.streaming.domain.enums.PlanType;
import br.com.streaming.domain.enums.ProfileType;
import br.com.streaming.repository.MovieRepository;
import br.com.streaming.repository.UserRepository;
import br.com.streaming.repository.WatchHistoryRepository;

@Service
public class WatchService {

    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final WatchHistoryRepository watchHistoryRepository;

    public WatchService(UserRepository userRepository, MovieRepository movieRepository, WatchHistoryRepository watchHistoryRepository) {
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.watchHistoryRepository = watchHistoryRepository;
    }

    public String watchMovie(WatchRequestDTO request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        Movie movie = movieRepository.findById(request.movieId())
                .orElseThrow(() -> new IllegalArgumentException("Filme não encontrado."));

        // Validação de Faixa Etária
        if (user.getProfileType() == ProfileType.CHILD && movie.getAgeRating() > 12) {
            throw new IllegalStateException("Acesso negado: A classificação indicativa deste filme é superior à permitida para perfis infantis.");
        }
        if (user.getProfileType() == ProfileType.TEEN && movie.getAgeRating() == 18) {
            if (!user.getAllowsAdultContent()) {
                throw new IllegalStateException("Acesso negado: Perfis adolescentes exigem permissão do titular para assistir conteúdo adulto.");
            }
        }

        // Validação de Plano
        if (user.getPlanType() == PlanType.BASIC && movie.getMinimumPlan() != PlanType.BASIC) {
            throw new IllegalStateException("Acesso negado: Seu plano BASIC não permite assistir a filmes STANDARD ou PREMIUM.");
        }
        if (user.getPlanType() == PlanType.STANDARD && movie.getMinimumPlan() == PlanType.PREMIUM) {
            throw new IllegalStateException("Acesso negado: Seu plano STANDARD não permite assistir a filmes PREMIUM.");
        }

        // Salvando no histórico
        br.com.streaming.domain.entities.WatchHistory history = new br.com.streaming.domain.entities.WatchHistory();
        history.setUser(user);
        history.setMovie(movie);
        history.setWatchedAt(java.time.LocalDateTime.now());
        
        watchHistoryRepository.save(history);

        return "Success! You watched " + movie.getTitle() + "!";
    }

    public List<WatchHistoryResponseDTO> getUserHistory(Long userId) {
        // Verifica se o usuário existe para garantir a integridade da requisição
        userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));

        // Busca o histórico ordenado pelo repositório que criamos
        // Mapeia a entidade do banco para o DTO com o historyId que você definiu
        return watchHistoryRepository.findByUserIdOrderByWatchedAtDesc(userId)
                .stream()
                .map(history -> new WatchHistoryResponseDTO(
                        history.getId(),
                        history.getMovie().getTitle(),
                        history.getWatchedAt()
                ))
                .toList(); 
    }
}