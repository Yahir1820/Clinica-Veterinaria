package modelo.personas;

/**
 * Clase abstracta padre para todas las personas de la clínica
 * Tiene los datos comunes de dueños, veterinarios y recepcionistas
 */

public abstract class Persona {
    protected String nombre;
    protected String apellido;
    protected String telefono;
    protected String correo;

    /**
     * Crea una persona validando que sus datos no estén vacíos
     *
     * @param nombre   nombre
     * @param apellido apellidos
     * @param telefono teléfono de contacto
     * @param correo   correo electrónico
     */

    public Persona(String nombre, String apellido, String telefono, String correo) {
        setNombre(nombre);
        setApellido(apellido);
        setTelefono(telefono);
        setCorreo(correo);
    }

    public Persona() {
        this.nombre = " ";
        this.apellido = " ";
        this.telefono = " ";
        this.correo = " ";
    }

    /**
     * Solo las personas del paquete modelo.personas pueden acceder a los atributos y métodos de la clase
     * Los getters son para retornar los valores de los atributos
     * Los setters son para ingresar los valores, además cuentan con una condicional de que si ingresan un dato vacío, este tira una excepción
     */

    /**
     * @param nombre
     * @throws IllegalArgumentException si algún dato está vacío
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        this.nombre = nombre;
    }

    /**
     * @param apellido
     * @throws IllegalArgumentException si algún dato está vacío
     */
    public void setApellido(String apellido) {
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido es obligatorio");
        }
        this.apellido = apellido;
    }

    /**
     * @param telefono
     * @throws IllegalArgumentException si algún dato está vacío
     */
    public void setTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("El telefono es obligatorio");
        }
        this.telefono = telefono;
    }

    /**
     * @param correo
     * @throws IllegalArgumentException si algún dato está vacío
     */
    public void setCorreo(String correo) {
        if (correo == null || correo.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo es obligatorio");
        }
        this.correo = correo;
    }

    /**
     * @return nombre completo de la persona
     */
    public String nombreCompleto() {
        return nombre + " " + apellido;
    }

    /**
     * Muestra los datos de cada tipo de persona.
     * @return Datos de la persona
     */
    public abstract String mostrarDatos();

}
