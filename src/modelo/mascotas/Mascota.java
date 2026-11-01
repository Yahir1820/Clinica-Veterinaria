package modelo.mascotas;
import modelo.personas.Dueno;

/**
 * Clase abstracta base para perros, gatos y aves exóticas
 * Cada mascota tiene un dueño y un historial clínico que existe solo dentro de ella
 */

public abstract class Mascota implements SonidoAnimal {
    private String nombre;
    private int edad;
    private double peso;
    private Dueno dueno;
    private HistorialClinico historial;

    /**
     * Crea una mascota con su dueño
     * El historial clínico se crea dentro de la mascota y no se comparte con nadie más
     *
     * @param nombre nombre de la mascota
     * @param edad   edad en años, mayor que cero
     * @param peso   peso en kilogramos, mayor que cero
     * @param dueno  dueño de la mascota, no nulo
     */

    protected Mascota(String nombre, int edad, double peso, Dueno dueno) {
        setNombre(nombre);
        setEdad(edad);
        setPeso(peso);
        setDueno(dueno);
        this.historial = new HistorialClinico();
    }

    /**
     * @return nombre de la mascota
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre
     * @throws IllegalArgumentException si algún dato no es válido
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la mascota no puede estar vacío");
        }
        this.nombre = nombre;
    }

    /**
     * @return edad de la mascota
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Actualiza la edad, validando que sea mayor que cero
     * @param edad
     * @throws IllegalArgumentException si la edad es menor o igual a cero
     */
    public void setEdad(int edad) {
        if (edad <= 0) {
            throw new IllegalArgumentException("El edad debe ser mayor a 0");
        }
        this.edad = edad;
    }

    /**
     * @return peso de la mascota en kg
     */
    public double getPeso() {
        return peso;
    }

    /**
     * Actualiza el peso, validando que sea mayor que cero
     * @param peso nuevo peso en kilogramos
     * @throws IllegalArgumentException si el peso es menor o igual a cero
     */
    public void setPeso(double peso) {
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso debe ser mayor a 0");
        }
    }

    /**
     * @return dueño de la mascota
     */
    public Dueno getDueno() {
        return dueno;
    }

    /**
     * Actualiza el dueño de la mascota
     * @param dueno
     * @throws IllegalArgumentException si no se registra un nuevo dueño
     */
    public void setDueno(Dueno dueno) {
        if (dueno == null) {
            throw new IllegalArgumentException("La mascota debe tener un dueño");
        }
        this.dueno = dueno;
    }

    /**
     * @return historial clínico de la mascota
     */
    public HistorialClinico getHistorial() {
        return historial;
    }

    /**
     * Muestra la mascota con sus datos específicos
     * @return datos de la mascota
     */
    public abstract String mostrarDatosAnimal();

    @Override
    public String toString() {
        return mostrarDatosAnimal();
    }
}
