package modelo.personas;

/**
 * Veterinario de la clínica.
 * Existe de forma independiente: puede trabajar en otra clínica
 */

public class Veterinario extends Persona {
    private String cedulaProfesional;
    private String especialidad;

    /**
     * Crea un veterinario con su cédula y especialidad
     *
     * @param nombre            nombre
     * @param apellido          apellidos
     * @param telefono          teléfono de contacto
     * @param correo            correo electrónico
     * @param cedulaProfesional cédula profesional
     * @param especialidad      especialidad clínica
     */

    public Veterinario(String nombre, String apellido, String telefono, String correo, String cedulaProfesional, String especialidad) {
        super(nombre, apellido, telefono, correo);
        setCedulaProfecional(cedulaProfesional);
        setEspecialidad(especialidad);
    }

    /**
     * @param cedulaProfesional
     * @throws IllegalArgumentException si algún dato está vacío
     */
    public void setCedulaProfecional(String cedulaProfesional) {
        if  (cedulaProfesional == null || cedulaProfesional.trim().isEmpty()) {
            throw new IllegalArgumentException("La Cedula Profecional es obligatoria");
        }
        this.cedulaProfesional = cedulaProfesional;
    }

    /**
     * @param especialidad
     * @throws IllegalArgumentException si algún dato está vacío
     */
    public void setEspecialidad(String especialidad) {
        if (especialidad == null || especialidad.trim().isEmpty()) {
            throw new IllegalArgumentException("La Especialidad es obligatoria");
        }
        this.especialidad = especialidad;
    }

    @Override
    public String mostrarDatos() {
        return "Veterinario: " + nombreCompleto() + "\nTelefono: " + telefono + "\nCorreo: " + correo
                + "\nCedula Profesional: " + cedulaProfesional + "\nEspecialidad: " + especialidad;
    }

    @Override
    public String toString() {
        return mostrarDatos();
    }

}
