// Agora é o momento de colocar o conhecimento de matrizes na prática.
// Crie uma matriz 3x3 e a preencha manualmente com números inteiros.
// Depois, imprima mostrando no console todos os elementos da matriz, na ordem que eles se encontram, linha por linha, usando um loop.

//import java.util.Random;

public class Exercicio1 {
    public static void main(String[] args) {
        
        // Criando e preenchendo a matriz 3x3 manualmente
        int[][] matrix = {
            {10, 20, 30},
            {40, 50, 60},
            {70, 80, 90}
        };

        /*
        // Loop para preencher automaticamente a matriz com valores aleatorios
        Random random = new Random();
        
        for (int[] linha : matrix) {
            for (int col = 0; col < linha.length; col++) {
                linha[col] = random.nextInt(100); 
            }
        }
        */ 

        System.out.println("Imprimindo os elementos da matriz linha por linha:");
        
        // Imprimindo todos os elementos no console usando apenas println
        for (int[] linha : matrix) {
            for (int col = 0; col < linha.length; col++) {
                System.out.print(linha[col] + " ");
            }
            System.out.println();
        }
    }
}