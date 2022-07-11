package com.example.mangayo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mangayo.R;

import java.util.ArrayList;

import com.example.mangayo.model.VehicleModel;

public class VehicleAdapter extends BaseAdapter {

    Context context;
    ArrayList<VehicleModel> list;
    LayoutInflater inflater;

    public VehicleAdapter(Context context, ArrayList<VehicleModel> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) { return list.get(i); }

    @Override
    public long getItemId(int i) { return i; }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        VehicleHandler vehicleHandler = null;
        if (view == null) {
            vehicleHandler = new VehicleHandler();
            view = inflater.inflate(R.layout.vehicle_list, null);
            vehicleHandler.name = view.findViewById(R.id.txtVehicleName);
            vehicleHandler.type = view.findViewById(R.id.txtVehicleType);
            vehicleHandler.regNum = view.findViewById(R.id.txtRegisrationNum);
            vehicleHandler.chasNum = view.findViewById(R.id.txtChassisNum);
            vehicleHandler.model = view.findViewById(R.id.txtVehicleModel);
            vehicleHandler.fuel = view.findViewById(R.id.txtFuelType);
            view.setTag(vehicleHandler);
        } else {
            vehicleHandler = (VehicleHandler) view.getTag();
            vehicleHandler.name.setText("Vehicle Name: "+list.get(i).getVehicle_name());
            vehicleHandler.type.setText("Vehicle Type: "+list.get(i).getVehicle_type());
            vehicleHandler.regNum.setText("Reistration No.: "+list.get(i).getRegistration_num());
            vehicleHandler.chasNum.setText("Chasis No.: "+list.get(i).getChassis_num());
            vehicleHandler.model.setText("Vehicle Model: "+list.get(i).getVehicle_model());
            vehicleHandler.fuel.setText("Fuel Type: "+list.get(i).getFuel_type());
        }
        return view;
    }

    class VehicleHandler {
        TextView name, type, regNum, chasNum, model, fuel;
    }


}


