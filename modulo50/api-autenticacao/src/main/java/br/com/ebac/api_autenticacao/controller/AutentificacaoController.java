package br.com.ebac.api_autenticacao.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ebac.api_autenticacao.dto.CredenciaisDTO;
import br.com.ebac.api_autenticacao.service.AutentificacaoService;

@RestController
@RequestMapping("/api/acesso")
public class AutentificacaoController {

    private final AutentificacaoService servicoAutenticacao;

    public AutentificacaoController(AutentificacaoService servicoAutenticacao) {
        this.servicoAutenticacao = servicoAutenticacao;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<String> realizarCadastro(@RequestBody CredenciaisDTO credenciais) {
        String resultadoCadastro = servicoAutenticacao.cadastrarUsuario(credenciais);

        if (resultadoCadastro.contains("Erro")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultadoCadastro);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> realizarLogin(@RequestBody CredenciaisDTO credenciais) {
        String resultadoValidacao = servicoAutenticacao.validarAcesso(credenciais);

        if (resultadoValidacao.contains("não existe")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resultadoValidacao);
        }

        if (resultadoValidacao.contains("errada")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultadoValidacao);
        }

        return ResponseEntity.ok("Autenticado com sucesso!");
    }
}