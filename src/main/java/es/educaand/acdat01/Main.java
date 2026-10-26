package es.educaand.acdat01;

import es.educaand.acdat01.dao.ConfigDAO;
import es.educaand.acdat01.dao.ConfigPropertiesDAO;
import es.educaand.acdat01.dao.ConfigXmlDAO;
import es.educaand.acdat01.dto.ConfigDTO;

public class Main {

    public static void main(String[] args) {
        ConfigDTO datos = new ConfigDTO("localhost", 3306, "acdat01", "root", "root");

        probar(new ConfigPropertiesDAO("config.properties"), datos);
        probar(new ConfigXmlDAO("config.xml"), datos);
    }

    private static void probar(ConfigDAO dao, ConfigDTO datos) {
        try {
            dao.guardar(datos);
            ConfigDTO config = dao.cargar();
            System.out.println("Configuración cargada: " + config.getHost() + ":" + config.getPort() + "/" + config.getDatabase());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}