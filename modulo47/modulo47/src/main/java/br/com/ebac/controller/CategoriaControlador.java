package br.com.ebac.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ebac.model.Categoria;
import br.com.ebac.repository.CategoriaRepository;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaControlador {

    private final CategoriaRepository categoriaRepository;

    public CategoriaControlador(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    public ResponseEntity<Categoria> criarCategoria(@RequestBody Categoria categoria) {
        Categoria salva = categoriaRepository.save(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }
}