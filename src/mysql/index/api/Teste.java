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
        System.out.println("Iniciando inserção com 10 mil....");
        TrackingPersistance.persistRoutes(cn, loadFiles(), 10000);
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

        System.out.println("Iniciando inserção com 100 mil....");
        TrackingPersistance.persistRoutes(cn, loadFiles(), 100000);
        System.out.println("Processo concluído com sucesso!");
        System.out.println("");
        Date inicioConsultaSemIndice2 = new Date();
        RouteDAO.list(cn, longitude, latitude);
        Date fimConsultaSemIndice2 = new Date();
        long tempoGastoSemIndice2 = fimConsultaSemIndice2.getTime() - inicioConsultaSemIndice2.getTime();
        System.out.println("Tempo gasto para consulta sem índice: " + tempoGastoSemIndice2 + " ms");
        System.out.println("");
        Date inicioConsultaComIndice2 = new Date();
        RouteDAO.listByIndex(cn, longitude, latitude);
        Date fimConsultaComIndice2 = new Date();
        long tempoGastoComIndice2 = fimConsultaComIndice2.getTime() - inicioConsultaComIndice2.getTime();
        System.out.println("Tempo gasto para consulta com índice: " + tempoGastoComIndice2 + " ms");

    }

    private static File[] loadFiles() {
        File file = new File("arquivos");
        return file.listFiles();
    }
}
