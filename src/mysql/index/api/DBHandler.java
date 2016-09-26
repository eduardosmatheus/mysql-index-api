package mysql.index.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler {
    
    public static Connection getConnection() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        try {
        	return DriverManager.getConnection("jdbc:mysql://localhost/parcial1", "root", "kjkszpj");			
		} catch (SQLException e) {
			throw new RuntimeException("Atenção! Usuário/senha do MySQL inválidos. Verifique os parâmetros.");
		}
    }
}
