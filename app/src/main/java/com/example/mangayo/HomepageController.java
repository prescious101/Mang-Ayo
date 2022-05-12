package com.example.mangayo;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;
import java.util.Locale;

import model.LocationModel;


public class HomepageController extends AppCompatActivity {
    public static final int FAST_UPDATE_INTERVAL = 5;
    public static final int DEFAULT_UPDATE_INTERVAL = 30;
    public static final int PERMISSIONS_FINE_LOCATION = 99;

    private String currentLocation = "didn't get location";
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest;
    private BottomNavigationView bottomNavigationView;

    com.example.mangayo.VehicleFragment vehicleFragment = new com.example.mangayo.VehicleFragment();
    com.example.mangayo.ServiceFragment servicesFragment = new com.example.mangayo.ServiceFragment();
    com.example.mangayo.NotificationFragment notificationFragment = new com.example.mangayo.NotificationFragment();
    com.example.mangayo.HomeFragment homeFragment = new com.example.mangayo.HomeFragment();
    com.example.mangayo.ProfileFragment profileFragment = new com.example.mangayo.ProfileFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        bottomNavigationView = findViewById(R.id.bottom_navigation_user);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
        bottomNavigationView.setSelectedItemId(R.id.home);

        setLocationRequest(); updateGPS();

        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.notification);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(8);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                        return true;
                    case R.id.notification:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, notificationFragment).commit();
                        return true;
                    case R.id.services:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, servicesFragment).commit();
                        return true;
                    case R.id.vehicles:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, vehicleFragment).commit();
                        return true;
                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment).commit();
                        return true;
                }
                return false;
            }
        });

    }

    public void setLocationRequest() {
        locationRequest = LocationRequest.create()
                .setInterval(DEFAULT_UPDATE_INTERVAL)
                .setFastestInterval(FAST_UPDATE_INTERVAL)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setMaxWaitTime(100);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSIONS_FINE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    updateGPS();
                    Toast.makeText(this, "PERMISSION GRANTED", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Mang-Ayo needs permissions to be granted to work properly", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void updateGPS() {
        //GET user permission first
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(HomepageController.this);
        if (ActivityCompat.checkSelfPermission(HomepageController.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    Geocoder geocoder = new Geocoder(HomepageController.this);
                    try {
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        sharedPref(addresses.get(0).getAddressLine(0),String.valueOf(addresses.get(0).getLatitude()),String.valueOf(addresses.get(0).getLongitude()));
                    } catch (Exception e) {
                        Log.e("TAG", "updateUIValues: ", e);
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                }
            });
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_FINE_LOCATION);
                Toast.makeText(this, "Called?", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void sharedPref(String address,String latitude , String longitude) {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("location", address);
        myEdit.putString("latitude", latitude);
        myEdit.putString("longitude", longitude);
        myEdit.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}