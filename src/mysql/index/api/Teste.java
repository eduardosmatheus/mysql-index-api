package mysql.index.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import mysql.index.api.parser.TrackingPersistance;

public class Teste {
    
    public static void main(String[] args) throws FileNotFoundException, SQLException, ClassNotFoundException, IOException {
        Connection cn = DBHandler.getConnection();
        TrackingPersistance.readFiles(cn, primeiroTeste());
        System.out.println("Processo concluído com sucesso!");
    }
    
    private static File[] primeiroTeste() {
        File file = new File("./PrimeiroTeste");
        return file.listFiles();
    }
}
