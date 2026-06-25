package br.com.streaming.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.streaming.domain.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    // Todos os filtros (idade + plano + permissões) dos filmes serão tratados no Service usando Streams.
}