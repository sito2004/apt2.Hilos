import java.util.ArrayList;

public class Rescate extends Thread {
    private final Barco barco;
    private final Balsa balsa;

    public Rescate(Barco barco, Balsa balsa) {
        this.barco = barco;
        this.balsa = balsa;
    }

    @Override
    public void run() {
        while (true) {
            // si no hay pasajeros, terminamos
            try {
                if (!barco.hayPasajeros()) {
                    System.out.println(">>> La balsa " + balsa.getNombre() + " termina: no quedan pasajeros.");
                    return;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            boolean embarcoAlguien = embarcar();

            if (!embarcoAlguien) {
                System.out.println(">>> La balsa " + balsa.getNombre() + " no embarcó a nadie, fin.");
                return;
            }

            navegandoATierra();
            desembarcar();

        }
    }

    private boolean embarcar() {
        System.out.println("Embarcando en balsa " + balsa.getNombre());
        boolean subio = false;

        for (int i = 0; i < balsa.getCapacidad(); i++) {
            Pasajero p = barco.sacarPrioritario();
            if (p == null) {
                break;
            }
            subio = true;
            System.out.println("\t" + p + " sube a " + balsa.getNombre());
            balsa.subirPasajeroBalsa(p);
        }

        return subio;
    }

    private void navegandoATierra() {
        System.out.println(balsa.getNombre() + " navegando a tierra...");
        try {
            Thread.sleep((long) balsa.getTiempo());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void desembarcar() {
        System.out.println(balsa.getNombre() + " desembarcando pasajeros...");
        ArrayList<Pasajero> lista = new ArrayList<>(balsa.getPasajeros());
        for (Pasajero p : lista) {
            System.out.println("\t" + p + " rescatado en " + balsa.getNombre());
            balsa.bajarPasajeroBalsa(p);
        }
        System.out.println("------ Fin desembarque de " + balsa.getNombre() + " ------");
    }

}
