package com.example.mangayo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;

import com.example.mangayo.R;

public class MechanicHomepageFragment extends Fragment {
    private String address,lat,lng,mechanic_id;
    private String urlString = "http://192.168.254.104:9999/Mangayo-Admin/addStoreLocation.php";
    private SharedPreferences sharedPreferences;
    private TextView setLocation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mechanic_home_fragment, container, false);
        setLocation = view.findViewById(R.id.txtMechanicCurrentLoc);
        address = "Pls Enable Location Settings First";
        sharedPreferences = getContext().getSharedPreferences("MySharedPref", Context.MODE_APPEND);
        address = sharedPreferences.getString("mechLocation", "");
        lat = sharedPreferences.getString("mechLatitude","");
        lng = sharedPreferences.getString("mechLongitude","");
        mechanic_id = sharedPreferences.getString("mechanic_id","");
                setLocation.setText(address);
        Toast.makeText(getContext(), "GOT YOUR LOCATION!", Toast.LENGTH_SHORT).show();

        getCurrentMechanicLocation();

        return view;
    }

    public void getCurrentMechanicLocation() {
        urlString+="?latitude=" +lat+  "&longitude=" +lng+ "&address="+ address+ "&mechanic_id="+mechanic_id  ;
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String message = br.readLine();
            Log.d("TAG", "getCurrentMechanicLocation: "+message);
            br.close();conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
