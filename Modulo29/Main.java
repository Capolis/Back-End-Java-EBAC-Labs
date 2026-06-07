import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Produto> listaDeProdutos = new ArrayList<>();

        // Adicionando 10 produtos com nomes e preços variados
        listaDeProdutos.add(new Produto("Notebook", 4500.00));
        listaDeProdutos.add(new Produto("Mouse", 89.99));
        listaDeProdutos.add(new Produto("Teclado Mecânico", 349.99));
        listaDeProdutos.add(new Produto("Monitor 24\"", 950.00));
        listaDeProdutos.add(new Produto("Cadeira Gamer", 1200.00));
        listaDeProdutos.add(new Produto("Mousepad", 45.00));
        listaDeProdutos.add(new Produto("Headset", 250.00));
        listaDeProdutos.add(new Produto("Webcam", 180.00));
        listaDeProdutos.add(new Produto("Microfone", 420.00));
        listaDeProdutos.add(new Produto("Cabo HDMI", 24.99));

        System.out.println("Lista de Produtos Ordenada por Preço");
        
        ListaUtil.ordenarExibir(listaDeProdutos);
    }
}