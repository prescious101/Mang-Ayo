package com.example.mangayo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import android.os.StrictMode;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mangayo.R;
import com.example.mangayo.controller.AddVehicleController;

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
import java.util.Map;

import com.example.mangayo.controller.HomepageController;
import com.example.mangayo.controller.MechanicHomepage;
import com.example.mangayo.model.VehicleModel;
import com.example.mangayo.adapter.VehicleAdapter;
import com.example.mangayo.util.Url;


public class VehicleFragment extends Fragment {

    private RecyclerView rv;
    private Button addVehicle;
    private Intent intent;
    private VehicleAdapter vehicleAdapter;
    private ArrayList<VehicleModel> list = new ArrayList<VehicleModel>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vehicle_fragment, container, false);

        rv = view.findViewById(R.id.listAddVehicles);
        vehicleAdapter = new VehicleAdapter(getContext(), list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(mLayoutManager);
        rv.setAdapter(vehicleAdapter);

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
        StringRequest request = new StringRequest(Request.Method.POST, Url.getVehicleDetails, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json = jsonArray.getJSONObject(i);
                        String name = json.getString("vehicle_brand");
                        String type = json.getString("vehicle_type");
                        String regNum = json.getString("plate_num");
                        String chasNum = json.getString("vehicle_model");
                        String model = json.getString("chassis_num");
                        String fuel = json.getString("fuel_type");
                        String imageUrl = json.getString("vehicle_image");
                        String url = imageUrl+ "png";

                        list.add(new VehicleModel(name, type, regNum, chasNum, model, fuel, url));
                        vehicleAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", "2");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        list.removeAll(list);
    }


}
