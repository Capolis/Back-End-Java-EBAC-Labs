// Exercicio
// Permita que o usuário crie uma quantidade definida por ele de objetos do tipo Carro (no máximo 50), definindo os atributos de cada objeto carro.
// Armazene esses objetos em um vetor de tipo Carro.
// Exiba o nome de todos os carros, a quantidade de carros criados e a soma do preço total deles.

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int quantidadeCarros;
        Carro[] frota;
        double somaTotalPrecos = 0;

        System.out.print("Quantos carros você deseja criar? (Máximo 50): ");
        quantidadeCarros = scanner.nextInt();
        scanner.nextLine();

        
        // Validação da quantidade máxima permitida
        if (quantidadeCarros > 50) {
            System.out.println("Limite máximo excedido. Criando 50 carros.");
            quantidadeCarros = 50;
        } else if (quantidadeCarros <= 0) {
            System.out.println("Quantidade inválida. Encerrando o programa.");
            scanner.close();
            return;
        }
        
        // Criando o vetor para armazenar os objetos Carro
        frota = new Carro[quantidadeCarros];

        // Loop para ler os dados e instanciar cada Carro
        for (int i = 0; i < quantidadeCarros; i++) {
            System.out.println("\n--- Inserindo dados do Carro #" + (i + 1) + " ---");

            System.out.print("Nome do carro: ");
            String nome = scanner.nextLine();

            System.out.print("Preço: R$ ");
            double preco = scanner.nextDouble();
            scanner.nextLine();

            // Instancia o objeto e guarda no vetor
            frota[i] = new Carro(nome, preco);

            // Adiciona o preço atual à soma total
            somaTotalPrecos += preco;
        }

        // Exibição dos resultados finais solicitados
        System.out.println("\n==================================");
        System.out.println("     RELATÓRIO DOS CARROS         ");
        System.out.println("==================================");

        System.out.println("Nomes dos carros criados:");
        for (Carro carro : frota) {
            System.out.println("- " + carro.getNome());
        }

        System.out.println("----------------------------------");
        System.out.println("Quantidade de carros criados: " + quantidadeCarros);
        System.out.printf("Soma total do preço: R$ %.2f\n", somaTotalPrecos);
        System.out.println("==================================");

        scanner.close();
    }
}