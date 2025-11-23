import java.util.ArrayList;
import java.util.List;

public class Balsa {
    private final String nombre;
    private final int capacidad;
    private final double tiempo; // en segundos
    private final List<Pasajero> pasajeros;

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

    public synchronized void subirPasajeroBalsa(Pasajero p) {
        pasajeros.add(p);
    }

    public synchronized void bajarPasajeroBalsa(Pasajero p) {
        pasajeros.remove(p);
    }

    public synchronized List<Pasajero> getPasajeros() {
        return new ArrayList<>(pasajeros);
    }

    public synchronized void vaciar() {
        pasajeros.clear();
    }

    @Override
    public String toString() {
        return "Balsa{" + nombre + " (" + pasajeros.size() + "/" + capacidad + ")}";
    }
}
