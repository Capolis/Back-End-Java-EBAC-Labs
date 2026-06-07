public class Produto implements Comparable<Produto> {
    private String nome;
    private double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    @Override
    public int compareTo(Produto outroProduto) {
        return Double.compare(this.preco, outroProduto.preco);
    }

    @Override
    public String toString() {
        // Formatando a saída para exibir o preço com duas casas decimais
        return String.format("Produto: %-20s | Preço: R$ %,.2f", nome, preco);
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }
}