package modelo.clinica;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import modelo.mascotas.Mascota;
import modelo.personas.Dueno;
import modelo.personas.Veterinario;
import modelo.servicios.Facturable;
import modelo.servicios.Servicio;

/**
 * Coordina el sistema entre dueños, mascotas, personal, consultorios, citas y cobros
 */
public class Clinica {

    protected String nombre;
    protected List<Consultorio> consultorios = new ArrayList<>();
    protected List<Veterinario> veterinarios = new ArrayList<>();
    protected List<Dueno> duenos = new ArrayList<>();
    protected List<Mascota> mascotas = new ArrayList<>();
    protected List<Cita> citas = new ArrayList<>();
    protected List<Comprobante> comprobantes = new ArrayList<>();

    /**
     * Crea la clínica PetCare con tres consultorios
     * */
    public Clinica() {
        this("PetCare", 3);
    }

    /**
     * Crea una clínica con un número determinado de consultorios
     *
     * @param nombre nombre de la clínica
     * @param numeroConsultorios cantidad de consultorios a crear, mayor que cero
     */
    public Clinica(String nombre, int numeroConsultorios) {
        setNombre(nombre);
        if (numeroConsultorios <= 0) {
            throw new IllegalArgumentException("La clínica debe tener al menos un consultorio");
        }
        for (int i = 1; i <= numeroConsultorios; i++) {
            consultorios.add(new Consultorio(i));
        }
    }

    /**
     * Registra un dueño en la clínica
     * @param dueno dueño a registrar
     */
    public void registrarDueno(Dueno dueno) {
        if (dueno == null) {
            throw new IllegalArgumentException("El dueño no puede ser nulo");
        }
        duenos.add(dueno);
    }

    /**
     * Registra una mascota
     * Su dueño debe estar registrado en la clínica
     *
     * @param mascota mascota a registrar, no nula
     * @throws IllegalArgumentException si la mascota es nula o su dueño no está registrado
     */
    public void registrarMascota(Mascota mascota) {
        if (mascota == null) {
            throw new IllegalArgumentException("La mascota no puede ser nula");
        }
        if (!duenos.contains(mascota.getDueno())) {
            throw new IllegalArgumentException("El dueño de la mascota no está registrado");
        }
        mascotas.add(mascota);
    }

    /**
     * Registra un veterinario que ya existe
     * @param veterinario veterinario a asignar
     */
    public void registrarVeterinario(Veterinario veterinario) {
        if (veterinario == null) {
            throw new IllegalArgumentException("El veterinario no puede ser nulo");
        }
        veterinarios.add(veterinario);
    }

    /**
     * Agenda una cita
     * Revisa que el veterinario y el consultorio estén libres
     *
     * @param mascota mascota que asiste
     * @param veterinario veterinario que atiende
     * @param consultorio consultorio donde ocurre
     * @param fechaHora fecha y hora de la cita
     * @return la cita creada
     * @throws IllegalArgumentException si hay un conflicto de horario o un dato no está registrado
     */
    public Cita agendarCita(Mascota mascota, Veterinario veterinario, Consultorio consultorio, String fechaHora) {
        if (!mascotas.contains(mascota)) {
            throw new IllegalArgumentException("La mascota no está registrada");
        }
        if (!veterinarios.contains(veterinario)) {
            throw new IllegalArgumentException("El veterinario no está asignado a la clínica");
        }
        if (!consultorios.contains(consultorio)) {
            throw new IllegalArgumentException("El consultorio no pertenece a la clínica");
        }
        for (Cita existente : citas) {
            if (existente.getEstado() != EstadoCita.PROGRAMADA) {
                continue;
            }
            if (existente.getFechaHora().equals(fechaHora)) {
                if (existente.getVeterinario() == veterinario) {
                    throw new IllegalArgumentException("El veterinario ya tiene una cita programada a esa hora");
                }
                if (existente.getConsultorio() == consultorio) {
                    throw new IllegalArgumentException("El consultorio ya está ocupado a esa hora");
                }
            }
        }
        Cita cita = new Cita(mascota, veterinario, consultorio, fechaHora);
        citas.add(cita);
        return cita;
    }

    // Ninguna otra clase necesita ejecutarla, así que es privado
    private void validarCitaDeClinica(Cita cita) {
        if (cita == null || !citas.contains(cita)) {
            throw new IllegalArgumentException("La cita no pertenece a la clínica");
        }
    }

    /**
     * Atiende una cita: la marca como atendida y registra la consulta en el historial de la mascota
     *
     * @param cita cita a atender
     * @param servicio servicio dado
     * @param diagnostico diagnóstico del veterinario
     * @param tratamiento tratamiento indicado
     * @throws IllegalArgumentException si la cita no puede atenderse
     */
    public void atenderCita(Cita cita, Servicio servicio, String diagnostico, String tratamiento) {
        validarCitaDeClinica(cita);
        cita.marcarAtendida(servicio);
        Consulta consulta = new Consulta(cita.getFechaHora(), diagnostico, tratamiento);
        cita.getMascota().getHistorial().agregarConsulta(consulta);
    }

    /**
     * Cancela una cita programada
     *
     * @param cita cita a cancelar
     * @throws IllegalArgumentException si la cita no pertenece a la clínica o ya fue atendida
     */
    public void cancelarCita(Cita cita) {
        validarCitaDeClinica(cita);
        cita.cancelar();
    }

    /**
     * Genera el comprobante de una cita atendida
     *
     * @param cita cita atendida
     * @return comprobante con el total calculado por el servicio
     * @throws IllegalArgumentException si la cita no está atendida o ya tiene comprobante
     */
    public Comprobante generarComprobante(Cita cita) {
        validarCitaDeClinica(cita);
        if (cita.getEstado() != EstadoCita.ATENDIDA) {
            throw new IllegalArgumentException("Solo se genera comprobante de citas atendidas");
        }
        for (Comprobante c : comprobantes) {
            if (c.getCita() == cita) {
                throw new IllegalArgumentException("Esta cita ya tiene comprobante");
            }
        }
        Comprobante comprobante = new Comprobante(cita, cita.getServicio().calcularCosto());
        comprobantes.add(comprobante);
        return comprobante;
    }

    /**
     * Da de baja una mascota del sistema
     * Sus citas programadas se cancelan
     * Su historial clínico se elimina con ella
     * Los comprobantes ya emitidos se conservan
     *
     * @param mascota mascota a dar de baja
     * @throws IllegalArgumentException si la mascota no está registrada
     */
    public void darDeBajaMascota(Mascota mascota) {
        if (!mascotas.remove(mascota)) {
            throw new IllegalArgumentException("La mascota no está registrada");
        }
        for (Cita cita : citas) {
            if (cita.getMascota() == mascota && cita.getEstado() == EstadoCita.PROGRAMADA) {
                cita.cancelar();
            }
        }
    }

    /**
     * Consulta los cobros de una mascota
     *
     * @param mascota mascota a consultar
     * @return comprobantes de todas sus citas, en orden de emisión
     */
    public List<Comprobante> consultarCobros(Mascota mascota) {
        List<Comprobante> resultado = new ArrayList<>();
        for (Comprobante c : comprobantes) {
            if (c.getCita().getMascota() == mascota) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    /**
     * Consulta los cobros de un veterinario
     *
     * @param veterinario veterinario a consultar
     * @return comprobantes de las citas que atendió, en orden de emisión
     */
    public List<Comprobante> consultarCobros(Veterinario veterinario) {
        List<Comprobante> resultado = new ArrayList<>();
        for (Comprobante c : comprobantes) {
            if (c.getCita().getVeterinario() == veterinario) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    /**
     * Busca las citas de una fecha
     *
     * @param fecha día a buscar
     * @return citas de ese día
     */
    public List<Cita> buscarCitas(String fecha) {
        List<Cita> resultado = new ArrayList<>();
        for (Cita c : citas) {
            if (c.getFechaHora().startsWith(fecha)) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    /**
     * Busca las citas de un veterinario
     *
     * @param veterinario veterinario a buscar
     * @return citas asignadas a ese veterinario
     */
    public List<Cita> buscarCitas(Veterinario veterinario) {
        List<Cita> resultado = new ArrayList<>();
        for (Cita c : citas) {
            if (c.getVeterinario() == veterinario) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    /**
     * Suma lo cobrado a un veterinario
     * @param veterinario veterinario a consultar
     * @return total cobrado por sus citas
     */
    public double totalCobradoPor(Veterinario veterinario) {
        double total = 0;
        for (Comprobante c : consultarCobros(veterinario)) {
            Facturable cobro = c.getCita().getServicio();
            total += cobro.calcularCosto();
        }
        return total;
    }

    /**
     * @param nombre
     * @throws IllegalArgumentException si el nombre está vacío
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("La clínica debe tener nombre");
        }
        this.nombre = nombre;
    }

    /**
     * @return nombre de la clínica
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return consultorios de la clínica, de solo lectura
     */
    public List<Consultorio> getConsultorios() {
        return Collections.unmodifiableList(consultorios);
    }

    /**
     * @return veterinarios asignados, de solo lectura
     */
    public List<Veterinario> getVeterinarios() {
        return Collections.unmodifiableList(veterinarios);
    }

    /**
     * @return dueños registrados, de solo lectura
     */
    public List<Dueno> getDuenos() {
        return Collections.unmodifiableList(duenos);
    }

    /**
     * @return mascotas registradas, de solo lectura
     */
    public List<Mascota> getMascotas() {
        return Collections.unmodifiableList(mascotas);
    }

    /**
     * @return todas las citas, de solo lectura
     */
    public List<Cita> getCitas() {
        return Collections.unmodifiableList(citas);
    }

}