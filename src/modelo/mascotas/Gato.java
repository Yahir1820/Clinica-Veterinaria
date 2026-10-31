package modelo.mascotas;
import modelo.personas.Dueno;

/**
 * Gato: además de los datos de Mascota, indica si es doméstico
 */

public class Gato extends Mascota {
    private boolean esDomestico;

    /**
     * Crea un gato.
     *
     * @param nombre      nombre del gato
     * @param edad        edad en años
     * @param peso        peso en kilogramos
     * @param dueno       dueño del gato
     * @param esDomestico true si el gato es doméstico
     */

    public Gato(String nombre, int edad, double peso, Dueno dueno, boolean esDomestico) {
        super(nombre, edad, peso, dueno);
        setEsDomestico(esDomestico);
    }

    /**
     * @return true si el gato es doméstico
     */
    public boolean getEsDomestico() {
        return esDomestico;
    }

    /**
     * @param esDomestico
     */
    public void setEsDomestico(boolean esDomestico) {
        this.esDomestico = esDomestico;
    }

    @Override
    public String mostrarDatos() {
        return "Gato: " + getNombre() + " (" + (esDomestico ? "doméstico" : "no doméstico") + ")";
    }

    @Override
    public String emitirSonido() {
        return "Miau";
    }
}
