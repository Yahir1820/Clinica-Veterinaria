package modelo.clinica;

/**
 * Comprobante de cobro generado al terminar una cita atendida.
 */
public class Comprobante {

    // El contador es static porque es compartido por todos los comprobantes del programa porque la numeración debe ser única
    private static int contadorGenerados = 0;

    protected int numero;
    protected Cita cita;
    protected double total;
    protected String fecha;

    /**
     * Crea un comprobante a partir de una cita atendida
     *
     * @param cita cita atendida que se cobra
     * @param total total a cobrar
     */
    public Comprobante(Cita cita, double total) {
        setCita(cita);
        setTotal(total);
        this.numero = ++contadorGenerados;
        this.fecha = cita.getFechaHora();
    }

    /**
     * @return cuántos comprobantes se han generado en el programa
     */
    public static int getContadorGenerados() {
        return contadorGenerados;
    }

    /**
     * @return número consecutivo del comprobante
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param cita cita atendida que se cobra
     * @throws IllegalArgumentException si la cita es nula
     */
    public void setCita(Cita cita) {
        if (cita == null) {
            throw new IllegalArgumentException("El comprobante requiere una cita");
        }
        this.cita = cita;
    }

    /**
     * @return cita a la que corresponde el cobro
     */
    public Cita getCita() {
        return cita;
    }

    /**
     * @param total total a cobrar
     * @throws IllegalArgumentException si el total es menor a 0
     */
    public void setTotal(double total) {
        if (total < 0) {
            throw new IllegalArgumentException("El total no puede ser menor a 0");
        }
        this.total = total;
    }

    /**
     * @return total cobrado
     */
    public double getTotal() {
        return total;
    }

    /**
     * @return fecha del cobro
     */
    public String getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "Comprobante #" + numero + "\nFecha" + fecha + "\nMascota: " + cita.getMascota().getNombreMascota()
                + "\nTotal: $" + String.format("%.2f", total);
    }
}
