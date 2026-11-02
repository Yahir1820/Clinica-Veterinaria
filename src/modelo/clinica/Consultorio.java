package modelo.clinica;
/**
 * Consultorio numerado de la clínica
 */

public class Consultorio {
    protected int numeroConsultorio;

    /**
     * Crea un consultorio con su número.
     *
     * @param numeroConsultorio número del consultorio
     */
    public Consultorio(int numeroConsultorio) {
        setNumeroConsultorio(numeroConsultorio);
    }

    /**
     * @param numeroConsultorio
     * * @throws IllegalArgumentException si el número de consultorio no es válido
     */
    public void setNumeroConsultorio(int numeroConsultorio) {
        if (numeroConsultorio <= 0) {
            throw new IllegalArgumentException("El número de consultorio debe ser mayor que cero");
        }
        this.numeroConsultorio = numeroConsultorio;
    }

    @Override
    public String toString() {
        return "Consultorio " + numeroConsultorio;
    }
}
