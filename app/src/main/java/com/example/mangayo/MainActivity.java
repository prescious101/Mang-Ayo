package com.example.mangayo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;

import com.google.android.gms.location.LocationRequest;


public class MainActivity extends AppCompatActivity {
    public static final int FAST_UPDATE_INTERVAL = 5;
    public static final int DEFAULT_UPDATE_INTERVAL = 30;
    public static final int PERMISSIONS_FINE_LOCATION = 99;
    private Intent intent;

    private LocationRequest locationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(haveNetworkConnection()){
            if(locationEnabled()){
                Thread background = new Thread() {
                    public void run() {
                        try {
                            sleep(1 * 1000);
                            intent = new Intent(MainActivity.this, LoginController.class);
                            startActivity(intent);
                            finish();
                        } catch (Exception e) {
                        }
                    }
                };
                background.start();
            }else{
                finish(); System.exit(0);
            }
        }else{
            finish(); System.exit(0);
        }
    }

    public void toHomepageController() {
        intent = new Intent(this, HomepageController.class);
        startActivity(intent);
        finish();
    }


    private boolean locationEnabled(){
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch(Exception e) {e.printStackTrace();}

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch(Exception e) {e.printStackTrace();}

        if(!gps_enabled && !network_enabled) {
            Toast.makeText(this, "Please enable location services", Toast.LENGTH_SHORT).show();
            return false;
        }else return true;
    }

    private boolean haveNetworkConnection() {
        final ConnectivityManager conMgr = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() &&    conMgr.getActiveNetworkInfo().isConnected()) {
            return true;
        } else {
            Toast.makeText(this, "Please enable wifi services", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}