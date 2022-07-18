package com.example.mangayo.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.example.mangayo.R;
import com.example.mangayo.controller.UserServiceDetails;
import com.example.mangayo.util.Url;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

//, GoogleMap.OnPolylineClickListener, GoogleMap.OnPolygonClickListener

public class MechanicHomepageFragment extends Fragment implements OnMapReadyCallback {
    private String address, lat, lng, lat2, lng2, mechanic_id, user_id;
    private SharedPreferences sharedPreferences;
    private TextView setLocation;
    private MapView mapView;
    private GoogleMap map;
    private Marker markerUser;
    private Marker markerMechanic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mechanic_home_fragment, container, false);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setLocation = view.findViewById(R.id.txtMechanicCurrentLoc);
        address = "Pls Enable Location Settings First";
        sharedPreferences = getContext().getSharedPreferences("MySharedPref", Context.MODE_APPEND);
        address = sharedPreferences.getString("mechLocation", "");
        lat = sharedPreferences.getString("mechLatitude", "");
        lng = sharedPreferences.getString("mechLongitude", "");
        mechanic_id = sharedPreferences.getString("mechanic_id", "");
        user_id = sharedPreferences.getString("user_id", "");
        setCurrentMechanicLocation();
        setLocation.setText(address);


        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mechanicMap);
        mapFragment.getMapAsync(this);


        return view;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
//        LatLng currentLocation = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
//        googleMap.addMarker(new MarkerOptions()
//                .position(currentLocation)
//                .title("You're Here"));
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(lat),
//                Double.parseDouble(lng)), 15));
//
//        googleMap.addMarker(new MarkerOptions()
//                .position(new LatLng(10.3043409, 123.943074))
//                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
//                .title("Vehicle Location"));
//
//        Polyline polyline1 = googleMap.addPolyline(new PolylineOptions()
//                .clickable(true)
//                .add(new LatLng(Double.parseDouble(lat), Double.parseDouble(lng)),
//                        new LatLng(10.3043409, 123.943074)));
//
//
//        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//            @Override
//            public boolean onMarkerClick(@NonNull Marker marker) {
//                if (marker.getTitle().toString().equals("Vehicle Location")){
//                    confirmationDialogue();
//                    return true;
//                }else return false;
//            }
//        });

    }

    public void confirmationDialogue() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setMessage("Check Service Details?");
        alertDialogBuilder.setPositiveButton("YES",
                new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(getContext(), UserServiceDetails.class);
                        startActivity(intent);
                        getActivity().finish();
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

    public void setCurrentMechanicLocation() {
        String urlString = Url.getMechanicLoc + user_id;

        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String message = br.readLine();
            br.close();
            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        catch (JSONException e) {
//            e.printStackTrace();
//        }
    }


}
