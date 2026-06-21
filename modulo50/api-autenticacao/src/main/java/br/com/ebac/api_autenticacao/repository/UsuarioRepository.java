package br.com.ebac.api_autenticacao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ebac.api_autenticacao.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Método para encontrar um usuário pelo nome de usuário
    Optional<Usuario> findByNomeUsuario(String nomeUsuario);
}