package modelo.mascotas;
import modelo.personas.Dueno;

/**
 * Perro: además de los datos de Mascota, guarda su raza y nivel de energía
 */

public class Perro extends Mascota {
    private String raza;
    private String nivelEnergia;

    /**
     * Crea un perro.
     *
     * @param nombre       nombre del perro
     * @param edad         edad en años
     * @param peso         peso en kilogramos
     * @param dueno        dueño del perro
     * @param raza         raza del perro
     * @param nivelEnergia nivel de energía (bajo, medio, alto)
     */

    public Perro(String nombre, int edad, double peso, Dueno dueno, String raza, String nivelEnergia) {
        super(nombre, edad, peso, dueno);
        setRaza(raza);
        setNivelEnergia(nivelEnergia);
    }

    /**
     * @return raza del perro
     */
    public String getRaza() {
        return raza;
    }

    /**
     * @param raza
     * @throws IllegalArgumentException si algún dato no es válido
     */
    public void setRaza(String raza) {
        if (raza == null || raza.isBlank()) {
            throw new IllegalArgumentException("La raza no puede estar vacía");
        }
        this.raza = raza;
    }

    /**
     * @param nivelEnergia
     * @throws IllegalArgumentException si algún dato no es válido
     */
    public void setNivelEnergia(String nivelEnergia) {
        if (nivelEnergia == null || nivelEnergia.isBlank()) {
            throw new IllegalArgumentException("El nivel de energía no puede estar vacío");
        }
        this.nivelEnergia = nivelEnergia;
    }

    @Override
    public String mostrarDatosAnimal() {
        return "Dueño: " + dueno.nombreCompleto() + "\nPerro: " + nombre + "\nEdad" + edad
                + "\nPeso: " + peso + "\nRaza: " + raza + "\nEnergía " + nivelEnergia;
    }

    @Override
    public String emitirSonido() {
        return "Guau";
    }
}
