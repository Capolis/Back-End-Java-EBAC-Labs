import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GerenciadorDeUsuarios {

    public static void main(String[] argumentos) {
        // Configurações do Banco de Dados
        String urlDoBanco = "jdbc:mysql://localhost:3306/usuarios";
        String usuarioDoBanco = "root";
        String senhaDoBanco = "minhasenhadorootquenaoquerocompartilhar";
        
        Scanner scanner = new Scanner(System.in);

        try (Connection conexao = DriverManager.getConnection(urlDoBanco, usuarioDoBanco, senhaDoBanco)) {
            System.out.println("--- Conexão estabelecida com sucesso! ---\n");

            // INSERIR
            String comandoSqlInserir = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";
            try (PreparedStatement comandoInserir = conexao.prepareStatement(comandoSqlInserir)) {
                comandoInserir.setString(1, "João Silva");
                comandoInserir.setString(2, "jaoa@email.com");
                comandoInserir.executeUpdate();
                System.out.println("[CREATE] Usuário 'João Silva' inserido com sucesso.");
            }

            // ATUALIZAR (Localizando pelo nome exato)
            String comandoSqlAtualizar = "UPDATE usuarios SET email = ? WHERE nome = ?";
            try (PreparedStatement comandoAtualizar = conexao.prepareStatement(comandoSqlAtualizar)) {
                comandoAtualizar.setString(1, "joao@email.com");
                comandoAtualizar.setString(2, "João Silva");
                int linhasAtualizadas = comandoAtualizar.executeUpdate();
                if (linhasAtualizadas > 0) {
                    System.out.println("[UPDATE] E-mail do usuário 'João Silva' atualizado com sucesso.");
                }
            }

            // CONSULTAR
            String comandoSqlConsultar = "SELECT id, nome, email FROM usuarios";
            try (PreparedStatement comandoConsultar = conexao.prepareStatement(comandoSqlConsultar);
                 ResultSet resultadoConsulta = comandoConsultar.executeQuery()) {
                 
                System.out.println("\n--- Lista de Usuários ---");
                while (resultadoConsulta.next()) {
                    int identificador = resultadoConsulta.getInt("id");
                    String nomeUsuario = resultadoConsulta.getString("nome");
                    String emailUsuario = resultadoConsulta.getString("email");
                    
                    // Formatando a saída conforme solicitado
                    System.out.println("ID: " + identificador + " - Nome: " + nomeUsuario + " - Email: " + emailUsuario);
                }
                System.out.println("-------------------------\n");
            }

            // EXCLUIR (Removendo o registro usando o nome como critério)
            String comandoSqlExcluir = "DELETE FROM usuarios WHERE nome = ?";
            try (PreparedStatement comandoExcluir = conexao.prepareStatement(comandoSqlExcluir)) {
                comandoExcluir.setString(1, "João Silva");
                int linhasExcluidas = comandoExcluir.executeUpdate();
                if (linhasExcluidas > 0) {
                    System.out.println("[DELETE] Usuário 'João Silva' removido com sucesso.");
                }
            }

        } catch (SQLException excecaoBanco) {
            System.err.println("Erro ao comunicar com o banco de dados: " + excecaoBanco.getMessage());
        } finally {
            scanner.close();
        }
    }
}