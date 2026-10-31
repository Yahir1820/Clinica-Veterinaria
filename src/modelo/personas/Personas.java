package modelo.personas;

/**
 * Clase abstracta padre para todas las personas de la clínica
 * Tiene los datos comunes de dueños, veterinarios y recepcionistas
 */

public abstract class Personas {
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

    public Personas(String nombre, String apellido, String telefono, String correo) {
        setNombre(nombre);
        setApellido(apellido);
        setTelefono(telefono);
        setCorreo(correo);
    }

    public Personas() {
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
     * @return nombre de la persona
     */
    public String getNombre() {
        return nombre;
    }

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
     * @return apellido de la persona
     */
    public String getApellido() {
        return apellido;
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
     * @return teléfono de la persona
     */
    public String getTelefono() {
        return telefono;
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
     * @return correo de la persona
     */
    public String getCorreo() {
        return correo;
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
