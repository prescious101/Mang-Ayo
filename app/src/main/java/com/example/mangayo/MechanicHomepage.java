package com.example.mangayo;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.ReferenceQueue;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MechanicHomepage extends AppCompatActivity {
    public static final int FAST_UPDATE_INTERVAL = 5;
    public static final int DEFAULT_UPDATE_INTERVAL = 30;
    public static final int PERMISSIONS_FINE_LOCATION = 99;

    private SharedPreferences sharedPreferences;
    private String currentLocation = "didn't get location",type,email,mechanicData,urlString,mechanic_id;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest;
    private BottomNavigationView bottomNavigationView;

    com.example.mangayo.MechanicHomepageFragment mechanicHomepageFragment = new com.example.mangayo.MechanicHomepageFragment();
    com.example.mangayo.MechanicProfileFragment mechanicProfileFragment = new com.example.mangayo.MechanicProfileFragment();
    com.example.mangayo.MechanicBookingFragment mechanicBookingFragment = new com.example.mangayo.MechanicBookingFragment();
    com.example.mangayo.MechanicSchedulingFragment mechanicSchedulingFragment = new com.example.mangayo.MechanicSchedulingFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mechanic_homepage);
        bottomNavigationView = findViewById(R.id.bottom_navigation_mechanic);
        getSupportFragmentManager().beginTransaction().replace(R.id.mechanicContainer, mechanicHomepageFragment).commit();
        bottomNavigationView.setSelectedItemId(R.id.homeMechanic);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        setLocationRequest(); updateGPS();

        sharedPreferences = this.getSharedPreferences("MySharedPref", Context.MODE_APPEND);
        type = sharedPreferences.getString("userType", "");
        email = sharedPreferences.getString("email", "");
        Log.d("TAG SHARED", "onCreate: "+ type + email);

        getUserData();

        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.bookingMechanic);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(8);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homeMechanic:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mechanicContainer, mechanicHomepageFragment).commit();
                        return true;
                    case R.id.bookingMechanic:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mechanicContainer, mechanicBookingFragment).commit();
                        return true;
                    case R.id.schedulingMechanic:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mechanicContainer, mechanicSchedulingFragment).commit();
                    case R.id.profileMechanic:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mechanicContainer, mechanicProfileFragment).commit();
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
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MechanicHomepage.this);
        if (ActivityCompat.checkSelfPermission(MechanicHomepage.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    Geocoder geocoder = new Geocoder(MechanicHomepage.this);
                    try {
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        sharedPref(addresses.get(0).getAddressLine(0),String.valueOf(addresses.get(0).getLatitude()),String.valueOf(addresses.get(0).getLongitude()));

                    } catch (Exception e) {
                        Log.d("TAG", "updateUIValues: "+ e);
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

    public void sharedPref(String address, String latitude , String longitude) {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("mechLocation", address);
        myEdit.putString("mechLatitude", latitude);
        myEdit.putString("mechLongitude", longitude);
        myEdit.putString("mechanic_id", mechanic_id);
        myEdit.commit();
    }

    public void getUserData(){
        urlString="http://192.168.254.114:9999/Mangayo-Admin/getUserProfile.php?email="+email+"&userType="+type;
        try {
            Log.d("URL",urlString);
            URL url =new URL(urlString);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            mechanicData=br.readLine();
            Log.d("MechanicHompage", "data: "+ mechanicData);
            br.close();conn.disconnect();

            JSONObject userData = new JSONObject(mechanicData);
            JSONObject userView = userData.getJSONObject("mechanic");
            Log.d("Mechanic", "getUserData: "+ userView.getString("mechanic_id"));
            mechanic_id = userView.getString("mechanic_id");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

}
