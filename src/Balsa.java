import java.util.ArrayList;
import java.util.List;

public class Balsa {
    private int capacidad;
    private int tiempo;
    private List<Persona> personasRescatadas;
    private String nombre;

    public Balsa(String nombre, int capacidad, int tiempo) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.tiempo = tiempo;
        this.personasRescatadas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public List<Persona> getPersonasRescatadas() {
        return personasRescatadas;
    }

    public boolean estaLlena() {
        return personasRescatadas.size() >= capacidad;
    }

    public void agregarPersona(Persona persona) {
        if (!estaLlena()) {
            personasRescatadas.add(persona);
        }
    }

    public void vaciarBalsa() {
        personasRescatadas.clear();
    }

    @Override
    public String toString() {
        return " Balsa " + nombre + " (" + personasRescatadas.size() + "/" + capacidad + ")";
    }
}