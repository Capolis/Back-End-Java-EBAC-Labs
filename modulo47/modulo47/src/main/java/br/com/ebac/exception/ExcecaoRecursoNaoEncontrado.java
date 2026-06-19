package br.com.ebac.exception;

public class ExcecaoRecursoNaoEncontrado extends RuntimeException {
    public ExcecaoRecursoNaoEncontrado(String mensagem) {
        super(mensagem);
    }
}