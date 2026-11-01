package modelo.servicios;
/**
 * Interfaz para calcular el costo de los servicios
 */
public interface Facturable {

    /**
     * Calcula el costo del servicio
     * @return costo en pesos, no se permite negativos
     */
    double calcularCosto();
}
