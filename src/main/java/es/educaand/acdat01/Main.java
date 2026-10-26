package es.educaand.acdat01;

import es.educaand.acdat01.dao.ConfigDAO;
import es.educaand.acdat01.dao.ConfigJsonDAO;
import es.educaand.acdat01.dao.ConfigPropertiesDAO;
import es.educaand.acdat01.dao.ConfigXmlDAO;
import es.educaand.acdat01.dto.ConfigDTO;
import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
        // Cambia usuario y contraseña por los de tu MySQL
        ConfigDTO datos = new ConfigDTO("localhost", 3306, "acdat01", "root", "");

        probar(new ConfigPropertiesDAO("config.properties"), datos);
        probar(new ConfigXmlDAO("config.xml"), datos);
        probar(new ConfigJsonDAO("config.json"), datos);
    }

    private static void probar(ConfigDAO dao, ConfigDTO datos) {
        try {
            dao.guardar(datos);
            ConfigDTO config = dao.cargar();
            System.out.println("Configuración cargada: " + config.getHost() + ":" + config.getPort() + "/" + config.getDatabase());

            try (Connection con = Conexion.conectar(config)) {
                System.out.println("Conexión establecida: " + con.isValid(3));
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}