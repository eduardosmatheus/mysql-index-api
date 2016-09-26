package mysql.index.api.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

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
    
    public static boolean insertRouteWithIndex(Connection db, Route route) throws SQLException {
        PreparedStatement stmt = db.prepareStatement("insert into tracking_index (idTaxi, dataDaMovimentacao, longitude, latitude) values(?, ?, ?, ?)");
        stmt.setInt(1, route.getId());
        stmt.setTimestamp(2, route.getMomento());
        stmt.setDouble(3, route.getLongitude());
        stmt.setDouble(4, route.getLatitude());
        int linesAffected = stmt.executeUpdate();
        stmt.close();
        return linesAffected > 0;
    }
    
    public static Set<Route> listByIndex(Connection db, double longitude, double latitude) throws SQLException {
    	String sql = "select * from tracking_index where longitude = ? and latitude = ?";
    	PreparedStatement stmt = db.prepareStatement(sql);
    	stmt.setDouble(1, longitude);
    	stmt.setDouble(2, latitude);
    	ResultSet rs = stmt.executeQuery();
    	Set<Route> result = new HashSet<>();
    	while(rs.next()) {
    		result.add(fetchRoute(rs));
    	}
    	rs.close();
    	System.out.println("Lines found: "+result.stream().count());
    	return result;
    }
    
    public static Set<Route> list(Connection db, double longitude, double latitude) throws SQLException {
    	String sql = "select * from tracking where longitude = ? and latitude = ?";
    	PreparedStatement stmt = db.prepareStatement(sql);
    	stmt.setDouble(1, longitude);
    	stmt.setDouble(2, latitude);
    	ResultSet rs = stmt.executeQuery();
    	Set<Route> result = new HashSet<>();
    	while(rs.next()) {
    		result.add(fetchRoute(rs));
    	}
    	rs.close();
    	System.out.println("Lines found: "+result.stream().count());
    	return result;
    }
    
    private static Route fetchRoute(ResultSet rs) throws SQLException {
    	return new Route(rs.getInt(1), rs.getInt(2), rs.getTimestamp(3), rs.getDouble(4), rs.getDouble(5));
    }
}
