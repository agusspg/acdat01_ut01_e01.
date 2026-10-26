package es.educaand.acdat01;

import es.educaand.acdat01.dao.ConfigDAO;
import es.educaand.acdat01.dao.ConfigPropertiesDAO;
import es.educaand.acdat01.dto.ConfigDTO;

public class Main {

    public static void main(String[] args) {
        ConfigDAO dao = new ConfigPropertiesDAO("config.properties");

        try {
            dao.guardar(new ConfigDTO("localhost", 3306, "acdat01", "root", "root"));
            ConfigDTO config = dao.cargar();
            System.out.println("Configuración cargada: " + config.getHost() + ":" + config.getPort() + "/" + config.getDatabase());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}