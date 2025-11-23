import java.util.ArrayList;
import java.util.List;

public class Barco {
    private final List<Pasajero> pasajeros;

    public Barco(List<Pasajero> pasajeros) {
        this.pasajeros = new ArrayList<>(pasajeros);
    }

    public synchronized boolean hayPasajeros() {
        return !pasajeros.isEmpty();
    }

    public synchronized Pasajero sacarPrioritario() {
        if (pasajeros.isEmpty()) {
            return null;
        }

        Pasajero prioritario = pasajeros.get(0);
        for (Pasajero p : pasajeros) {
            if (p.getPrioridad() < prioritario.getPrioridad()) {
                prioritario = p;
            }
            if (prioritario.getPrioridad() == 1) {
                break;
            }
        }

        pasajeros.remove(prioritario);
        return prioritario;
    }

    public synchronized int getNumPasajeros() {
        return pasajeros.size();
    }
}
