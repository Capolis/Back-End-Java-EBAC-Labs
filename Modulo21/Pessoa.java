import java.util.Objects;

class Pessoa {
    private String nome;
    private int idade;

    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    @Override
    public boolean equals(Object objetoReferencia) {
        if (this == objetoReferencia)
            return true;
        if (objetoReferencia == null || getClass() != objetoReferencia.getClass())
            return false;

        Pessoa pessoaComparada = (Pessoa) objetoReferencia;
        return idade == pessoaComparada.idade && Objects.equals(nome, pessoaComparada.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, idade);
    }

    @Override
    public String toString() {
        return "Pessoa{nome='" + nome + "', idade=" + idade + "}";
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }
}