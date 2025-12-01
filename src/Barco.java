import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Barco {
    private final List<Pasajero> pasajeros;
    private final Semaphore semBarco = new Semaphore(1);
    public Barco(List<Pasajero> pasajeros) {
        this.pasajeros = new ArrayList<>(pasajeros);
    }

    public synchronized boolean hayPasajeros() throws InterruptedException {
        semBarco.acquire();
        return !pasajeros.isEmpty();
    }

    public synchronized Pasajero sacarPrioritario() throws InterruptedException {
        semBarco.acquire();
        if (pasajeros.isEmpty()) {
            return null;
        }

        Pasajero prioritario = pasajeros.get(0);
        for (Pasajero p : pasajeros) {
            if (p.getPrioridad() < prioritario.getPrioridad()) {
                prioritario = p;
            }
        }

        pasajeros.remove(prioritario);
        return prioritario;
    }

    public synchronized int getNumPasajeros() throws InterruptedException {
        try {
            semBarco.acquire();
            return pasajeros.size();
        }catch (InterruptedException e){
            return -1;
        }finally {
            semBarco.release();
        }

    }
}
