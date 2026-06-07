import java.util.Collections;
import java.util.List;

public class ListaUtil {
    
    public static <T extends Comparable<? super T>> void ordenarExibir(List<T> lista) {
        // Ordena a lista
        Collections.sort(lista);
        
        for (T item : lista) {
            System.out.println(item);
        }
    }
}