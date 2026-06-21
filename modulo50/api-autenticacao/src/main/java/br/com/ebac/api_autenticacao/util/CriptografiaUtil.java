package br.com.ebac.api_autenticacao.util;

public class CriptografiaUtil {
    
    public static String criptografarSenha(String senhaPura) {
        // Gerar o hash da criptografia.
        return "hash_" + senhaPura; 
    }

    public static boolean verificarSenha(String senhaPura, String senhaCriptografadaBanco) {
        // Método que já usamos para comparar a senha pura com o hash do banco
        return criptografarSenha(senhaPura).equals(senhaCriptografadaBanco); 
    }

}