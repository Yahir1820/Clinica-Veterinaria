package modelo.personas;

public class Dueno extends Personas{
    private String direccion;

    public Dueno(String nombre, String apellido, String telefono, String correo, String direccion) {
        super(nombre, apellido, telefono, correo);
        setDireccion(direccion);
    }

    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        if (direccion == null || direccion.trim().isEmpty()) {
            throw new IllegalArgumentException("La dirección es obligatorio");
        }
        this.direccion = direccion;
    }

    @Override
    public String mostrarDatos() {
        return "Dueño: " + nombreCompleto() + "\nTelefono: " + telefono + "\nDireccion: "
                + direccion + "\nCorreo: " + correo;
    }

    @Override
    public String toString() {
        return mostrarDatos();
    }
}


