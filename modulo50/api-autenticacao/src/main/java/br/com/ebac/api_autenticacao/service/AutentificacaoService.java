package br.com.ebac.api_autenticacao.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ebac.api_autenticacao.dto.CredenciaisDTO;
import br.com.ebac.api_autenticacao.model.Usuario;
import br.com.ebac.api_autenticacao.repository.UsuarioRepository;
import br.com.ebac.api_autenticacao.util.CriptografiaUtil;

@Service
public class AutentificacaoService {

    private final UsuarioRepository usuarioRepositorio;

    public AutentificacaoService(UsuarioRepository usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public String cadastrarUsuario(CredenciaisDTO credenciais) {
        // Verifica se o usuário já existe para não duplicar
        if (usuarioRepositorio.findByNomeUsuario(credenciais.getNomeUsuario()).isPresent()) {
            return "Erro: Este usuário já está cadastrado.";
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNomeUsuario(credenciais.getNomeUsuario());

        // Criptografa a senha antes de mandar para o banco de dados
        String senhaCriptografada = CriptografiaUtil.criptografarSenha(credenciais.getSenhaUsuario());
        novoUsuario.setSenhaCriptografada(senhaCriptografada);

        usuarioRepositorio.save(novoUsuario);
        return "Sucesso";
    }

    public String validarAcesso(CredenciaisDTO credenciais) {
        Optional<Usuario> usuarioOpcional = usuarioRepositorio.findByNomeUsuario(credenciais.getNomeUsuario());

        if (usuarioOpcional.isEmpty()) {
            return "Erro: O usuário não existe.";
        }

        Usuario usuarioEncontrado = usuarioOpcional.get();

        boolean senhaCorreta = CriptografiaUtil.verificarSenha(
                credenciais.getSenhaUsuario(),
                usuarioEncontrado.getSenhaCriptografada());

        if (!senhaCorreta) {
            return "Erro: A senha está errada.";
        }

        return "Sucesso";
    }
}