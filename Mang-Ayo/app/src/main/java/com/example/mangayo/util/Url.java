package com.example.mangayo.util;

public class Url {
    public static final String host = "http://10.0.2.2";
    public static final String path = "/mobile";
    public static final String api = host + path;


    public static  String login = api + "/loginCheck.php?";
    public static  String mechanicLoc = api + "/getNearbyMechanicLocation.php";
    public static  String registerVehicleOwner = api + "/mobileRegisterUser.php";
    public static  String getVehicleDetails = api + "/getVehicles.php?userID=";
    public static  String getMechaniDetails = api + "/getDetails.php";
    public static  String addBookingMechanic = api + "/addBooking.php";
    public static  String addVehicle = api + "/mobileAddVehicle.php?";
    public static  String getMechanicLoc = api + "/getUserLocation.php?user_id=";
}
