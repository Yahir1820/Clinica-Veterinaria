package modelo.servicios;
/**
 * Vacunación: su costo es el costo de la vacuna aplicada
 */

public class Vacunacion extends Servicio{
    private String nombreVacuna;
    private double costoVacuna;

    /**
     * Crea un servicio de vacunación
     * @param nombreVacuna nombre de la vacuna aplicada
     * @param costoVacuna costo de la vacuna en pesos, no menor a 0
     */

    public Vacunacion(String nombreVacuna, double costoVacuna) {
        setNombreVacuna(nombreVacuna);
        setCostoVacuna(costoVacuna);
    }

    /**
     * @param nombreVacuna
     * @throws IllegalArgumentException si el nombre está vacío
     */
    public void setNombreVacuna(String nombreVacuna) {
        if (nombreVacuna == null || nombreVacuna.isBlank()) {
            throw new IllegalArgumentException("El nombre de vacuna no puede estar vacío");
        }
        this.nombreVacuna = nombreVacuna;
    }

    /**
     * @param costoVacuna
     * @throws IllegalArgumentException si el costo es menor o igual a 0
     */
    public void setCostoVacuna(double costoVacuna) {
        if (costoVacuna <= 0) {
            throw new IllegalArgumentException("El costro de la vacuna no puede ser menor a 0");
        }
        this.costoVacuna = costoVacuna;
    }

    @Override
    public String describirServicio() {
        return "Se vacunó con: " + nombreVacuna;
    }

    @Override
    public double calcularCosto() {
        return costoVacuna;
    }

}
