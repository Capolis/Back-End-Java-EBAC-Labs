package br.com.ebac.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ebac.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Consulta personalizada usando JPQL
    @Query("SELECT p FROM Produto p WHERE p.nome = :nome")
    List<Produto> buscarPorNomeComJpql(@Param("nome") String nome);

    // Consulta para o filtro e paginação nativa do Spring Data
    Page<Produto> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}