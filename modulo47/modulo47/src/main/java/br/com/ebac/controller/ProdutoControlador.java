package br.com.ebac.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ebac.dto.ProdutoDTO;
import br.com.ebac.model.Produto;
import br.com.ebac.service.ProdutoServico;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoControlador {

    private final ProdutoServico produtoServico;

    public ProdutoControlador(ProdutoServico produtoServico) {
        this.produtoServico = produtoServico;
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> obterTodosOsProdutos(
            @RequestParam(required = false) String nome,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho,
            @RequestParam(defaultValue = "ASC") String ordem) {
            
        // Busca a página de entidades do banco
        Page<Produto> produtos = produtoServico.obterProdutosPaginadosEFiltrados(nome, pagina, tamanho, ordem);
        
        // Transforma a página de entidades em uma página de DTOs
        Page<ProdutoDTO> produtosDTO = produtos.map(this::converterParaDTO);
        
        return ResponseEntity.ok(produtosDTO);
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> criarProduto(@RequestBody Produto produto) {
        Produto salvo = produtoServico.salvar(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(converterParaDTO(salvo));
    }

    // Método auxiliar para isolar a conversão e proteger a estrutura da entidade
    private ProdutoDTO converterParaDTO(Produto produto) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setDescricao(produto.getDescricao());
        dto.setPreco(produto.getPreco());
        
        // Evita NullPointerException se o produto estiver sem categoria e isola o modelo
        if (produto.getCategoria() != null) {
            dto.setNomeCategoria(produto.getCategoria().getNome());
        }
        
        return dto;
    }
}