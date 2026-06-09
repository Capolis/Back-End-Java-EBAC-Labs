class Produto {
    private final String nome;
    private final Double preco;

    public Produto(String nome, Double preco) {
        this.nome = nome;
        this.preco = preco;
    }
    
    @Override
    public String toString() {
        return String.format("%s - R$ %.2f", nome, preco);
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }
}