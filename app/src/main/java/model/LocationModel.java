package model;

import android.os.Parcelable;

public class LocationModel {

    private String location_id;
    private String latitude;
    private String longitude;
    private String address;
    private String mechanic_id;

    public LocationModel(String location_id, String latitude, String longitude, String address, String mechanic_id) {
        this.location_id = location_id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.mechanic_id = mechanic_id;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMechanic_id() {
        return mechanic_id;
    }

    public void setMechanic_id(String mechanic_id) {
        this.mechanic_id = mechanic_id;
    }
}
