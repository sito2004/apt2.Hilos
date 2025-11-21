import java.util.ArrayList;
import java.util.List;

public class Balsa {
    private String nombre;
    private int capacidad;
    private double tiempo;
    private List<Pasajero> pasajerosRescatados;

    public Balsa(String nombre, int capacidad, double tiempo) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.tiempo = tiempo;
        this.pasajerosRescatados = new ArrayList<>();
    }

    public int getCapacidad() {
        return capacidad;
    }

    public double getTiempo() {
        return tiempo;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Pasajero> getPasajerosRescatados() {
        return pasajerosRescatados;
    }

    public boolean estaLlena() {
        return pasajerosRescatados.size() >= capacidad;
    }

    public void agregarPasajero(Pasajero pasajero) {
        if (!estaLlena()) {
            pasajerosRescatados.add(pasajero);
        }
    }

    public void vaciarBalsa() {
        pasajerosRescatados.clear();
    }

    @Override
    public String toString() {
        return "Balsa " + nombre + " - Capacidad: " + capacidad + " - Tiempo: " + tiempo + "s";
    }
}