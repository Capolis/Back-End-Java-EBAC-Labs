import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in, "UTF-8");

        // Coleta de dados do usuário
        System.out.println("--- Bem-vindo ao Sistema de Cadastro! ---");
        System.out.print("Digite seu nome completo: ");
        String nome = leitor.nextLine();
        System.out.print("Digite seu CPF(ex: 123.456.789-00): ");
        String cpf = leitor.next(); // CPF vai ser tratado como String por causa dos pontos/traços
        System.out.print("Digite sua idade: ");
        int idade = leitor.nextInt();
        System.out.print("Digite seu peso (ex: 70,5): ");
        double peso = leitor.nextDouble();
        System.out.print("Digite sua altura (ex: 1,75): ");
        double altura = leitor.nextDouble();
        System.out.print("Digite seu estado civil: ");
        String estadoCivil = leitor.next();

        // Exibição dos dados
        System.out.println("\n--- Dados Cadastrados com Sucesso ---");
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Idade: " + idade + " anos");
        System.out.println("Peso: " + peso + " kg");
        System.out.println("Altura: " + altura + " m");
        System.out.println("Estado Civil: " + estadoCivil);

        leitor.close();
    }
}