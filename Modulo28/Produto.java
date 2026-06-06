public class Produto {
    private int id;
    private String nome;
    private double preco;
    private TipoProduto tipo;

    public Produto(int id, String nome, double preco, TipoProduto tipo) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.tipo = tipo;
    }
    
    public String paraXml() {
        return "    <produto>\n" +
                "        <id>" + id + "</id>\n" +
                "        <nome>" + nome + "</nome>\n" +
                "        <preco>" + preco + "</preco>\n" +
                "        <tipo>" + tipo + "</tipo>\n" +
                "    </produto>";
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public TipoProduto getTipo() {
        return tipo;
    }

}