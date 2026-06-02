import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Pessoa> cadastroPessoas = inicializarCadastro();
        exibirResultadosCadastro(cadastroPessoas);
        imprimirExplicacaoConsole();
    }

    private static Set<Pessoa> inicializarCadastro() {
        Set<Pessoa> conjuntoTemporario = new HashSet<>();
        
        // Inserções únicas
        conjuntoTemporario.add(new Pessoa("Carlos", 30));
        conjuntoTemporario.add(new Pessoa("Ana", 25));
        
        // Tentativas de inserção de dados duplicados
        conjuntoTemporario.add(new Pessoa("Carlos", 30)); // Duplicado
        conjuntoTemporario.add(new Pessoa("Beatriz", 40)); // Único
        conjuntoTemporario.add(new Pessoa("Ana", 25));    // Duplicado
        
        return conjuntoTemporario;
    }

    private static void exibirResultadosCadastro(Set<Pessoa> conjuntoExibicao) {
        System.out.println("Total de registros finais no sistema: " + conjuntoExibicao.size());
        System.out.println("---- Lista de Pessoas Cadastradas ----");
        
        for (Pessoa pessoaCorrente : conjuntoExibicao) {
            System.out.println(pessoaCorrente.toString());
        }
        System.out.println("------------------------------------\n");
    }

    private static void imprimirExplicacaoConsole() {
        System.out.println("---- Explicação Técnica do Resultado ----");
        System.out.println("Ao tentar inserir 5 instâncias no HashSet, terminamos com apenas 3 registros.");
        System.out.println("1. O HashSet utiliza uma tabela de espalhamento (Hash Table) que não admite elementos duplicados.");
        System.out.println("2. Durante a inserção, a coleção invoca primeiro o método hashCode() do objeto para determinar a posição na memória onde ele deve ser alocada.");
        System.out.println("3. Se o bucket estiver vazio, o elemento entra. Se o bucket já contiver um elemento com o mesmo hash, ocorre uma colisão. Nesse momento, o HashSet invoca o método equals() para verificar a igualdade lógica.");
        System.out.println("4. Como nós sobrescrevemos os métodos hashCode() e equals() na classe Pessoa para avaliar estritamente os campos 'nome' e 'idade', objetos em endereços de memória distintos, mas com os mesmos valores, geram um comportamento de colisão intencional.");
        System.out.println("5. O equals() retorna 'true' validando que são logicamente idênticos, e a interface Set descarta a nova tentativa de inserção, garantindo a unicidade sem falhas.");
    }
}