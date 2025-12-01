import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Barco {
    private final List<Pasajero> pasajeros;
    private final Semaphore semBarco = new Semaphore(1);
    public Barco(List<Pasajero> pasajeros) {
        this.pasajeros = new ArrayList<>(pasajeros);
    }

    public boolean hayPasajeros() throws InterruptedException {
        try {
            semBarco.acquire();
            return !pasajeros.isEmpty();
        } catch (InterruptedException e) {
            return false;
        } finally {
            semBarco.release();
        }
    }

public Pasajero sacarPrioritario() {
    try {
        semBarco.acquire();

        if (pasajeros.isEmpty())
            return null;

        // PRIORIDAD: 1 es la más urgente
        Pasajero mejor = pasajeros.get(0);

        for (Pasajero p : pasajeros) {
            if (p.getPrioridad() < mejor.getPrioridad()) {
                mejor = p;
            }
            if (mejor.getPrioridad() == 1)
                break; // ya no puede haber mejor
        }

        pasajeros.remove(mejor);
        return mejor;

    } catch (InterruptedException e) {
        return null;
    } finally {
        semBarco.release();
    }
}

    public int getNumPasajeros() throws InterruptedException {
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
