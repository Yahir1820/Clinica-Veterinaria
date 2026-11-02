package modelo.clinica;
import modelo.mascotas.Mascota;
import modelo.personas.Veterinario;
import modelo.servicios.Servicio;

/**
 * Cita que relaciona una mascota con un veterinario, en un consultorio y en una fecha y hora
 */
public class Cita {
    protected Mascota mascota;
    protected Veterinario veterinario;
    protected Consultorio consultorio;
    protected String fechaHora;
    protected EstadoCita estado;
    protected Servicio servicio;

    /**
     * Crea una cita en estado PROGRAMADA
     *
     * @param mascota mascota que asiste
     * @param veterinario veterinario que atiende
     * @param consultorio consultorio donde ocurre
     * @param fechaHora fecha y hora de la cita
     * @throws IllegalArgumentException si algún dato es nulo
     */
    public Cita(Mascota mascota, Veterinario veterinario, Consultorio consultorio, String fechaHora) {
        if (mascota == null || veterinario == null || consultorio == null || fechaHora == null) {
            throw new IllegalArgumentException("La cita requiere mascota, veterinario, consultorio y fecha");
        }
        this.mascota = mascota;
        this.veterinario = veterinario;
        this.consultorio = consultorio;
        this.fechaHora = fechaHora;
        this.estado = EstadoCita.PROGRAMADA;
    }

    /**
     * Marca la cita como atendida y guarda el servicio prestado
     *
     * @param servicio servicio realizado
     * @throws IllegalArgumentException si la cita está cancelada o ya fue atendida
     */
    public void marcarAtendida(Servicio servicio) {
        if (estado == EstadoCita.CANCELADA) {
            throw new IllegalArgumentException("Una cita cancelada no puede marcarse como atendida");
        }
        if (estado == EstadoCita.ATENDIDA) {
            throw new IllegalArgumentException("La cita ya fue atendida");
        }
        if (servicio == null) {
            throw new IllegalArgumentException("Debe indicar el servicio dado");
        }
        this.servicio = servicio;
        this.estado = EstadoCita.ATENDIDA;
    }

    /**
     * Cancela la cita
     * Una cita atendida ya no puede cancelarse
     * @throws IllegalArgumentException si la cita ya fue atendida
     */
    public void cancelar() {
        if (estado == EstadoCita.ATENDIDA) {
            throw new IllegalArgumentException("Una cita atendida no puede cancelarse");
        }
        this.estado = EstadoCita.CANCELADA;
    }

    /**
     * @return mascota de la cita
     */
    public Mascota getMascota() {
        return mascota;
    }

    /**
     * @return veterinario asignado
     */
    public Veterinario getVeterinario() {
        return veterinario;
    }

    /**
     * @return consultorio asignado
     */
    public Consultorio getConsultorio() {
        return consultorio;
    }

    /**
     * @return fecha y hora de la cita
     */
    public String getFechaHora() {
        return fechaHora;
    }

    /**
     * @return estado actual de la cita
     */
    public EstadoCita getEstado() {
        return estado;
    }

    /**
     @return servicio prestado, o null si la cita no ha sido atendida
     */
    public Servicio getServicio() {
        return servicio;
    }

    @Override
    public String toString() {
        return "Fecha: " + fechaHora + "\nMascota: " + mascota.getNombreMascota() + "\nVeterinario asignado: " + veterinario.nombreCompleto()
                + "\n" + consultorio + "\nEstado: " + estado;
    }

}
