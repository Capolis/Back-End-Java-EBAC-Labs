// 2. Classe Evento atualizada

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Evento {
    private String nome;
    private LocalDateTime dataHora;
    private DiaDaSemana diaDaSemana;

    // Construtor criado para definir o DiaDaSemana automaticamente a partir da data
    public Evento(String nome, LocalDateTime dataHora) {
        this.nome = nome;
        this.dataHora = dataHora;
        this.atualizarDiaDaSemana(); // Define o dia correto na criação
    }
    
    public void exibirEvento() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        System.out.println("--- Detalhes do Evento ---");
        System.out.println("Nome: " + nome);
        System.out.println("Dia da Semana: " + diaDaSemana);
        System.out.println("Data/Hora: " + dataHora.format(formatter));
    }
    
    // Setter que atualiza a data E recalcula o dia da semana automaticamente
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
        this.atualizarDiaDaSemana(); // Atualiza o dia automaticamente sempre que a data muda
    }
    
    // Método auxiliar privado para evitar repetição de código
    private void atualizarDiaDaSemana() {
        diaDaSemana = DiaDaSemana.conversãoDoDia(this.dataHora.getDayOfWeek());
    }
    
    public LocalDateTime getDataHora() {
        return dataHora;
    }

}