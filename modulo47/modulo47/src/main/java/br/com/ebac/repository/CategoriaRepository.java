package br.com.ebac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ebac.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
    // O JpaRepository já traz o método save() que usamos no CategoriaControlador por padrão.
    
}