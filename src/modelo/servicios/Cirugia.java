package modelo.servicios;
/**
 * Cirugía: su costo depende de la complejidad y de las horas de quirófano
 */

public class Cirugia extends Servicio {
    /**
     * Costo de uso del quirófano
     * Costo de uso del quirófano por hora
     */
    public static final double TARIFA_SALA = 300.00;
    public static final double TARIFA_HORA = 50.00;

    private int complejidad;
    private double horasQuirofano;

    /**
     * Crea una cirugía
     * @param complejidad nivel de complejidad, de 1 a 5
     * @param horasQuirofano horas de quirófano, mayor que 0
     */
    public Cirugia(int complejidad, double horasQuirofano) {
        setComplejidad(complejidad);
        setHorasQuirofano(horasQuirofano);
    }

    /**
     * @param complejidad
     * @throws IllegalArgumentException si la complejidad no es válida
     */
    public void setComplejidad(int complejidad) {
        if  (complejidad < 1 ||  complejidad > 5) {
            throw new IllegalArgumentException("El complejidad debe ser entre 1 y 5");
        }
        this.complejidad = complejidad;
    }

    /**
     * @param horasQuirofano
     * @throws IllegalArgumentException si las horas en quirófano son menores o igual a 0
     */
    public void setHorasQuirofano(double horasQuirofano) {
        if  (horasQuirofano <= 0) {
            throw new IllegalArgumentException("Las horas en quirófano debe ser mayores a 0");
        }
        this.horasQuirofano = horasQuirofano;
    }

    @Override
    public String describirServicio() {
        return "Cirugía con complejidad de " + complejidad + " y " + horasQuirofano + " horas";
    }

    /**
     * Costo = cargo fijo de la sala + horas de quirófano × tarifa por hora × complejidad.
     * @return costo total de la cirugía
     */
    @Override
    public double calcularCosto() {
        return TARIFA_SALA + (horasQuirofano * TARIFA_HORA * complejidad);
    }
}
