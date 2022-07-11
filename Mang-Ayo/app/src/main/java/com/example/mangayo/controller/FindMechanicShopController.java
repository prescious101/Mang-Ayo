package com.example.mangayo.controller;

import android.widget.Toast;
import android.content.Intent;
import android.os.StrictMode;
import android.os.Bundle;
import android.location.Address;
import android.location.Geocoder;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mangayo.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class FindMechanicShopController extends AppCompatActivity implements OnMapReadyCallback {
    private String user, pass, firstName, lastName, phone, specialty, type, mechanicAddress, getAddress,lat,lng;
    private Button getLocBTN;
    private List<Address> address;
    private List<Marker> markers;
    private Marker setShopMarker = null;
    private Intent intent;
    private LatLngBounds.Builder builder = new LatLngBounds.Builder();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_store_location);
        markers = new ArrayList<Marker>();
        getDataIntent();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.storeLocationMap);
        mapFragment.getMapAsync(FindMechanicShopController.this);

        getLocBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getAddress != null) {
                    intent = new Intent(FindMechanicShopController.this, LoginController.class);
                    startActivity(intent);
                    finish();
                } else
                    Toast.makeText(FindMechanicShopController.this, "Pls select Location", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                if (markers.size() > 0) googleMap.clear();
                try {
                    Geocoder geocoder = new Geocoder(FindMechanicShopController.this);
                    address = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    if (address.size() > 0) {
                        setShopMarker = googleMap.addMarker(new MarkerOptions()
                                .position(latLng)
                                .title("Is this your shop location? Address:" + address.get(0).getAddressLine(0)));
                        markers.add(setShopMarker);
                        getAddress = address.get(0).getAddressLine(0).toString();
                        lat = String.valueOf(latLng.latitude);
                        lng = String.valueOf(latLng.longitude);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void getDataIntent() {
        Bundle bundle = this.getIntent().getExtras();
        user = bundle.getString("user");
        pass = bundle.getString("pass");
        firstName = bundle.getString("firstName");
        lastName = bundle.getString("lastName");
        phone = bundle.getString("phone");
        specialty = bundle.getString("specialty");
        mechanicAddress = bundle.getString("address");
        Toast.makeText(this, "Fetched Data from " + firstName, Toast.LENGTH_SHORT).show();
    }




}