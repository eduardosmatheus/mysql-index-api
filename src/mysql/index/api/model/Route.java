package mysql.index.api.model;

import java.sql.Timestamp;

public class Route {
    
    private int id;
    private int idTaxi;
    private Timestamp data;
    private double longitude;
    private double latitude;

    public Route() {}
    
    public Route(int id, int idTaxi, Timestamp data, double longitude, double latitude) {
		this.id = id;
		this.idTaxi = idTaxi;
		this.data = data;
		this.longitude = longitude;
		this.latitude = latitude;
	}

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTaxi() {
        return idTaxi;
    }

    public void setIdTaxi(int idTaxi) {
        this.idTaxi = idTaxi;
    }

    public Timestamp getMomento() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Route{" + "id=" + id + ", idTaxi=" + idTaxi + ", data=" + data + ", longitude=" + longitude + ", latitude=" + latitude + '}';
    }
}
