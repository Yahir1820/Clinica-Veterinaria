package modelo.clinica;
import java.time.LocalDate;

/**
 * Registro clínico de una consulta
 * Existe dentro del historial de una mascota
 */

public class Consulta {
    protected String fecha;
    protected String diagnostico;
    protected String tratamiento;

    /**
     * Crea un registro de consulta
     *
     * @param fecha fecha de la atención
     * @param diagnostico diagnóstico del veterinario
     * @param tratamiento tratamiento indicado
     */
    public Consulta(String fecha, String diagnostico, String tratamiento) {
        setFecha(fecha);
        setDiagnostico(diagnostico);
        setTratamiento(tratamiento);
    }

    /**
     * @param fecha fecha de la atención
     * @throws IllegalArgumentException si la consulta está vacía
     */
    public void setFecha(String fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha de la consulta no puede estar vacía");
        }
        this.fecha = fecha;
    }

    /**
     * @param diagnostico diagnóstico del veterinario
     * @throws IllegalArgumentException si el diagnostico está vacío
     */
    public void setDiagnostico(String diagnostico){
        if (diagnostico == null ||  diagnostico.isBlank()) {
            throw new IllegalArgumentException("El diagnostico no puede estar vacío");
        }
        this.diagnostico = diagnostico;
    }

    /**
     * @param tratamiento tratamiento indicado
     * @throws IllegalArgumentException si el tratamiento está vacío
     */
    public void setTratamiento(String tratamiento){
        if (tratamiento == null ||  tratamiento.isBlank()) {
            throw new IllegalArgumentException("El tratamiento no puede estar vacío");
        }
        this.tratamiento = tratamiento;
    }

    @Override
    public String toString() {
        return "Fecha: " + fecha + "\nDiagnóstico: " + diagnostico + "\nTratamiento: " + tratamiento;
    }
}
