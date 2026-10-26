package es.educaand.acdat01.dao;

import es.educaand.acdat01.dto.ConfigDTO;
import java.io.IOException;

/** DAO: guardar y cargar la configuración, sea cual sea el formato. */
public interface ConfigDAO {
    void guardar(ConfigDTO config) throws IOException;
    ConfigDTO cargar() throws IOException;
}
