package app;

import modelo.clinica.*;
import modelo.mascotas.Mascota;
import modelo.mascotas.Perro;
import modelo.mascotas.Gato;
import modelo.mascotas.AveExotica;
import modelo.personas.Dueno;
import modelo.personas.Veterinario;
import modelo.servicios.Servicio;
import modelo.servicios.ConsultaGeneral;
import modelo.servicios.Vacunacion;
import modelo.servicios.Cirugia;

import java.util.List;
import java.util.Scanner;

/**
 * Clase principal que ejecuta la aplicación de la clínica
 * Muestra el menú interactivo hasta que el usuario elige salir
 */
public class Main {

    /**
     * Función principal de entrada para la ejecución de la aplicación
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        Clinica clinica = new Clinica("PetCare Central", 3);
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            mostrarMenu();
            try {
                if (scanner.hasNextInt()) {
                    opcion = scanner.nextInt();
                    scanner.nextLine();
                } else {
                    System.out.println("\nIngrese un número válido del menú");
                    scanner.next();
                    continue;
                }

                switch (opcion) {
                    case 1:
                        registrarDuenoYMascota(clinica, scanner);
                        break;
                    case 2:
                        registrarMascota(clinica, scanner);
                        break;
                    case 3:
                        mostrarDuenos(clinica);
                        break;
                    case 4:
                        registrarVeterinario(clinica, scanner);
                        break;
                    case 5:
                        mostrarVeterinarios(clinica);
                        break;
                    case 6:
                        agendarCita(clinica, scanner);
                        break;
                    case 7:
                        mostrarCitas(clinica, scanner);
                        break;
                    case 8:
                        atenderOCancelarCita(clinica, scanner);
                        break;
                    case 9:
                        generarComprobanteCita(clinica, scanner);
                        break;
                    case 10:
                        consultarHistorial(clinica, scanner);
                        break;
                    case 11:
                        listarMascotasYSonido(clinica);
                        break;
                    case 12:
                        verTotalCobradoVeterinario(clinica, scanner);
                        break;
                    case 13:
                        darDeBajaMascota(clinica, scanner);
                        break;
                    case 14:
                        System.out.println("\nSaliendo...");
                        break;
                    default:
                        System.out.println("\nOpción fuera de rango, elija un número entre 1 y 14");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("\nError: "+ e.getMessage());
            } catch (Exception e) {
                System.out.println("\nError: " + e.getMessage());
            }

        } while (opcion != 14);
        scanner.close();
    }

    /**
     * Imprime en la consola las opciones disponibles del sistema de gestión
     */
    private static void mostrarMenu() {
        System.out.println("\n=== SISTEMA DE GESTIÓN VETERINARIA - PETCARE ===");
        System.out.println("1. Registrar dueño y mascota");
        System.out.println("2. Registrar mascota y asociarlo a un dueño existente");
        System.out.println("3. Listar todos los dueños");
        System.out.println("4. Registrar veterinario y asignarlo a la clínica");
        System.out.println("5. Listar veterinarios");
        System.out.println("6. Agendar una cita");
        System.out.println("7. Listar todas las citas de la clínica");
        System.out.println("8. Atender o cancelar una cita");
        System.out.println("9. Generar el comprobante de una cita atendida");
        System.out.println("10. Consultar el historial clínico de una mascota");
        System.out.println("11. Listar mascotas y su sonido");
        System.out.println("12. Ver el total cobrado por veterinario");
        System.out.println("13. Dar de baja una mascota");
        System.out.println("14. Salir");
        System.out.print("Elija una opción: ");
    }

    /**
     * Solicita por consola los datos personales y de contacto para registrar un nuevo dueño
     *
     * @instancia clinica la clínica donde se registrará el dueño
     * @param scanner instancia de Scanner para leer la entrada del usuario
     * @return el objeto Dueno creado y registrado
     */
    private static Dueno registrarDueno(Clinica clinica, Scanner scanner) {
        System.out.println("\n--- REGISTRO DE DUEÑO ---");
        System.out.print("Nombre del dueño: ");
        String nombreDueno = scanner.nextLine();
        System.out.print("Apellidos del dueño: ");
        String apellidoDueno = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefonoDueno = scanner.nextLine();
        System.out.print("Correo: ");
        String correoDueno = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccionDueno = scanner.nextLine();

        Dueno dueno = new Dueno(nombreDueno, apellidoDueno, telefonoDueno, correoDueno, direccionDueno);
        clinica.registrarDueno(dueno);
        System.out.println("Dueño registrado con éxito");
        return dueno;
    }

    /**
     * Muestra la lista de dueños existentes y permite registrar una nueva mascota asociada a uno de ellos.
     *
     * @param clinica la clínica que contiene los registros
     * @param scanner instancia de Scanner para leer la entrada del usuario
     * @throws IllegalArgumentException si no hay dueños registrados o el índice seleccionado es inválido
     */
    private static void registrarMascota(Clinica clinica, Scanner scanner) {
        System.out.println("\n--- REGISTRO DE MASCOTA (DUEÑO EXISTENTE) ---");
        List<Dueno> duenos = clinica.getDuenos();
        if (duenos.isEmpty()) {
            throw new IllegalArgumentException("No hay dueños registrados en el sistema. Debe registrar un dueño primero.");
        }

        System.out.println("Seleccione el dueño de la mascota:");
        for (int i = 0; i < duenos.size(); i++) {
            System.out.println((i + 1) + ". " + duenos.get(i).nombreCompleto() + " (Teléfono: " + duenos.get(i).getTelefono() + ")");
        }
        int iDueno = scanner.nextInt() - 1;
        scanner.nextLine();
        if (iDueno < 0 || iDueno >= duenos.size()) {
            throw new IllegalArgumentException("El índice del dueño seleccionado no es válido");
        }

        Dueno duenoSeleccionado = duenos.get(iDueno);
        crearYRegistrarMascota(clinica, scanner, duenoSeleccionado);
    }

    /**
     * Función auxiliar que solicita las características específicas según el tipo de mascota y la registra formalmente en la clínica vinculándola a un dueño dado
     *
     * @param clinica la clínica donde se registrará la mascota
     * @param scanner instancia de Scanner para leer la entrada del usuario
     * @param dueno el dueño al cual pertenecerá la mascota
     * @throws IllegalArgumentException si los datos ingresados son incorrectos o el tipo de mascota no es válido
     */
    private static void crearYRegistrarMascota(Clinica clinica, Scanner scanner, Dueno dueno) {
        System.out.print("Tipo de mascota (1. Perro, 2. Gato, 3. Ave Exótica): ");
        if (!scanner.hasNextInt()) {
            scanner.next();
            throw new IllegalArgumentException("Debe ingresar un número válido para el tipo de mascota.");
        }
        int tipoMascota = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nombre de la mascota: ");
        String nombreMascota = scanner.nextLine();
        System.out.print("Edad (años): ");
        if (!scanner.hasNextInt()) {
            scanner.next();
            throw new IllegalArgumentException("La edad debe ser un número entero");
        }
        int edadMascota = scanner.nextInt();
        System.out.print("Peso (kg): ");
        if (!scanner.hasNextDouble()) {
            scanner.next();
            throw new IllegalArgumentException("El peso debe ser un número válido");
        }
        double pesoMascota = scanner.nextDouble();
        scanner.nextLine();

        Mascota mascota;
        if (tipoMascota == 1) {
            System.out.print("Raza del perro: ");
            String raza = scanner.nextLine();
            System.out.print("Nivel de energía (Bajo, Medio, Alto): ");
            String energia = scanner.nextLine();
            mascota = new Perro(nombreMascota, edadMascota, pesoMascota, dueno, raza, energia);
        } else if (tipoMascota == 2) {
            System.out.print("¿Es doméstico? (1. Si, 2. No): ");
            if (!scanner.hasNextInt()) {
                scanner.next();
                throw new IllegalArgumentException("Debe indicar 1 o 2 para el valor doméstico");
            }
            boolean domestico = false;
            int opcionDomestico = scanner.nextInt();
            scanner.nextLine();

            if (opcionDomestico == 1) {
                domestico = true;
            } else if (opcionDomestico == 2) {
                domestico = false;
            }
            mascota = new Gato(nombreMascota, edadMascota, pesoMascota, dueno, domestico);
        } else if (tipoMascota == 3) {
            System.out.print("Longitud de alas (cm): ");
            if (!scanner.hasNextDouble()) {
                scanner.next();
                throw new IllegalArgumentException("La longitud de las alas debe ser un número válido");
            }
            double alas = scanner.nextDouble();
            scanner.nextLine();
            mascota = new AveExotica(nombreMascota, edadMascota, pesoMascota, dueno, alas);
        } else {
            throw new IllegalArgumentException("El tipo de mascota seleccionado no es válido");
        }

        clinica.registrarMascota(mascota);
        System.out.println("Mascota registrada y asociada al dueño con éxito");
    }

    /**
     * Hace el registro de un nuevo dueño y después procede a registrar una mascota para él
     *
     * @param clinica la clínica donde se realizarán los registros
     * @param scanner instancia de Scanner para leer la entrada del usuario
     */
    private static void registrarDuenoYMascota(Clinica clinica, Scanner scanner) {
        System.out.println("\n--- REGISTRO DE DUEÑO Y NUEVA MASCOTA ---");
        Dueno nuevoDueno = registrarDueno(clinica, scanner);

        System.out.println("\n--- REGISTRO DE LA MASCOTA PARA EL NUEVO DUEÑO ---");
        crearYRegistrarMascota(clinica, scanner, nuevoDueno);
    }

    /**
     * Muestra el listado completo de los dueños registrados en la clínica
     *
     * @param clinica la clínica que contiene la lista de dueños
     * @throws IllegalArgumentException si la lista de dueños se encuentra vacía
     */
    private static void mostrarDuenos(Clinica clinica) {
        System.out.println("\n--- DUEÑOS REGISTRADOS ---");
        List<Dueno> duenos = clinica.getDuenos();
        if (duenos.isEmpty()) {
            throw new IllegalArgumentException("No hay dueños registrados en el sistema");
        }

        for (int i = 0; i < duenos.size(); i++) {
            System.out.println((i + 1) + ". " + duenos.get(i));
            System.out.println("-----------------------------------");
        }
    }

    /**
     * Solicita los datos profesionales y de contacto para registrar un nuevo veterinario en el sistema
     *
     * @param clinica la clínica donde se asignará el veterinario
     * @param scanner instancia de Scanner para leer la entrada del usuario
     */
    private static void registrarVeterinario(Clinica clinica, Scanner scanner) {
        System.out.println("\n--- REGISTRO DE VETERINARIO ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellidos: ");
        String apellido = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Cédula profesional: ");
        String cedula = scanner.nextLine();
        System.out.print("Especialidad: ");
        String especialidad = scanner.nextLine();

        Veterinario vet = new Veterinario(nombre, apellido, telefono, correo, cedula, especialidad);
        clinica.registrarVeterinario(vet);
        System.out.println("Veterinario registrado y asignado a la clínica con éxito");
    }

    /**
     * Muestra el listado de todos los veterinarios que forman parte de la clínica
     *
     * @param clinica la clínica que almacena al personal médico
     */
    private static void mostrarVeterinarios(Clinica clinica) {
        System.out.println("\n--- VETERINARIOS DE LA CLÍNICA ---");
        List<Veterinario> veterinarios = clinica.getVeterinarios();
        if (veterinarios.isEmpty()) {
            System.out.println("No hay veterinarios registrados en la clínica.");
            return;
        }

        for (int i = 0; i < veterinarios.size(); i++) {
            System.out.println((i + 1) + ". " + veterinarios.get(i));
            System.out.println("-----------------------------------");
        }
    }

    /**
     * Permite agendar una cita seleccionando una mascota, un veterinario y un consultorio disponibles
     * Válida que existan los registros previos necesarios
     *
     * @param clinica la clínica encargada de coordinar las citas
     * @param scanner instancia de Scanner para leer la entrada del usuario
     * @throws IllegalArgumentException si falta información previa o los índices/fechas son inválidos
     */
    private static void agendarCita(Clinica clinica, Scanner scanner) {
        System.out.println("\n--- AGENDAR CITA ---");
        List<Mascota> mascotas = clinica.getMascotas();
        List<Veterinario> veterinarios = clinica.getVeterinarios();
        List<Consultorio> consultorios = clinica.getConsultorios();

        if (mascotas.isEmpty() || veterinarios.isEmpty() || consultorios.isEmpty()) {
            throw new IllegalArgumentException("Debe haber al menos una mascota, un veterinario y un consultorio registrados");
        }

        System.out.println("Seleccione la mascota:");
        for (int i = 0; i < mascotas.size(); i++) {
            System.out.println((i + 1) + ". " + mascotas.get(i).getNombreMascota() + " (Dueño: " + mascotas.get(i).getDueno().nombreCompleto() + ")");
        }
        int iMascotas = scanner.nextInt() - 1;
        scanner.nextLine();
        if (iMascotas < 0 || iMascotas >= mascotas.size()) {
            throw new IllegalArgumentException("El índice de la mascota seleccionado no es válido.");
        }

        System.out.println("Seleccione el veterinario:");
        for (int i = 0; i < veterinarios.size(); i++) {
            System.out.println((i + 1) + ". Dr(a). " + veterinarios.get(i).nombreCompleto() + " (" + veterinarios.get(i).getEspecialidad() + ")");
        }
        int iVeterinario = scanner.nextInt() - 1;
        scanner.nextLine();
        if (iVeterinario < 0 || iVeterinario >= veterinarios.size()) {
            throw new IllegalArgumentException("El índice del veterinario seleccionado no es válido");
        }

        System.out.println("Seleccione el consultorio:");
        for (int i = 0; i < consultorios.size(); i++) {
            System.out.println((i + 1) + ". " + consultorios.get(i));
        }
        int iConsultorio = scanner.nextInt() - 1;
        scanner.nextLine();
        if (iConsultorio < 0 || iConsultorio >= consultorios.size()) {
            throw new IllegalArgumentException("El índice del consultorio seleccionado no es válido");
        }

        System.out.print("Ingrese fecha y hora (Ej. 2026-10-10 10:00): ");
        String fechaHora = scanner.nextLine();

        Cita cita = clinica.agendarCita(mascotas.get(iMascotas), veterinarios.get(iVeterinario), consultorios.get(iConsultorio), fechaHora);
        System.out.println("Cita agendada exitosamente\n" + cita);
    }

    /**
     * Muestra por consola el listado de todas las citas agendadas, atendidas o canceladas en el sistema.
     *
     * @param clinica la clínica que contiene el registro de citas
     * @param scanner instancia de Scanner para leer la entrada del usuario
     * @throws IllegalArgumentException si no existen citas registradas
     */
    private static void mostrarCitas(Clinica clinica, Scanner scanner) {
        List<Cita> citas = clinica.getCitas();
        if (citas.isEmpty()) {
            throw new IllegalArgumentException("No hay citas registradas en el sistema");
        }

        for (int i = 0; i < citas.size(); i++) {
            System.out.println((i + 1) + ". " + citas.get(i));
            System.out.println("-----------------------------------");
        }
    }
    /**
     * Permite gestionar una cita existente seleccionándola de la lista para proceder a atenderla o cancelarla
     *
     * @param clinica la clínica que administra las citas y servicios
     * @param scanner instancia de Scanner para leer la entrada del usuario
     * @throws IllegalArgumentException si no hay citas, el índice es incorrecto o la acción no es válida
     */
    private static void atenderOCancelarCita(Clinica clinica, Scanner scanner) {
        System.out.println("\n--- ATENDER O CANCELAR CITA ---");
        List<Cita> citas = clinica.getCitas();
        if (citas.isEmpty()) {
            throw new IllegalArgumentException("No hay citas registradas en el sistema");
        }

        System.out.println("Listado de citas:");
        for (int i = 0; i < citas.size(); i++) {
            System.out.println((i + 1) + ". [" + citas.get(i).getEstado() + "] " + citas.get(i).getFechaHora() + " - Mascota: " + citas.get(i).getMascota().getNombreMascota() + " con Dr(a). " + citas.get(i).getVeterinario().nombreCompleto());
        }

        System.out.print("Seleccione el número de la cita: ");
        int iCita = scanner.nextInt() - 1;
        scanner.nextLine();

        if (iCita < 0 || iCita >= citas.size()) {
            System.out.println("Selección inválida");
            return;
        }

        Cita citaSeleccionada = citas.get(iCita);

        System.out.println("1. Atender cita");
        System.out.println("2. Cancelar cita");
        System.out.print("Elija una acción: ");
        int accion = scanner.nextInt();
        scanner.nextLine();

        if (accion == 1) {
            System.out.println("Seleccione el servicio prestado:");
            System.out.println("1. Consulta General");
            System.out.println("2. Vacunación");
            System.out.println("3. Cirugía");
            int tipoServicio = scanner.nextInt();
            scanner.nextLine();

            Servicio servicio = null;
            if (tipoServicio == 1) {
                servicio = new ConsultaGeneral();
            } else if (tipoServicio == 2) {
                System.out.print("Nombre de la vacuna: ");
                String nomVac = scanner.nextLine();
                System.out.print("Costo de la vacuna: ");
                double costVac = scanner.nextDouble();
                scanner.nextLine();
                servicio = new Vacunacion(nomVac, costVac);
            } else if (tipoServicio == 3) {
                System.out.print("Complejidad (1 al 5): ");
                int comp = scanner.nextInt();
                System.out.print("Horas de quirófano: ");
                double horas = scanner.nextDouble();
                scanner.nextLine();
                servicio = new Cirugia(comp, horas);
            } else {
                throw new IllegalArgumentException("Servicio no válido");
            }

            System.out.print("Diagnóstico: ");
            String diagnostico = scanner.nextLine();
            System.out.print("Tratamiento: ");
            String tratamiento = scanner.nextLine();

            clinica.atenderCita(citaSeleccionada, servicio, diagnostico, tratamiento);
            System.out.println("Cita atendida y registrada en el historial clínico");

        } else if (accion == 2) {
            clinica.cancelarCita(citaSeleccionada);
            System.out.println("Cita cancelada con éxito");
        } else {
            throw new IllegalArgumentException("Acción no válida");
        }
    }

    /**
     * Genera e imprime el comprobante de pago para una cita que previamente ha sido marcada como atendida
     *
     * @param clinica la clínica que procesa la facturación
     * @param scanner instancia de Scanner para leer la entrada del usuario
     * @throws IllegalArgumentException si no hay citas, el índice es inválido o la cita no está atendida
     */
    private static void generarComprobanteCita(Clinica clinica, Scanner scanner) {
        System.out.println("\n--- GENERAR COMPROBANTE ---");
        List<Cita> citas = clinica.getCitas();
        if (citas.isEmpty()) {
            throw new IllegalArgumentException("No hay citas registradas");
        }

        System.out.println("Seleccione la cita atendida:");
        for (int i = 0; i < citas.size(); i++) {
            System.out.println((i + 1) + ". [" + citas.get(i).getEstado() + "] Fecha: " + citas.get(i).getFechaHora() + " - Mascota: " + citas.get(i).getMascota().getNombreMascota());
        }
        int iCita = scanner.nextInt() - 1;
        scanner.nextLine();

        if (iCita < 0 || iCita >= citas.size()) {
            throw new IllegalArgumentException("Índice inválido");
        }

        Cita citaSeleccionada = citas.get(iCita);
        if (citaSeleccionada.getEstado() != EstadoCita.ATENDIDA) {
            throw new IllegalArgumentException("Solo se pueden generar comprobantes de citas que ya han sido atendidas.");
        }

        Comprobante comprobante = clinica.generarComprobante(citas.get(iCita));
        System.out.println("\nComprobante generado exitosamente:\n" + comprobante);
    }

    /**
     * Consulta y despliega detalladamente el historial clínico (consultas previas) de una mascota seleccionada
     *
     * @param clinica la clínica que almacena las mascotas
     * @param scanner instancia de Scanner para leer la entrada del usuario
     * @throws IllegalArgumentException si no hay mascotas o el historial se encuentra vacío
     */
    private static void consultarHistorial(Clinica clinica, Scanner scanner) {
        System.out.println("\n--- CONSULTAR HISTORIAL CLÍNICO ---");
        List<Mascota> mascotas = clinica.getMascotas();
        if (mascotas.isEmpty()) {
            throw new IllegalArgumentException("No hay mascotas registradas");
        }

        System.out.println("Seleccione la mascota:");
        for (int i = 0; i < mascotas.size(); i++) {
            System.out.println((i + 1) + ". " + mascotas.get(i).getNombreMascota() + " (Dueño: " + mascotas.get(i).getDueno().nombreCompleto() + ")");
        }
        int iMascota = scanner.nextInt() - 1;
        scanner.nextLine();

        if (iMascota < 0 || iMascota >= mascotas.size()) {
            throw new IllegalArgumentException("Mascota inválida.");
        }

        Mascota m = mascotas.get(iMascota);
        System.out.println("\nHistorial Clínico de: " + m.getNombreMascota());
        List<Consulta> consultas = m.getHistorial().listarConsultas();
        if (consultas.isEmpty()) {
            throw new IllegalArgumentException("El historial clínico está vacío");
        } else {
            for (int i = 0; i < consultas.size(); i++) {
                System.out.println("--- Consulta #" + (i + 1) + " ---\n" + consultas.get(i));
            }
        }
    }

    /**
     * Lista todas las mascotas registradas en la clínica
     *
     * @param clinica la clínica que resguarda a las mascotas
     * @throws IllegalArgumentException si no existen mascotas registradas
     */
    private static void listarMascotasYSonido(Clinica clinica) {
        System.out.println("\n--- LISTADO DE MASCOTAS Y SONIDO ---");
        List<Mascota> mascotas = clinica.getMascotas();
        if (mascotas.isEmpty()) {
            throw new IllegalArgumentException("No hay mascotas registradas");
        }

        for (Mascota mascota : mascotas) {
            System.out.println(mascota);
            System.out.println("Sonido: " + mascota.emitirSonido());
            System.out.println("-----------------------------------");
        }
    }

    /**
     * Calcula y muestra el monto económico total cobrado por los servicios prestados por un veterinario específico.
     *
     * @param clinica la clínica que procesa los cobros
     * @param scanner instancia de Scanner para leer la entrada del usuario
     * @throws IllegalArgumentException si no hay veterinarios registrados o la selección es inválida
     */
    private static void verTotalCobradoVeterinario(Clinica clinica, Scanner scanner) {
        System.out.println("\n--- TOTAL COBRADO POR VETERINARIO ---");
        List<Veterinario> veterinarios = clinica.getVeterinarios();
        if (veterinarios.isEmpty()) {
            throw new IllegalArgumentException("No hay veterinarios registrados");
        }

        System.out.println("Seleccione el veterinario:");
        for (int i = 0; i < veterinarios.size(); i++) {
            System.out.println((i + 1) + ". Doctor(a): " + veterinarios.get(i).nombreCompleto());
        }
        int iVeterinario = scanner.nextInt() - 1;
        scanner.nextLine();

        if (iVeterinario < 0 || iVeterinario >= veterinarios.size()) {
            throw new IllegalArgumentException("Veterinario inválido");
        }

        Veterinario vet = veterinarios.get(iVeterinario);
        double total = clinica.totalCobradoPor(vet);
        System.out.println("El total cobrado por el/la Doctor(a) " + vet.nombreCompleto() + " es: $" + String.format("%.2f", total));
    }

    /**
     * Da de baja una mascota seleccionada del sistema, cancelando sus citas pendientes y eliminando su historial clínico asociado
     *
     * @param clinica la clínica encargada de actualizar el registro
     * @param scanner instancia de Scanner para leer la entrada del usuario
     * @throws IllegalArgumentException si no hay mascotas registradas o el índice es incorrecto
     */
    private static void darDeBajaMascota(Clinica clinica, Scanner scanner) {
        System.out.println("\n--- DAR DE BAJA MASCOTA ---");
        List<Mascota> mascotas = clinica.getMascotas();
        if (mascotas.isEmpty()) {
            throw new IllegalArgumentException("No hay mascotas registradas");
        }

        System.out.println("Seleccione la mascota a dar de baja:");
        for (int i = 0; i < mascotas.size(); i++) {
            System.out.println((i + 1) + ". " + mascotas.get(i).getNombreMascota());
        }
        int iMascotas = scanner.nextInt() - 1;
        scanner.nextLine();

        if (iMascotas < 0 || iMascotas >= mascotas.size()) {
            throw new IllegalArgumentException("Mascota inválida");
        }

        Mascota mascota = mascotas.get(iMascotas);
        clinica.darDeBajaMascota(mascota);
        System.out.println("La mascota ha sido dada de baja del sistema con éxito");
    }
}