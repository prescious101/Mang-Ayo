package com.example.mangayo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mangayo.R;

import java.util.ArrayList;

import com.example.mangayo.model.VehicleModel;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.ViewHolder> {

    Context context;
    ArrayList<VehicleModel> list;

    public VehicleAdapter(Context context, ArrayList<VehicleModel> list) {
        this.context = context;
        this.list = list;
    }



    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleAdapter.ViewHolder holder, int position) {
        VehicleModel model = list.get(position);
        Log.d("vehiclename", model.getVehicle_name());
        holder.name.setText("Vehicle Name: "+model.getVehicle_name());
        holder.type.setText("Vehicle Type: "+model.getVehicle_type());
        holder.regNum.setText("Reistration No.: "+model.getRegistration_num());
        holder.chasNum.setText("Chasis No.: "+model.getChassis_num());
        holder.model.setText("Vehicle Model: "+model.getVehicle_model());
        holder.fuel.setText("Fuel Type: "+model.getFuel_type());
        Glide.with(context)
                .load(model.getImageUrl())
                .into(holder.vehicleImage);
    }

    @Override
    public long getItemId(int i) { return i; }

    @Override
    public int getItemCount() {
        return list.size();
    }

//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        VehicleHandler vehicleHandler = null;
//        if (view == null) {
//            vehicleHandler = new VehicleHandler();
//            view = inflater.inflate(R.layout.vehicle_list, null);

//            view.setTag(vehicleHandler);
//        } else {
//            vehicleHandler = (VehicleHandler) view.getTag();
//            vehicleHandler.name.setText("Vehicle Name: "+list.get(i).getVehicle_name());
//            vehicleHandler.type.setText("Vehicle Type: "+list.get(i).getVehicle_type());
//            vehicleHandler.regNum.setText("Reistration No.: "+list.get(i).getRegistration_num());
//            vehicleHandler.chasNum.setText("Chasis No.: "+list.get(i).getChassis_num());
//            vehicleHandler.model.setText("Vehicle Model: "+list.get(i).getVehicle_model());
//            vehicleHandler.fuel.setText("Fuel Type: "+list.get(i).getFuel_type());
//        }
//        return view;
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, type, regNum, chasNum, model, fuel;
        ImageView vehicleImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vehicleImage = itemView.findViewById(R.id.imgvVehiclePhotoDB);
            name = itemView.findViewById(R.id.txtVehicleName);
            type = itemView.findViewById(R.id.txtVehicleType);
            regNum = itemView.findViewById(R.id.txtRegisrationNum);
            chasNum = itemView.findViewById(R.id.txtChassisNum);
            model = itemView.findViewById(R.id.txtVehicleModel);
            fuel = itemView.findViewById(R.id.txtFuelType);
        }
    }


}


