package br.com.ebac.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.ebac.exception.ExcecaoRecursoNaoEncontrado;
import br.com.ebac.model.Produto;
import br.com.ebac.repository.ProdutoRepository;

@Service
public class ProdutoServico {

    private final ProdutoRepository produtoRepository;

    public ProdutoServico(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> salvarTodos(List<Produto> produtos) {
        return produtoRepository.saveAll(produtos);
    }

    public void deletar(Long id) {
        Produto produto = buscarPorId(id);
        produtoRepository.delete(produto);
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ExcecaoRecursoNaoEncontrado("Produto não encontrado no banco. ID: " + id));
    }

    public Page<Produto> obterProdutosPaginadosEFiltrados(String filtroNome, int pagina, int tamanho, String direcaoOrdem) {
        
        Sort ordenacao = direcaoOrdem.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by("nome").ascending() : Sort.by("nome").descending();

        Pageable paginacao = PageRequest.of(pagina, tamanho, ordenacao);

        if (filtroNome != null && !filtroNome.isEmpty()) {
            return produtoRepository.findByNomeContainingIgnoreCase(filtroNome, paginacao);
        }
        
        return produtoRepository.findAll(paginacao);
    }
}