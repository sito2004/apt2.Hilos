import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Balsa {
    private final String nombre;
    private final int capacidad;
    private final double tiempo; // en segundos
    private final List<Pasajero> pasajeros;
    private final Semaphore semBalsa = new Semaphore(1);

    public Balsa(String nombre, int capacidad, double tiempo) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.tiempo = tiempo;
        this.pasajeros = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public double getTiempo() {
        return tiempo * 1000; // convertir a ms para sleep
    }

    public void subirPasajeroBalsa(Pasajero p) {
        try {
            semBalsa.acquire();
            pasajeros.add(p);
        } catch (InterruptedException ignored) {
        } finally {
            semBalsa.release();
        }
    }


    public void bajarPasajeroBalsa(Pasajero p) {
        try {
            semBalsa.acquire();
            pasajeros.remove(p);
        } catch (InterruptedException ignored) {
        } finally {
            semBalsa.release();
        }
    }


    public List<Pasajero> getPasajeros() {
        try {
            semBalsa.acquire();
            return new ArrayList<>(pasajeros);
        } catch (InterruptedException e) {
            return new ArrayList<>();
        } finally {
            semBalsa.release();

        }

    }
}

