package com.example.mangayo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HomeFragment extends Fragment {

    private LinearLayout linearLayout;
    private Context mContext; private View view;
    private TextView showLocation; private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        showLocation = view.findViewById(R.id.txtLocation);

        String address = "Pls Enable Location Settings First";
        sharedPreferences = getContext().getSharedPreferences("MySharedPref", Context.MODE_APPEND);
        address = sharedPreferences.getString("location", "");
        showLocation.setText(address);
        Toast.makeText(getContext(), "GOT YOUR LOCATION!", Toast.LENGTH_SHORT).show();

        return view;
    }

}
