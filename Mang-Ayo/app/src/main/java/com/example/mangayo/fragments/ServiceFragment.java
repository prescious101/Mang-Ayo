package com.example.mangayo.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.os.StrictMode;

import com.example.mangayo.R;
import com.example.mangayo.controller.MapsMarkerActivity;

import java.util.ArrayList;
import java.util.List;

public class ServiceFragment extends Fragment implements View.OnClickListener {
    private String services,desc,cost;
    private Intent intent;
    private View view;
    private LinearLayout tireReplacement;
    private LinearLayout engineRepair;
    private LinearLayout poopCleaning;
    private LinearLayout batteryReplacement;
    private LinearLayout brakeRepair;
    private LinearLayout otherService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.select_service_fragment, container, false);

        tireReplacement = view.findViewById(R.id.tire_replacement);
        tireReplacement.setOnClickListener(this);
        engineRepair = view.findViewById(R.id.engine_repair);
        engineRepair.setOnClickListener(this);
        poopCleaning = view.findViewById(R.id.poop_cleaning);
        poopCleaning.setOnClickListener(this);
        batteryReplacement = view.findViewById(R.id.battery_replacement);
        batteryReplacement.setOnClickListener(this);
        brakeRepair = view.findViewById(R.id.brake_repair);
        brakeRepair.setOnClickListener(this);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

//        serviceType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//                switch (arg0.getItemAtPosition(arg2).toString()){
//                    case "TIRE REPLACEMENT":serviceCost.setText("100.00 PHP");break;
//                    case "ENGINE REPAIR":serviceCost.setText("100.00 PHP");break;
//                    case "POOP CLEANING":serviceCost.setText("100.00 PHP");break;
//                    case "BATTERY REPLACEMENT":serviceCost.setText("100.00 PHP");break;
//                    case "BRAKE REPAIR":serviceCost.setText("100.00 PHP");break;
//                    case "OTHER SERVICE":serviceCost.setText("TBA");break;
//                }
//
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> arg0) {
//
//            }
//
//        });
//
//        addService.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                services = serviceType.getSelectedItem().toString().trim();
//                desc = serviceDescription.getText().toString().trim();
//                cost = serviceCost.getText().toString().replaceAll(" PHP","").trim();
//                toMaps(services,desc,cost);
//            }
//        });
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
    }

    public void toMaps(String services,String desc, String cost) {
        intent = new Intent(getContext(), MapsMarkerActivity.class);
        intent.putExtra("services", services);
        intent.putExtra("desc", desc);
        intent.putExtra("cost", cost);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
     switch (v.getId()) {
         case R.id.tire_replacement:
             toMaps("Tire Replacement", "", "");
             break;
         case R.id.engine_repair:
             toMaps("Engine Repair", "", "");
             break;
         case R.id.poop_cleaning:
             toMaps("Poop Cleaning", "", "");
             break;
         case R.id.battery_replacement:
             toMaps("Battery Replacement", "", "");
             break;
         case R.id.brake_repair:
             toMaps("Brake Repair", "", "");
             break;
     }
    }
}
