package com.example.mangayo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.os.StrictMode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
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

public class ServiceFragment extends Fragment {
    private String services,desc,cost;
    private Intent intent;
    private View view;
    private Spinner serviceType;
    private EditText serviceDescription;
    private TextView serviceCost;
    private Button addService;
    private String urlString = "http://192.168.254.104:9999/Mangayo-Admin/mobileServices.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.service_fragment, container, false);

        serviceType = (Spinner) view.findViewById(R.id.spnServices);
        serviceDescription = (EditText) view.findViewById(R.id.editTextTextMultiLine);
        serviceCost = (TextView) view.findViewById(R.id.txtServiceCost);
        addService = (Button) view.findViewById(R.id.btnAddService);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        fillSpinner();
        serviceType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                switch (arg0.getItemAtPosition(arg2).toString()){
                    case "TIRE REPLACEMENT":serviceCost.setText("100.00 PHP");break;
                    case "ENGINE REPAIR":serviceCost.setText("200.00 PHP");break;
                    case "POOP CLEANING":serviceCost.setText("300.00 PHP");break;
                    case "BATTERY REPLACEMENT":serviceCost.setText("400.00 PHP");break;
                    case "BRAKE REPAIR":serviceCost.setText("500.00 PHP");break;
                    case "OTHER SERVICE":serviceCost.setText("600.00 PHP");break;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });

        addService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                services = serviceType.getSelectedItem().toString().trim();
                desc = serviceDescription.getText().toString().trim();
                cost = serviceCost.getText().toString().replaceAll(" PHP","").trim();
                toMaps(services,desc,cost);
            }
        });
        return view;
    }


    public void fillSpinner() {
        ArrayAdapter<String> adapter;
        List<String> list;View view;
        list = new ArrayList<>();
        list.add("TIRE REPLACEMENT");list.add("ENGINE REPAIR");
        list.add("POOP CLEANING");list.add("BATTERY REPLACEMENT");
        list.add("BRAKE REPAIR");list.add("OTHER SERVICE");
        adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, list);
        serviceType.setAdapter(adapter);
    }

    public void toMaps(String services,String desc, String cost){
        intent = new Intent(getContext(),MapsMarkerActivity.class);
        intent.putExtra("services",services);intent.putExtra("desc",desc);intent.putExtra("cost",cost);
        startActivity(intent);
        getActivity().finish();
    }

}
