package mysql.index.api.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.function.Function;

import mysql.index.api.model.Route;
import mysql.index.api.model.RouteDAO;

public class TrackingPersistance {

	public static void persistRoutes(Connection db, File[] files, int numeroDeRegistros) {
		final ArrayList<Route> routesStream = new ArrayList<>();
		for (File file : files) {
			try {
				routesStream.addAll(listRoutes(file));
			} catch (IOException e) {
				System.out.println("");
			}
		}
		System.out.println(routesStream.stream().limit(numeroDeRegistros).count());
		routesStream.stream().limit(numeroDeRegistros).forEach(route -> {
			try {
				RouteDAO.insertRoute(db, route);
				RouteDAO.insertRouteWithIndex(db, route);
			} catch (SQLException e) {
				System.out.println("");
			}
		});
	}
	
	private static ArrayList<Route> listRoutes(File file) throws IOException {
		ArrayList<Route> rotas = new ArrayList<>();
		@SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader(new FileReader(file));
		reader.lines().map(ROUTE_PARSER).forEach(route -> rotas.add(route));
		return rotas;
	}
	
	private static final Function<String, Route> ROUTE_PARSER = (String content) -> {
		String[] parts = content.split(",");
		return new Route(Integer.parseInt(parts[0]), Timestamp.valueOf(parts[1]), parts[2], parts[3]);
	};
}
