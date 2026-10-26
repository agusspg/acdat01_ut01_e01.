package es.educaand.acdat01;

import es.educaand.acdat01.dao.*;
import es.educaand.acdat01.dto.ConfigDTO;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Gestión de configuraciones por terminal. */
public class Main {

    private static final String[] FORMATOS = {"properties", "xml", "json"};
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=================================");
        System.out.println(" SISTEMA DE CONFIGURACIÓN");
        System.out.println("=================================\n");

        // Buscar los ficheros de configuración existentes
        List<String> existentes = new ArrayList<>();
        for (String f : FORMATOS) {
            if (new File("config." + f).exists()) {
                existentes.add(f);
            }
        }

        try {
            ConfigDTO config;

            if (existentes.isEmpty()) {
                System.out.println("No se han encontrado configuraciones almacenadas.\n");
                config = pedirDatos();

                System.out.println("\nSeleccione el formato de almacenamiento:\n");
                for (int i = 0; i < FORMATOS.length; i++) {
                    System.out.println((i + 1) + ". " + FORMATOS[i].toUpperCase());
                }
                String formato = FORMATOS[leerOpcion(FORMATOS.length) - 1];

                crearDao(formato).guardar(config);
                System.out.println("\nConfiguración guardada correctamente como config." + formato);
            } else {
                System.out.println("Configuraciones disponibles:\n");
                for (int i = 0; i < existentes.size(); i++) {
                    System.out.println((i + 1) + ". config." + existentes.get(i));
                }
                String formato = existentes.get(leerOpcion(existentes.size()) - 1);

                config = crearDao(formato).cargar();
                System.out.println("\nConfiguración cargada desde config." + formato);
            }

            // Usar la configuración: conectar con la base de datos
            try (Connection con = Conexion.conectar(config)) {
                System.out.println("Conexión establecida: " + con.isValid(3));
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static ConfigDTO pedirDatos() {
        System.out.println("Introduzca los datos de configuración:\n");
        System.out.print("Servidor: ");
        String host = sc.nextLine();
        System.out.print("Puerto: ");
        int puerto = Integer.parseInt(sc.nextLine());
        System.out.print("Base de datos: ");
        String bd = sc.nextLine();
        System.out.print("Usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Contraseña: ");
        String password = sc.nextLine();
        return new ConfigDTO(host, puerto, bd, usuario, password);
    }

    private static int leerOpcion(int max) {
        while (true) {
            System.out.print("\nOpción: ");
            try {
                int op = Integer.parseInt(sc.nextLine());
                if (op >= 1 && op <= max) {
                    return op;
                }
            } catch (NumberFormatException e) {
                // se vuelve a pedir
            }
            System.out.println("Opción no válida.");
        }
    }

    private static ConfigDAO crearDao(String formato) {
        String fichero = "config." + formato;
        switch (formato) {
            case "xml":  return new ConfigXmlDAO(fichero);
            case "json": return new ConfigJsonDAO(fichero);
            default:     return new ConfigPropertiesDAO(fichero);
        }
    }
}