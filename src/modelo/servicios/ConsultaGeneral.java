package modelo.servicios;
/**
 * Consulta general: su costo es la tarifa base
 */

public class ConsultaGeneral extends Servicio {
    /**
     * Tarifa base de una consulta general, en pesos.
     */
    public static final double TARIFA_BASE = 250.00;

    /**
     * Crea una consulta general con la tarifa base.
     */
    public ConsultaGeneral() {}

    @Override
    public double calcularCosto() {
        return TARIFA_BASE;
    }

    @Override
    public String describirServicio() {
        return "Consulta general";
    }
}
