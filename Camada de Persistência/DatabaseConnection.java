

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Configuração da conexão com o banco de dados PostgreSQL
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/fittrack";
    private static final String USER = "postgres";
    private static final String PASSWORD = "senha_aqui";

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }
}
