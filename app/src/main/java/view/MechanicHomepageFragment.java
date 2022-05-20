package com.example.mangayo;

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
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private String address, lat, lng, lat2="init", lng2="init", mechanic_id, user_id;
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
        setCurrentMechanicLocation();
        setLocation.setText(address);
        sharedPreferences = getContext().getSharedPreferences("MySharedPref", Context.MODE_APPEND);
        user_id = sharedPreferences.getString("user_id", "");

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mechanicMap);
        mapFragment.getMapAsync(this);


        return view;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        LatLng currentLocation = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
        googleMap.addMarker(new MarkerOptions()
                .position(currentLocation)
                .title("You're Here"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(lat),
                Double.parseDouble(lng)), 15));

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(10.26427842824107, 123.8384620855812))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                .title("Vehicle Location"));

        Polyline polyline1 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(new LatLng(Double.parseDouble(lat), Double.parseDouble(lng)),
                        new LatLng(10.26427842824107, 123.8384620855812)));


        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                if (marker.getTitle().toString().equals("Vehicle Location")){
                    confirmationDialogue();
                    return true;
                }else return false;
            }
        });

    }

    public void confirmationDialogue() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setMessage("Accept Booking?");
        alertDialogBuilder.setPositiveButton("YES",
                new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(getContext(), TransactionDetails.class);
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
        urlString += "?latitude=" + lat + "&longitude=" + lng + "&address=" + address + "&mechanic_id=" + mechanic_id;
        String urlString2 = "http://192.168.254.104:9999/Mangayo-Admin/mobile/getUserLocation.php";
        urlString2 += "&user_id=" + user_id;

        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String message = br.readLine();
            br.close();
            conn.disconnect();

//            URL url2 = new URL(urlString2);
//            HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
//            BufferedReader br2 = new BufferedReader(new InputStreamReader(conn2.getInputStream()));
//            String message2 = br2.readLine();
//            br2.close();
//            conn2.disconnect();
//            if (message2 != null) {
//                JSONObject serviceData = new JSONObject(message2);
//                JSONArray serviceView = serviceData.getJSONArray("user_location");
//                JSONObject json = serviceView.getJSONObject(0);
//                lat2 = json.getString("latitude");
//                lng2 = json.getString("longitude");
//                Log.d("SERVICE", "Pending Service(s): " + lat2);
//            }

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
