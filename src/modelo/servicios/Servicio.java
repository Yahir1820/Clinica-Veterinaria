package modelo.servicios;
/**
 * Clase abstracta padre de los servicios de la clínica
 * Cada servicio calcula su costo de forma distinta
 */

public abstract class Servicio implements Facturable {

    /**
     * Describe el servicio para mostrarlo al usuario
     * @return nombre y detalle del servicio
     */
    public abstract String describirServicio();

    @Override
    public abstract double calcularCosto();
}
