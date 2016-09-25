package mysql.index.api.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RouteDAO {
    
    public static boolean insertRoute(Connection db, Route route) throws SQLException {
        PreparedStatement stmt = db.prepareStatement("insert into tracking (idTaxi, dataDaMovimentacao, longitude, latitude) values(?, ?, ?, ?)");
        stmt.setInt(1, route.getId());
        stmt.setTimestamp(2, route.getMomento());
        stmt.setDouble(3, route.getLongitude());
        stmt.setDouble(4, route.getLatitude());
        int linesAffected = stmt.executeUpdate();
        stmt.close();
        return linesAffected > 0;
    }
}
