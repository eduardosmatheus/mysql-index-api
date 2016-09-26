package mysql.index.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import mysql.index.api.model.RouteDAO;
import mysql.index.api.parser.TrackingPersistance;

public class Teste {
    
    public static void main(String[] args) throws FileNotFoundException, SQLException, ClassNotFoundException, IOException {
        
    	Connection cn = DBHandler.getConnection();
        System.out.println("Iniciando....");
        TrackingPersistance.readFiles(cn, loadFiles());
        System.out.println("Processo concluído com sucesso!");
        
        double longitude = 39.88632;
        double latitude = 116.44237;
        
        Date inicioConsultaSemIndice = new Date();
        RouteDAO.list(cn, longitude, latitude);
        Date fimConsultaSemIndice = new Date();
        long tempoGastoSemIndice = fimConsultaSemIndice.getTime() - inicioConsultaSemIndice.getTime();
        System.out.println("Tempo gasto para consulta sem índice: "+tempoGastoSemIndice+" ms");
        
        Date inicioConsultaComIndice = new Date();
        RouteDAO.listByIndex(cn, longitude, latitude);
        Date fimConsultaComIndice = new Date();
        long tempoGastoComIndice = fimConsultaComIndice.getTime() - inicioConsultaComIndice.getTime();
        System.out.println("Tempo gasto para consulta com índice: "+tempoGastoComIndice+" ms");
    }
    
    private static File[] loadFiles() {
        File file = new File("./PrimeiroTeste");
        return file.listFiles();
    }
}
