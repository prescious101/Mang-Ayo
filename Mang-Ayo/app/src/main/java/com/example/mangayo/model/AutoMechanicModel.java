package com.example.mangayo.model;

public class AutoMechanicModel {
    private String id,fName, lName, email, address, specialty, phoneNum;


    public  AutoMechanicModel(){

    }

//    public AutoMechanicModel(String id, String fName, String lName, String email, String address, String specialty) {
//        this.id = id;
//        this.fName = fName;
//        this.lName = lName;
//        this.email = email;
//        this.address = address;
//        this.specialty = specialty;
//    }

    public AutoMechanicModel( String fName, String lName, String email,String phoneNum, String address, String specialty) {
        this.phoneNum = phoneNum;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.address = address;
        this.specialty = specialty;
    }



    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
