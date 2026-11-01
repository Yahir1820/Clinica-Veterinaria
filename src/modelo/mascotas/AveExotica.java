package modelo.mascotas;
import modelo.personas.Dueno;

/**
 * Ave exótica: además de los datos de Mascota, guarda la longitud de sus alas
 */

public class AveExotica extends Mascota {
    private double longitudAlas;

    /**
     * Crea un ave exótica.
     *
     * @param nombre       nombre del ave
     * @param edad         edad en años
     * @param peso         peso en kilogramos
     * @param dueno        dueño del ave
     * @param longitudAlas longitud de las alas en centímetros, mayor que cero
     */

    public AveExotica(String nombre, int edad, double peso, Dueno dueno, double longitudAlas) {
        super(nombre, edad, peso, dueno);
        setLongitudAlas(longitudAlas);
    }

    /**
     * @return longitud de las alas en centímetros
     */
    public double getLongitudAlas() {
        return longitudAlas;
    }

    /**
     * @param longitudAlas
     * @throws IllegalArgumentException si la longitud de las alas es menor o igual a cero
     */
    public void setLongitudAlas(double longitudAlas) {
        if (longitudAlas <= 0) {
            throw new IllegalArgumentException("La longitud debe ser mayor o igual a 0");
        }
        this.longitudAlas = longitudAlas;
    }

    @Override
    public String mostrarDatosAnimal() {
        return "Dueño: " + dueno.nombreCompleto() + "\nPerro: " + nombre + "\nEdad" + edad
                + "\nPeso: " + peso + "\nAlas de " + longitudAlas + " cm";
    }

    @Override
    public String emitirSonido() {
        return "Pío";
    }
}
