package com.example.mangayo.model;

public class VehicleModel {
    private String vehicle_name;
    private String vehicle_type;
    private String registration_num;
    private String vehicle_model;
    private String chassis_num;
    private String fuel_type;

    public VehicleModel(String vehicle_name, String vehicle_type, String registration_num, String vehicle_model, String chassis_num, String fuel_type) {
        this.vehicle_name = vehicle_name;
        this.vehicle_type = vehicle_type;
        this.registration_num = registration_num;
        this.vehicle_model = vehicle_model;
        this.chassis_num = chassis_num;
        this.fuel_type = fuel_type;
    }



    public String getVehicle_name() {
        return vehicle_name;
    }

    public void setVehicle_name(String vehicle_name) {
        this.vehicle_name = vehicle_name;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public String getRegistration_num() {
        return registration_num;
    }

    public void setRegistration_num(String registration_num) {
        this.registration_num = registration_num;
    }

    public String getVehicle_model() {
        return vehicle_model;
    }

    public void setVehicle_model(String vehicle_model) {
        this.vehicle_model = vehicle_model;
    }

    public String getChassis_num() {
        return chassis_num;
    }

    public void setChassis_num(String chassis_num) {
        this.chassis_num = chassis_num;
    }

    public String getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        this.fuel_type = fuel_type;
    }
}
