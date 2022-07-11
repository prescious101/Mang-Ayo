package com.example.mangayo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class UserModel implements Parcelable {
    private String uid;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String userType;
    private String phonenumber;

    public UserModel(){

    }

    public UserModel(String uid, String username, String password,String userType, String firstName, String lastName, String phonenumber) {
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.phonenumber = phonenumber;
    }


    protected UserModel(Parcel in) {
        uid = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        userType = in.readString();
        username = in.readString();
        password = in.readString();
        phonenumber = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uid);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(userType);
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(phonenumber);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getUserType() { return userType; }

    public void setUserType(String userType) { this.userType = userType; }

}
