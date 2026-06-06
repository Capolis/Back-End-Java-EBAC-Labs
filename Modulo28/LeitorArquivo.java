import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeitorArquivo {

    public static void main(String[] args) {
        String caminhoArquivo = "produtos.txt";
        List<Produto> listaProdutos = lerDadosDoArquivo(caminhoArquivo);

        exibirEmXml(listaProdutos);
    }

    public static List<Produto> lerDadosDoArquivo(String caminho) {
        List<Produto> produtosLidos = new ArrayList<>();

        try (BufferedReader leitor = new BufferedReader(new FileReader(caminho))) {
            String linha;
            
            while ((linha = leitor.readLine()) != null) {
                // A regra de separação nessa execução é o '|'
                String[] dados = linha.split("\\|");

                // Valida se a linha tem exatamente as 4 informações esperadas
                if (dados.length == 4) {
                    int id = Integer.parseInt(dados[0].trim());
                    String nome = dados[1].trim();
                    double preco = Double.parseDouble(dados[2].trim());
                    TipoProduto tipo = TipoProduto.valueOf(dados[3].trim().toUpperCase());

                    Produto produto = new Produto(id, nome, preco, tipo);
                    produtosLidos.add(produto);
                }
                else {
                    System.err.println("Linha ignorada (formato incorreto): " + linha);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao acessar ou ler o arquivo: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao converter um dado (verifique números ou o Enum): " + e.getMessage());
        }

        return produtosLidos;
    }

    public static void exibirEmXml(List<Produto> produtos) {
        System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        System.out.println("<produtos>");
        
        for (Produto produto : produtos) {
            System.out.println(produto.paraXml());
        }
        
        System.out.println("</produtos>");
    }
}