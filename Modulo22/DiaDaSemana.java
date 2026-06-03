// 1. Criação do Enum DiaDaSemana

import java.time.DayOfWeek;

enum DiaDaSemana {
    SEGUNDA, TERÇA, QUARTA, QUINTA, SEXTA, SÁBADO, DOMINGO;

    // Método para converter o DayOfWeek
    public static DiaDaSemana conversãoDoDia(DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case MONDAY -> {
                return SEGUNDA;
            }
            case TUESDAY -> {
                return TERÇA;
            }
            case WEDNESDAY -> {
                return QUARTA;
            }
            case THURSDAY -> {
                return QUINTA;
            }
            case FRIDAY -> {
                return SEXTA;
            }
            case SATURDAY -> {
                return SÁBADO;
            }
            case SUNDAY -> {
                return DOMINGO;
            }
            default -> throw new IllegalArgumentException("Dia inválido");
        }
    }
}