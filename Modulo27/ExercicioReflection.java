import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ExercicioReflection {

    public static void main(String[] args) {
        try {
            Class<?> classeHospedeira = ClasseComLista.class;
            Object instanciaClasse = classeHospedeira.getDeclaredConstructor().newInstance();

            Class<?> classeArrayList = Class.forName("java.util.ArrayList");
            Object instanciaLista = classeArrayList.getDeclaredConstructor().newInstance();

            Field campoLista = classeHospedeira.getDeclaredField("minhaLista");
            campoLista.setAccessible(true); // acessar pois o campo é private
            campoLista.set(instanciaClasse, instanciaLista);

            Method metodoAdd = classeArrayList.getMethod("add", Object.class);

            metodoAdd.invoke(instanciaLista, "Caio César");          // String
            metodoAdd.invoke(instanciaLista, 18);                    // Integer
            metodoAdd.invoke(instanciaLista, 3.14159);               // Double
            metodoAdd.invoke(instanciaLista, true);                  // Boolean
            metodoAdd.invoke(instanciaLista, 'C');                   // Character

            Method metodoSize = classeArrayList.getMethod("size");
            Method metodoGet = classeArrayList.getMethod("get", int.class);

            int tamanhoDaLista = (Integer) metodoSize.invoke(instanciaLista);

            System.out.println("--- Lista ---");
            for (int i = 0; i < tamanhoDaLista; i++) {
                Object valor = metodoGet.invoke(instanciaLista, i);
                System.out.println("Índice " + (i+1) + ": " + valor + 
                                    " (Tipo: " + valor.getClass().getSimpleName() + ")");
            }

        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException
                 | NoSuchFieldException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            System.out.println("Ocorreu um erro ao manipular os objetos via Reflection:");
            e.printStackTrace();
        }
    }
}