package modelo.personas;

public class Recepcionista extends Personas {
    private String turno;

    public Recepcionista(String nombre, String apellido, String telefono, String correo, String turno) {
        super(nombre, apellido, telefono, correo);
        setTurno(turno);
    }

    public String getTurno() {
        return turno;
    }
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
