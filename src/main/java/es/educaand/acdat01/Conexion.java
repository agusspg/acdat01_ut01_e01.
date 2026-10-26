package es.educaand.acdat01;

import es.educaand.acdat01.dto.ConfigDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Commit 3: conexión a la base de datos con los datos del DTO. */
public class Conexion {

    public static Connection conectar(ConfigDTO c) throws SQLException {
        String url = "jdbc:mysql://" + c.getHost() + ":" + c.getPort() + "/" + c.getDatabase();
        return DriverManager.getConnection(url, c.getUser(), c.getPassword());
    }
}
