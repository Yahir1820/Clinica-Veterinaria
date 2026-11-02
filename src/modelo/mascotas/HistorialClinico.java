package modelo.mascotas;
import modelo.clinica.Consulta;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Historial clínico de una mascota
 * Se crea junto con la mascota y se elimina con ella
 */

public class HistorialClinico {
    private final List<Consulta> consultas = new ArrayList<>();

    /**
     * Agrega una consulta al historial
     *
     * @param consulta consulta realizada
     * @throws IllegalArgumentException si la consulta es nula
     */
    public void agregarConsulta(Consulta consulta) {
        if (consulta == null) {
            throw new IllegalArgumentException("La consulta no puede ser nula");
        }
        consultas.add(consulta);
    }

    /**
     * Devuelve una copia de solo lectura de las consultas registradas
     * @return lista inmodificable de consultas
     */
    public List<Consulta> listarConsultas() {
        return Collections.unmodifiableList(consultas);
    }
}
