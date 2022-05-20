package com.example.mangayo;

import androidx.annotation.NonNull;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.mangayo.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.LocationModel;

public class MapsMarkerActivity extends AppCompatActivity implements OnMapReadyCallback {

    com.example.mangayo.ServiceFragment servicesFragment = new com.example.mangayo.ServiceFragment();
    private Marker localMarker = null;
    private String lat, lng, lat1, lng1,mechanic_id,services,desc,cost,user_id="1";
    private LatLng mechanicsLocation;
    private List<Marker> markers;
    private ArrayList<LocationModel> list = new ArrayList<LocationModel>();
    private SharedPreferences sharedPreferences;
    private LatLngBounds.Builder builder = new LatLngBounds.Builder();
    private JSONArray locationsArray;
    private Intent intent;private Bundle bundle;
    private Button mapBackBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        getSupportActionBar().setTitle("Locate Mechanics");


        markers = new ArrayList<Marker>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        getAutoMechanicLocations();

        // Get the SupportMapFragment and request notification when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mechanicsLocationMap);
        mapFragment.getMapAsync(MapsMarkerActivity.this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        cameraFocus(googleMap);
        try {
            if(locationsArray!=null){
                for (int i = 0; i < locationsArray.length(); i++) {
                    JSONObject json = locationsArray.getJSONObject(i);
                    String location_id = json.getString("mechanic_id");
                    String latitude = json.getString("latitude");
                    String longitude = json.getString("longitude");
                    String address = json.getString("address");
                    String mechanic_id = json.getString("mechanic_id");
                    list.add(new LocationModel(location_id, latitude, longitude, address, mechanic_id));

                    mechanicsLocation = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
                    localMarker = googleMap.addMarker(new MarkerOptions()
                            .position(mechanicsLocation)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                            .title("Mechanic ID:" + mechanic_id));
                    markers.add(localMarker);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                LatLng pos = marker.getPosition();
                if (!marker.getTitle().equals("You're Here")) {
                    confirmationDialogue(marker.getTitle().replaceAll("Mechanic ID:","").toString());
                    Toast.makeText(MapsMarkerActivity.this, marker.getTitle().toString(), Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(MapsMarkerActivity.this, "This is Your location", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }
    @Override
    public void onBackPressed() {

    }

    public void confirmationDialogue(String mechanic_id) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Choose this Mechanic?");
        alertDialogBuilder.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        intent = new Intent(MapsMarkerActivity.this, MechanicDetails.class);
                        intent.putExtra("user_id",user_id);
                        intent.putExtra("mechanic_id",mechanic_id);
                        intent.putExtra("services",services);
                        intent.putExtra("desc",desc);
                        intent.putExtra("cost",cost);
                        startActivity(intent);
                        finish();
                    }
                });

        alertDialogBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void getAutoMechanicLocations() {
        String urlString = "http://192.168.1.217:9999/Mangayo-Admin/getNearbyMechanicLocation.php";
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String locations = br.readLine();
            br.close();
            conn.disconnect();
            locationsArray = new JSONArray(locations);
            Log.d("ARRAYLIST", "LIST: " + locationsArray.toString());

            if (locationsArray != null)
                Toast.makeText(MapsMarkerActivity.this, "Mechanics Found!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MapsMarkerActivity.this, "No Mechanics in the Area", Toast.LENGTH_SHORT).show();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void cameraFocus(GoogleMap googleMap) {
        sharedPreferences = MapsMarkerActivity.this.getSharedPreferences("MySharedPref", Context.MODE_APPEND);

        lat = sharedPreferences.getString("latitude", "");
        lng = sharedPreferences.getString("longitude", "");

        LatLng currentLocation = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
        localMarker = googleMap.addMarker(new MarkerOptions()
                .position(currentLocation)
                .title("You're Here"));
        markers.add(localMarker);

        for (Marker m : markers) {
            builder.include(m.getPosition());
        }
        LatLngBounds bounds = builder.build();
        int padding = 0; // offset from edges of the map in pixels
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        googleMap.moveCamera(cu);
    }

    public void getDataFromIntent(){
        services = bundle.getString("services");
        desc = bundle.getString("desc");
        cost = bundle.getString("cost");
    }



}