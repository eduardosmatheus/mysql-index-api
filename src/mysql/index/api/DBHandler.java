package mysql.index.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler {

    public static Connection getConnection() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        try {
            Connection result = DriverManager.getConnection("jdbc:mysql://localhost/oficial1", "root", "kjkszpj");
            result.setAutoCommit(false);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Atenção! Usuário/senha do MySQL inválidos. Verifique os parâmetros.");
        }
    }
}
