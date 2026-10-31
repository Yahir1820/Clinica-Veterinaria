package modelo.personas;

public class Veterinario extends Personas {
    private String cedulaProfecional;
    private String especialidad;

    public Veterinario(String nombre, String apellido, String telefono, String correo, String cedulaProfecional, String especialidad) {
        super(nombre, apellido, telefono, correo);
        setCedulaProfecional(cedulaProfecional);
        setEspecialidad(especialidad);
    }

    public String getCedulaProfecional() {
        return cedulaProfecional;
    }
    public void setCedulaProfecional(String cedulaProfecional) {
        if  (cedulaProfecional == null || cedulaProfecional.trim().isEmpty()) {
            throw new IllegalArgumentException("La Cedula Profecional es obligatoria");
        }
        this.cedulaProfecional = cedulaProfecional;
    }

    public String getEspecialidad() {
        return especialidad;
    }
    public void setEspecialidad(String especialidad) {
        if (especialidad == null || especialidad.trim().isEmpty()) {
            throw new IllegalArgumentException("La Especialidad es obligatoria");
        }
        this.especialidad = especialidad;
    }

    @Override
    public String mostrarDatos() {
        return "Veterinario: " + nombreCompleto() + "\nTelefono: " + telefono + "\nCorreo: " + correo
                + "\nCedula Profecional: " + cedulaProfecional + "\nEspecialidad: " + especialidad;
    }

    @Override
    public String toString() {
        return mostrarDatos();
    }

}
