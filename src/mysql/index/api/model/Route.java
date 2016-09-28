package mysql.index.api.model;

import java.sql.Timestamp;

public class Route {
    
    private int id;
    private int idTaxi;
    private Timestamp data;
    private String longitude;
    private String latitude;

    private Route(int id, int idTaxi, Timestamp data, String longitude, String latitude) {
		this.id = id;
		this.idTaxi = idTaxi;
		this.data = data;
		this.longitude = longitude;
		this.latitude = latitude;
	}
    
    public Route(int idTaxi, Timestamp data, String longitude, String latitude){
    	this(0, idTaxi, data, longitude, latitude);
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

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    @Override
    public String toString() {
        return "Route{" + "id=" + id + ", idTaxi=" + idTaxi + ", data=" + data + ", longitude=" + longitude + ", latitude=" + latitude + '}';
    }
}
