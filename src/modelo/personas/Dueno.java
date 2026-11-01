package modelo.personas;

/**
 * Dueño de una o más mascotas
 * Además de los datos de Persona, guarda su dirección
 */
public class Dueno extends Persona {
    private String direccion;

    /**
     * Crea un dueño con todos sus datos
     *
     * @param nombre    nombre
     * @param apellido  apellidos
     * @param telefono  teléfono de contacto
     * @param correo    correo electrónico
     * @param direccion domicilio del dueño
     */
    public Dueno(String nombre, String apellido, String telefono, String correo, String direccion) {
        super(nombre, apellido, telefono, correo);
        setDireccion(direccion);
    }

    /**
     * @param direccion
     * @throws IllegalArgumentException si algún dato está vacío
     */
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


