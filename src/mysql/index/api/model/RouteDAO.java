package mysql.index.api.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteDAO {

    public static boolean insertRoute(Connection db, Route route) throws SQLException {
        PreparedStatement stmt = db.prepareStatement("insert into tracking (idTaxi, dataDaMovimentacao, longitude, latitude) values(?, ?, ?, ?)");
        stmt.setInt(1, route.getId());
        stmt.setTimestamp(2, route.getMomento());
        stmt.setString(3, route.getLongitude());
        stmt.setString(4, route.getLatitude());
        int linesAffected = stmt.executeUpdate();
        stmt.close();
        return linesAffected > 0;
    }

    public static boolean insertRouteWithIndex(Connection db, Route route) throws SQLException {
        PreparedStatement stmt = db.prepareStatement("insert into tracking_index (idTaxi, dataDaMovimentacao, longitude, latitude) values(?, ?, ?, ?)");
        stmt.setInt(1, route.getId());
        stmt.setTimestamp(2, route.getMomento());
        stmt.setString(3, route.getLongitude());
        stmt.setString(4, route.getLatitude());
        int linesAffected = stmt.executeUpdate();
        stmt.close();
        return linesAffected > 0;
    }

    public static List<Route> listByIndex(Connection db, String longitude, String latitude) throws SQLException {
        String sql = "select * from tracking_index where longitude = ? and latitude = ?";
        PreparedStatement stmt = db.prepareStatement(sql);
        stmt.setString(1, longitude);
        stmt.setString(2, latitude);
        ResultSet rs = stmt.executeQuery();
        List<Route> result = new ArrayList<>();
        while (rs.next()) {
            result.add(fetchRoute(rs));
        }
        rs.close();
        return result;
    }

    public static List<Route> list(Connection db, String longitude, String latitude) throws SQLException {
        String sql = "select * from tracking where longitude = ? and latitude = ?";
        PreparedStatement stmt = db.prepareStatement(sql);
        stmt.setString(1, longitude);
        stmt.setString(2, latitude);
        ResultSet rs = stmt.executeQuery();
        List<Route> result = new ArrayList<>();
        while (rs.next()) {
            result.add(fetchRoute(rs));
        }
        rs.close();
        return result;
    }

    private static Route fetchRoute(ResultSet rs) throws SQLException {
        return new Route(rs.getInt(2), rs.getTimestamp(3), rs.getString(4), rs.getString(5));
    }
}
