
import java.util.*;

// Clase Main
public class Main {
    public static void main(String[] args) {
        // Crear pasajeros
        List<Pasajero> pasajeros = new ArrayList<>();
        int id = 1;

        // Ejemplo de distribución
        for (int i = 0; i < 100; i++) pasajeros.add(new Pasajero(id++,
                1)); // Niños
        for (int i = 0; i < 100; i++) pasajeros.add(new Pasajero(id++,
                2)); // Adultos
        for (int i = 0; i < 100; i++) pasajeros.add(new Pasajero(id++,
                3)); // Ancianos
        for (int i = 0; i < 52; i++) pasajeros.add(new Pasajero(id++,
                4)); // Tripulación

        Barco barco = new Barco(pasajeros);

        // Crear balsas
        Balsa[] balsas = {
                new Balsa("Acasta", 1, 0.5),
                new Balsa("Banff", 2, 1),
                new Balsa("Cadiz", 3, 2),
                new Balsa("Deimos", 4, 4),
                new Balsa("Expedición", 5, 8)
        };

        // Crear y arrancar hilos
        for (Balsa b : balsas) {
            new Thread(new Rescate(b, barco)).start();
        }
    }
}