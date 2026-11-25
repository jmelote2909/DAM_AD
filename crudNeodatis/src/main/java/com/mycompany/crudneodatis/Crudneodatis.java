/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crudneodatis;

import java.util.*;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

public class Crudneodatis {

    private static final String basedatos = "database.odb";
    private static ODB odb;

    public static void main(String[] args) {
        try {
            odb = ODBFactory.open(basedatos);

            Scanner scanner = new Scanner(System.in);
            int option;
            do {
                System.out.println("\nMenu:");
                System.out.println("1. Crear Grupo");
                System.out.println("2. Crear Alumno");
                System.out.println("3. Crear Localidad");
                System.out.println("4. Ver Grupos");
                System.out.println("5. Ver Alumnos");
                System.out.println("6. Ver Localidades");
                System.out.println("7. Actualizar");
                System.out.println("8. Consultas");
                System.out.println("9. Salir");
                System.out.print("Seleccione una opción: ");
                option = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                switch (option) {
                    case 1:
                        creaGrupo(scanner);
                        break;
                    case 2:
                        creaAlumno(scanner);
                        break;
                    case 3:
                        creaLocalidad(scanner);
                        break;
                    case 4:
                        verGrupos();
                        break;
                    case 5:
                        verAlumnos();
                        break;
                    case 6:
                        verLocalidades();
                        break;
                    case 7:
                        actualizar(scanner);
                    case 8:
                        consultas();
                    case 9:

                        System.out.println("Saliendo...");
                        break;

                    default:
                        System.out.println("Opción no válida.");
                }
            } while (option != 9);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en la base de datos: " + e.getMessage());

        } finally {
            if (odb != null) {
                odb.close(); // Cierra la base de datos
            }
        }
    }
    // Crear un Grupo

    private static void creaGrupo(Scanner scanner) {
        System.out.print("Codigo de Grupo: ");
        int codigoGrupo = scanner.nextInt();
        scanner.nextLine();
        Contenedor<Grupo> grupoContenedor = new Contenedor<>();
        if (!compruebaGrupo(codigoGrupo, grupoContenedor)) {

            System.out.print("Nombre del grupo: ");
            String nombreGrupo = scanner.nextLine();

            Grupo grupo = new Grupo(nombreGrupo, codigoGrupo);
            odb.store(grupo);
            System.out.println("Grupo creado.");
        } else {
            System.out.println("El grupo ya existe");
        }
    }

    private static void creaAlumno(Scanner scanner) {

        System.out.print("código del alumno: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        if (!compruebaAlumno(codigo)) {
            System.out.print("Nombre del alumno: ");
            String nombreAlumno = scanner.nextLine();
            System.out.println("Apellidos del alumno:");
            String apellidoAlumno = scanner.nextLine();

            int codigoLocalidad;
            System.out.print("código de localidad: ");
            codigoLocalidad = scanner.nextInt();

            Contenedor<Localidad> localidadContenedor = new Contenedor<>();
            if (!compruebaLocalidad(codigoLocalidad, localidadContenedor)) {
                System.out.println("La localidad dada no existe");
                return;
            }

            scanner.nextLine();
            int codigoGrupo;
            System.out.print("código de grupo: ");
            codigoGrupo = scanner.nextInt();
            Contenedor<Grupo> grupoContenedor = new Contenedor<>();
            if (!compruebaGrupo(codigoGrupo, grupoContenedor)) {
                System.out.println("El grupo dado no existe");
                return;
            }
            Alumno alumno = new Alumno(nombreAlumno, apellidoAlumno, codigo, localidadContenedor.obtenerValor(),
                    grupoContenedor.obtenerValor());

            odb.store(alumno);
            System.out.println("Alumno creado.");
        } else {
            System.out.println("El alumno ya existe");
        }

    }

    private static boolean compruebaAlumno(int codAlumno) {
        Objects<Alumno> alumnos = odb.getObjects(Alumno.class);
        for (Alumno alumno : alumnos) {
            if (alumno.getCodAlumnno() == codAlumno) {
                return true;
            }
        }
        return false;
    }

    // Crear una Localidad
    private static void creaLocalidad(Scanner scanner) {
        Localidad l = null;
        System.out.print("Codigo de la localidad: ");
        int codigoPostal = scanner.nextInt();
        scanner.nextLine();
        Contenedor<Localidad> localidadContenedor = new Contenedor<>();
        if (!compruebaLocalidad(codigoPostal, localidadContenedor)) {
            System.out.print("Nombre de la localidad: ");
            String nombreLocalidad = scanner.nextLine();
            System.out.print("Nombre de la provincia: ");
            String nombreProvincia = scanner.nextLine();

            Localidad localidad = new Localidad(nombreLocalidad, nombreProvincia, codigoPostal);
            odb.store(localidad);
            System.out.println("Localidad creada.");
        } else {
            System.out.println("La localidad ya existe");
        }
    }
    // Ver Grupos

    private static void verGrupos() {
        Objects<Grupo> grupos = odb.getObjects(Grupo.class);
        for (Grupo grupo : grupos) {
            System.out.println(grupo);
        }
    }

    private static boolean compruebaGrupo(int codGrupo, Contenedor<Grupo> g) {
        System.out.println("estoy en compruebagrupo");
        Objects<Grupo> grupos = odb.getObjects(Grupo.class);
        for (Grupo grupo : grupos) {
            if (grupo.getCodGrupo() == codGrupo) {
                g.ponerValor(grupo);
                return true;
            }
        }
        return false;
    }

    // Ver Alumnos
    private static void verAlumnos() {
        Objects<Alumno> alumnos = odb.getObjects(Alumno.class);
        for (Alumno alumno : alumnos) {
            System.out.println(alumno);
        }
    }

    // Ver Localidades
    private static void verLocalidades() {
        Objects<Localidad> localidades = odb.getObjects(Localidad.class);
        for (Localidad localidad : localidades) {
            System.out.println(localidad);
        }
    }

    private static boolean compruebaLocalidad(int codLocalidad, Contenedor<Localidad> local) {
        Objects<Localidad> localidades = odb.getObjects(Localidad.class);
        for (Localidad localidad : localidades) {
            if (localidad.getCodPostal() == codLocalidad) {

                local.ponerValor(localidad);
                return true;
            }
        }
        return false;
    }

    private static void actualizar(Scanner scanner) {
        int option;
        do {
            System.out.println("\nSubmenú Actualizar:");
            System.out.println("1. Borrar Grupo");
            System.out.println("2. Borrar Localidad");
            System.out.println("3. Borrar Alumno");
            System.out.println("4. Modificar Grupo");
            System.out.println("5. Modificar Alumno");
            System.out.println("6. Modificar Localidad");
            System.out.println("7. Regresar");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (option) {
                case 1:
                    borrarGrupo(scanner);
                    break;
                case 2:
                    borrarLocalidad(scanner);
                    break;
                case 3:
                    borrarAlumno(scanner);
                    break;
                case 4:
                    modificarGrupo(scanner);
                    break;
                case 5:
                    modificarAlumno(scanner);
                    break;
                case 6:
                    modificarLocalidad(scanner);
                    break;
                case 7:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (option != 7);
    }
    // Borrar Grupo

    private static void borrarGrupo(Scanner scanner) {
        System.out.print("Ingrese el código del grupo a borrar: ");
        int codigoGrupo = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Objects<Grupo> grupos = odb.getObjects(Grupo.class);
        for (Grupo grupo : grupos) {
            if (grupo.getCodGrupo() == codigoGrupo) {
                System.out.print("¿Desea borrar también los alumnos asociados a este grupo? (s/n): ");
                String respuesta = scanner.nextLine();
                if (respuesta.equalsIgnoreCase("s")) {
                    // Buscar y borrar los alumnos asociados a este grupo
                    Objects<Alumno> alumnos = odb.getObjects(Alumno.class);
                    for (Alumno alumno : alumnos) {
                        if (alumno.getGrupo() != null && alumno.getGrupo().getCodGrupo() == codigoGrupo) {
                            odb.delete(alumno); // Eliminar alumno de la base de datos
                            System.out.println("Alumno " + alumno.getNombre() + " borrado.");
                        }
                    }
                }
                odb.delete(grupo); // Eliminar grupo de la base de datos
                System.out.println("Grupo borrado.");
                odb.commit();
                return;
            }
        }
        System.out.println("Grupo no encontrado.");
    }
    // Borrar Localidad

    private static void borrarLocalidad(Scanner scanner) {
        System.out.print("Ingrese el código postal de la localidad a borrar: ");
        int codigoLocalidad = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Objects<Localidad> localidades = odb.getObjects(Localidad.class);
        for (Localidad localidad : localidades) {
            if (localidad.getCodPostal() == codigoLocalidad) {
                System.out.print("¿Desea borrar también los alumnos asociados a esta localidad? (s/n): ");
                String respuesta = scanner.nextLine();
                if (respuesta.equalsIgnoreCase("s")) {
                    // Buscar y borrar los alumnos asociados a esta localidad
                    Objects<Alumno> alumnos = odb.getObjects(Alumno.class);
                    for (Alumno alumno : alumnos) {
                        if (alumno.getLocalidad() != null && alumno.getLocalidad().getCodPostal() == codigoLocalidad) {
                            odb.delete(alumno); // Eliminar alumno de la base de datos
                            System.out.println("Alumno " + alumno.getNombre() + " borrado.");
                        }
                    }
                    odb.delete(localidad); // Eliminar localidad de la base de datos
                    System.out.println("Localidad borrada.");
                    odb.commit();
                    return;
                }
            } else {
                System.out.println("Localidad no encontrada.");
            }
        }
    }
    // Borrar Alumno

    private static void borrarAlumno(Scanner scanner) {
        System.out.print("Ingrese el código del alumno a borrar: ");
        int codigoAlumno = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Objects<Alumno> alumnos = odb.getObjects(Alumno.class);
        for (Alumno alumno : alumnos) {
            if (alumno.getCodAlumnno() == codigoAlumno) {
                odb.delete(alumno); // Eliminar alumno de la base de datos
                System.out.println("Alumno borrado.");
                odb.commit();
                return;
            }
        }
        System.out.println("Alumno no encontrado.");
    }
    // Modificar Grupo

    private static void modificarGrupo(Scanner scanner) {
        System.out.print("Ingrese el código del grupo a modificar: ");
        int codigoGrupo = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Objects<Grupo> grupos = odb.getObjects(Grupo.class);
        for (Grupo grupo : grupos) {
            if (grupo.getCodGrupo() == codigoGrupo) {
                System.out.print("Nuevo nombre del grupo: ");
                String nuevoNombre = scanner.nextLine();
                grupo.setNombre(nuevoNombre);
                odb.store(grupo); // Modificar grupo en la base de datos
                System.out.println("Grupo modificado.");
                odb.commit();
                return;
            }
        }
        System.out.println("Grupo no encontrado.");
    }
    // Modificar Alumno

    private static void modificarAlumno(Scanner scanner) {
        System.out.print("Ingrese el código del alumno a modificar: ");
        int codigoAlumno = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Objects<Alumno> alumnos = odb.getObjects(Alumno.class);
        for (Alumno alumno : alumnos) {
            if (alumno.getCodAlumnno() == codigoAlumno) {
                System.out.println("Nuevo nombre del alumno: ");
                String nuevoNombre = scanner.nextLine();

                System.out.print("Nuevos apellidos del alumno: ");
                String nuevosApellidos = scanner.nextLine();
                alumno.setNombre(nuevoNombre, nuevosApellidos);
                odb.store(alumno); // Modificar alumno en la base de datos
                System.out.println("Alumno modificado.");
                odb.commit();
                return;
            }
        }
        System.out.println("Alumno no encontrado.");
    }
    // Modificar Localidad

    private static void modificarLocalidad(Scanner scanner) {
        System.out.print("Ingrese el código postal de la localidad a modificar: ");
        int codigoLocalidad = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Objects<Localidad> localidades = odb.getObjects(Localidad.class);
        for (Localidad localidad : localidades) {
            if (localidad.getCodPostal() == codigoLocalidad) {
                System.out.print("Nuevo nombre de la localidad: ");
                String nuevoNombre = scanner.nextLine();
                localidad.setNombre(nuevoNombre);
                System.out.print("Nueva provincia de la localidad: ");
                String nuevaProvincia = scanner.nextLine();
                localidad.setProvincia(nuevaProvincia);
                odb.store(localidad); // Modificar localidad en la base de datos
                System.out.println("Localidad modificada.");
                odb.commit();
                return;
            }
        }
        System.out.println("Localidad no encontrada.");
    }

    private static void consultas() {
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("\nSubmenú Consultas:");
            System.out.println("1. Alumnos de la localidad de Lucena");
            System.out.println("2. Alumnos del grupo 4A");
            System.out.println("3. Alumnos de Lucena y 4A");
            System.out.println("4. Volver al menú anterior");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (option) {
                case 1:
                    alumnosDeLucena();
                    break;
                case 2:
                    alumnosDeGrupo4A();
                    break;
                case 3:
                    alumnosDeLucenaYGrupo4A();
                    break;
                case 4:
                    System.out.println("Volviendo al menú anterior...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (option != 4);
    }

    /*
     * private static void alumnosDeLucena() {
     * System.out.println("\nConsultando alumnos de la localidad de Lucena...");
     * Objects<Alumno> alumnos = odb.getObjects(Alumno.class);
     * for (Alumno alumno : alumnos) {
     * if (alumno.getLocalidad() != null &&
     * alumno.getLocalidad().getNombre().equalsIgnoreCase("Lucena")) {
     * System.out.println(alumno);
     * }
     * }
     * }
     * private static void alumnosDeGrupo4A() {
     * System.out.println("\nConsultando alumnos del grupo 4A...");
     * Objects<Alumno> alumnos = odb.getObjects(Alumno.class);
     * for (Alumno alumno : alumnos) {
     * if (alumno.getGrupo() != null &&
     * alumno.getGrupo().getNombre().equalsIgnoreCase("4A")) {
     * System.out.println(alumno);
     * }
     * }
     * }
     * private static void alumnosDeLucenaYGrupo4A() {
     * System.out.println("\nConsultando alumnos de Lucena y grupo 4A...");
     * Objects<Alumno> alumnos = odb.getObjects(Alumno.class);
     * for (Alumno alumno : alumnos) {
     * if (alumno.getLocalidad() != null &&
     * alumno.getLocalidad().getNombre().equalsIgnoreCase("Lucena") &&
     * alumno.getGrupo() != null &&
     * alumno.getGrupo().getNombre().equalsIgnoreCase("4A")) {
     * System.out.println(alumno);
     * }
     * }
     * }
     */
    private static void alumnosDeLucena() {
        System.out.println("\nConsultando alumnos de la localidad de Lucena...");

        // Construir la consulta con CriteriaQuery (Uso correcto de Where)
        CriteriaQuery query = new CriteriaQuery(Alumno.class, Where.equal("localidad.nombre", "Lucena"));

        Objects<Alumno> alumnos = odb.getObjects(query); // Ejecutamos la consulta
        for (Alumno alumno : alumnos) {
            System.out.println(alumno);
        }
    }

    private static void alumnosDeGrupo4A() {
        System.out.println("\nConsultando alumnos del grupo 4A...");

        // Construir la consulta con CriteriaQuery (Uso correcto de Where)
        CriteriaQuery query = new CriteriaQuery(Alumno.class, Where.equal("grupo.nombre", "4A"));

        Objects<Alumno> alumnos = odb.getObjects(query); // Ejecutamos la consulta
        for (Alumno alumno : alumnos) {
            System.out.println(alumno);
        }
    }

    private static void alumnosDeLucenaYGrupo4A() {
        System.out.println("\nConsultando alumnos de Lucena y grupo 4A...");

        ICriterion condition1 = Where.equal("localidad.nombre", "Lucena");
        ICriterion condition2 = Where.equal("grupo.nombre", "4A");

        CriteriaQuery query = new CriteriaQuery(Alumno.class,
                Where.and().add(condition1).add(condition2));

        Objects<Alumno> alumnos = odb.getObjects(query);
        for (Alumno alumno : alumnos) {
            System.out.println(alumno);
        }
    }
}
