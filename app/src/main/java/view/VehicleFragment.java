package com.example.mangayo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import android.os.StrictMode;
import androidx.fragment.app.Fragment;

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

import model.VehicleModel;
import view.VehicleAdapter;


public class VehicleFragment extends Fragment {

    private ListView lv;
    private Button addVehicle;
    private Intent intent;
    private VehicleAdapter vehicleAdapter;
    private ArrayList<VehicleModel> list = new ArrayList<VehicleModel>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vehicle_fragment, container, false);

        lv = view.findViewById(R.id.listAddVehicles);
        vehicleAdapter = new VehicleAdapter(getContext(), list);
        lv.setAdapter(vehicleAdapter);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        getVehicleData();

        addVehicle = view.findViewById(R.id.btnAddVehicles);

        addVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getContext(), AddVehicleController.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public void getVehicleData() {
        String uid = "31";
        String urlString = "http://192.168.254.113:9999/Mangayo-Admin/mobile/getVehicles.php?userID=" + uid;
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String vehicles = br.readLine();
            br.close();
            conn.disconnect();

            JSONArray vehicleArray = new JSONArray(vehicles);
            Log.d("Profile", "JSONARRAY: " + vehicleArray);
            for (int i = 0; i < vehicleArray.length(); i++) {
                JSONObject json = vehicleArray.getJSONObject(i);
                String name = json.getString("vehicle_name");
                String type = json.getString("vehicle_type");
                String regNum = json.getString("registration_num");
                String chasNum = json.getString("vehicle_model");
                String model = json.getString("chassis_num");
                String fuel = json.getString("fuel_type");
                list.add(new VehicleModel(name, type, regNum, chasNum, model, fuel));
            }
            Log.d("ARRAYLIST", "LIST: "+list.get(0).getVehicle_name());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        list.removeAll(list);
    }


}
