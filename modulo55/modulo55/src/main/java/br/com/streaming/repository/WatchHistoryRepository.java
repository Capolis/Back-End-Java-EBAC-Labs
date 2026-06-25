package br.com.streaming.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.streaming.domain.entities.WatchHistory;

@Repository
public interface WatchHistoryRepository extends JpaRepository<WatchHistory, Long> {
    
    // Busca o histórico de um usuário ordenado pela data mais recente
    List<WatchHistory> findByUserIdOrderByWatchedAtDesc(Long userId);
}