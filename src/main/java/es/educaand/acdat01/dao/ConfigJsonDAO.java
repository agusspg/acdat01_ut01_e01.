package es.educaand.acdat01.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.educaand.acdat01.dto.ConfigDTO;
import java.io.File;
import java.io.IOException;

/** Commit 4: formato JSON (necesita la librería Jackson). */
public class ConfigJsonDAO implements ConfigDAO {

    private final File fichero;
    private final ObjectMapper mapper = new ObjectMapper();

    public ConfigJsonDAO(String fichero) {
        this.fichero = new File(fichero);
    }

    @Override
    public void guardar(ConfigDTO c) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(fichero, c);
    }

    @Override
    public ConfigDTO cargar() throws IOException {
        return mapper.readValue(fichero, ConfigDTO.class);
    }
}
