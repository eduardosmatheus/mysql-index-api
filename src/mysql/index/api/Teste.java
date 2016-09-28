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
    	runProcess(cn, 10000);
    	runProcess(cn, 100000);
    	runProcess(cn, 1000000);
    }

    private static File[] loadFiles() {
        File file = new File("arquivos");
        return file.listFiles();
    }
    
    private static void runProcess(Connection cn, int numeroDeRegistros) throws ClassNotFoundException, SQLException{
        
    	System.out.println("Iniciando inserção com "+numeroDeRegistros+" registros....");
        TrackingPersistance.persistRoutes(cn, loadFiles(), numeroDeRegistros);
        System.out.println("Processo concluído com sucesso!");
        System.out.println("");
        String longitude = "39.88632";
        String latitude = "116.44237";
        
        Date inicioConsultaSemIndice = new Date();
        RouteDAO.list(cn, longitude, latitude);
        Date fimConsultaSemIndice = new Date();
        
        long tempoGastoSemIndice = fimConsultaSemIndice.getTime() - inicioConsultaSemIndice.getTime();
        System.out.println("Tempo gasto para consulta sem índice: " + tempoGastoSemIndice + " ms");
        System.out.println("");
        
        Date inicioConsultaComIndice = new Date();
        RouteDAO.listByIndex(cn, longitude, latitude);
        Date fimConsultaComIndice = new Date();
        
        long tempoGastoComIndice = fimConsultaComIndice.getTime() - inicioConsultaComIndice.getTime();
        System.out.println("Tempo gasto para consulta com índice: " + tempoGastoComIndice + " ms");
        System.out.println("");
    }
}
