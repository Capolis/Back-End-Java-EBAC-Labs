package br.com.ebac.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControladorExcecoes {

    @ExceptionHandler(ExcecaoRecursoNaoEncontrado.class)
    public ResponseEntity<String> lidarComExcecaoRecursoNaoEncontrado(ExcecaoRecursoNaoEncontrado ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}