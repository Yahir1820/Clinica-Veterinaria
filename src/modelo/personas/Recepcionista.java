package modelo.personas;

/**
 * Recepcionista de la clínica, responsable de registrar dueños, mascotas y citas
 */

public class Recepcionista extends Personas {
    private String turno;

    /**
     * Crea un recepcionista con su turno
     *
     * @param nombre   nombre
     * @param apellido apellidos
     * @param telefono teléfono de contacto
     * @param correo   correo electrónico
     * @param turno    turno de trabajo (matutino, vespertino, etc.)
     */

    public Recepcionista(String nombre, String apellido, String telefono, String correo, String turno) {
        super(nombre, apellido, telefono, correo);
        setTurno(turno);
    }

    /**
     * @return turno
     */
    public String getTurno() {
        return turno;
    }

    /**
     * @param turno
     * @throws IllegalArgumentException si algún dato está vacío
     */
    public void setTurno(String turno) {
        if (turno == null || turno.trim().isEmpty()) {
            throw new IllegalArgumentException("El turno es obligatorio");
        }
        this.turno = turno;
    }

    @Override
    public String mostrarDatos(){
        return "Recepcionista: " + nombreCompleto() + "\nTelefono: " + telefono + "\nCorreo: " + correo
            + "\nTurno: " + turno;
    }

    @Override
    public String toString() {
        return mostrarDatos();
    }
}
