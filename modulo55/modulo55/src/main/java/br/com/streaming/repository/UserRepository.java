package br.com.streaming.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.streaming.domain.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Conta quantos usuários dependentes estão vinculados a este assinante
    long countBySubscriberId(Long subscriberId);
    
    // Busca todos os dependentes de um assinante
    List<User> findBySubscriberId(Long subscriberId);

}