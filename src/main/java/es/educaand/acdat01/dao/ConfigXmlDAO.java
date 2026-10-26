package es.educaand.acdat01.dao;

import es.educaand.acdat01.dto.ConfigDTO;

import java.io.*;
import java.util.Properties;

/**
 * Commit 2: formato properties en XML.
 */
public class ConfigXmlDAO implements ConfigDAO {

    private final String fichero;

    public ConfigXmlDAO(String fichero) {
        this.fichero = fichero;
    }

    @Override
    public void guardar(ConfigDTO c) throws IOException {
        Properties p = new Properties();
        p.setProperty("host", c.getHost());
        p.setProperty("port", String.valueOf(c.getPort()));
        p.setProperty("database", c.getDatabase());
        p.setProperty("user", c.getUser());
        p.setProperty("password", c.getPassword());
        try (FileOutputStream out = new FileOutputStream(fichero)) {
            p.storeToXML(out, "Configuracion BD");
        }
    }

    @Override
    public ConfigDTO cargar() throws IOException {
        Properties p = new Properties();
        try (FileInputStream in = new FileInputStream(fichero)) {
            p.loadFromXML(in);
        }
        return new ConfigDTO(p.getProperty("host"), Integer.parseInt(p.getProperty("port")),
                p.getProperty("database"), p.getProperty("user"), p.getProperty("password"));
    }
}
