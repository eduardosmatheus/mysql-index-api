package mysql.index.api.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Stream;
import mysql.index.api.model.Route;
import mysql.index.api.model.RouteDAO;

public class TrackingPersistance {

    private static final Function<String, Route> routeParser = (String content) -> {
        Route rota = new Route();
        String[] parts = content.split(",");
        rota.setIdTaxi(Integer.parseInt(parts[0]));
        rota.setData(Timestamp.valueOf(parts[1]));
        rota.setLatitude(Double.parseDouble(parts[2]));
        rota.setLongitude(Double.parseDouble(parts[3]));
        return rota;
    };
    
    public static void readFiles(Connection cn, File[] files) throws FileNotFoundException, SQLException, IOException {
        Date before = new Date();
        for (File arquivo : files) {
            System.out.println("Lendo arquivo "+arquivo.getName()+"....");
            Stream<String> fileLines = Files.lines(Paths.get(arquivo.getAbsolutePath()));
            fileLines.map(routeParser).forEach(rota -> {
                try {
                    RouteDAO.insertRoute(cn, rota);
                    RouteDAO.insertRouteWithIndex(cn, rota);
                } catch (SQLException ex) { 
                    System.out.println("Não vai processar a rota \n"+rota);
                }
            });
        }
        Date now = new Date();
        long timeElapsed = now.getTime() - before.getTime();
        System.out.println("Tempo gasto: "+timeElapsed);
    }
}
