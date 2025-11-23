import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Pasajero> listaPasajeros = new ArrayList<>();
        int id = 1;

        // Generar 352 pasajeros (o los que necesites)
        for (int i = 0; i < 100; i++) listaPasajeros.add(new Pasajero(id++, 1));
        for (int i = 0; i < 100; i++) listaPasajeros.add(new Pasajero(id++, 2));
        for (int i = 0; i < 100; i++) listaPasajeros.add(new Pasajero(id++, 3));
        for (int i = 0; i < 52; i++) listaPasajeros.add(new Pasajero(id++, 4));

        Barco barco = new Barco(listaPasajeros);

        Balsa[] balsas = {
                new Balsa("Acasta", 1, 0.5),
                new Balsa("Banff", 2, 1),
                new Balsa("Cadiz", 3, 2),
                new Balsa("Deimos", 4, 4),
                new Balsa("Expedición", 5, 8)
        };

        // Crear y arrancar los hilos de rescate
        Rescate[] rescates = new Rescate[balsas.length];
        for (int i = 0; i < balsas.length; i++) {
            rescates[i] = new Rescate(barco, balsas[i]);
            rescates[i].start();
        }

        // Esperar a que acaben todos
        for (Rescate r : rescates) {
            r.join();
        }

        System.out.println("RESCATE FINALIZADO");
    }
}
