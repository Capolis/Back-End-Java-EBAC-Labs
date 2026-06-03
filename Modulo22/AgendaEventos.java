import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class AgendaEventos {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss z");

        // Criando a instância com a data atual (03/06/2026 - Quarta-feira)
        LocalDateTime dataAtual = LocalDateTime.of(2026, 6, 3, 7, 29, 0);
        Evento meuEvento = new Evento("Workshop de Desenvolvimento", dataAtual);
        
        System.out.println("----- Evento Original -----");
        meuEvento.exibirEvento();
        System.out.println();

        // Adicionando 5 dias à data do evento (03/06 + 5 dias = 08/06, que cai numa Segunda-feira)
        LocalDateTime novaDataHora = meuEvento.getDataHora().plusDays(5);
        meuEvento.setDataHora(novaDataHora); 
        
        System.out.println("----- Evento Após Adicionar 5 Dias -----");
        meuEvento.exibirEvento();
        System.out.println();

        // Conversão de Fusos Horários
        ZoneId zoneSaoPaulo = ZoneId.of("America/Sao_Paulo");
        ZoneId zoneGmt = ZoneId.of("GMT");

        ZonedDateTime dataEmSaoPaulo = novaDataHora.atZone(zoneSaoPaulo);
        ZonedDateTime dataEmGmt = dataEmSaoPaulo.withZoneSameInstant(zoneGmt);

        System.out.println("----- Conversão de Fusos Horários -----");
        System.out.println("São Paulo (BRT): " + dataEmSaoPaulo.format(formatter));
        System.out.println("GMT:             " + dataEmGmt.format(formatter));
    }
}