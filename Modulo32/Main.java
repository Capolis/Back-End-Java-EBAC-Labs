import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        
        // Criando uma lista com 30 produtos diferentes
        List<Produto> produtos = criarListaProdutos();
        
        System.out.println("\nFILTRANDO PRODUTOS ACIMA DE R$ 100");
        
        // Criando uma nova lista apenas com produtos maiores que 100 usando collect
        List<Produto> filtroProduto = produtos.stream()
                .filter(p -> p.getPreco() > 100.0)
                .collect(Collectors.toList());

        // filtroProduto.forEach(System.out::println); // Imprime os produtos filtrados
                
        System.out.println("\nLISTA ORDENADA EM ORDEM ALFABÉTICA E COM VALOR MAIOR QUE R$ 100");
        
        // Ordenando a lista pelo nome
        List<Produto> produtosOrdenados = filtroProduto.stream()
                .sorted(Comparator.comparing(Produto::getNome))
                .collect(Collectors.toList());
                
        produtosOrdenados.forEach(System.out::println);


        System.out.println("\nBUSCA DE PRODUTOS");
        
        // Realizando buscas usando Optional e ifPresentOrElse
        String nomeBuscaSucesso = "Webcam";
        String nomeBuscaFalha = "Drone";

        System.out.println("Buscando: " + nomeBuscaSucesso);
        buscarEExibirProduto(produtos, nomeBuscaSucesso);

        System.out.println("\nBuscando: " + nomeBuscaFalha);
        buscarEExibirProduto(produtos, nomeBuscaFalha);
    }

    private static List<Produto> criarListaProdutos() {
         return List.of(
            new Produto("Notebook", 3500.0),
            new Produto("Mouse", 50.0),
            new Produto("Teclado", 120.0),
            new Produto("Monitor", 800.0),
            new Produto("Smartphone", 2000.0),
            new Produto("Tablet", 1500.0),
            new Produto("Fone de Ouvido", 80.0),
            new Produto("Cadeira Gamer", 900.0),
            new Produto("Mesa", 400.0),
            new Produto("Webcam", 250.0),
            new Produto("Microfone", 300.0),
            new Produto("Roteador", 150.0),
            new Produto("HD Externo", 400.0),
            new Produto("Pendrive", 40.0),
            new Produto("Cabo HDMI", 25.0),
            new Produto("Placa de Vídeo", 2500.0),
            new Produto("Processador", 1200.0),
            new Produto("Placa Mãe", 850.0),
            new Produto("Memória RAM", 350.0),
            new Produto("Fonte", 450.0),
            new Produto("Gabinete", 300.0),
            new Produto("Cooler", 100.0),
            new Produto("SSD", 250.0),
            new Produto("Impressora", 700.0),
            new Produto("Cartão de Memória", 60.0),
            new Produto("Smartwatch", 500.0),
            new Produto("Caixa de Som", 180.0),
            new Produto("Projetor", 1100.0),
            new Produto("Estabilizador", 130.0),
            new Produto("Nobreak", 600.0)
        );
}

    // Método para buscar um produto por nome e exibir seu preço usando Optional
    private static void buscarEExibirProduto(List<Produto> produtos, String nomeBusca) {
        Optional<Produto> produtoBuscado = produtos.stream()
                .filter(p -> p.getNome().equalsIgnoreCase(nomeBusca))
                .findFirst();

        produtoBuscado.ifPresentOrElse(
            produto -> System.out.println("Encontrado: " + produto.getNome() + " | Preço: R$ " + produto.getPreco()),
            () -> System.out.println("Produto não encontrado.")
        );
    }
}