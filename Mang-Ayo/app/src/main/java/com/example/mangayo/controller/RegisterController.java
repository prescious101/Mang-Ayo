package com.example.mangayo.controller;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mangayo.R;
import com.example.mangayo.fragments.RegistrationMechanicsFragment;
import com.example.mangayo.fragments.RegistrationUsersFragment;

import java.util.ArrayList;
import java.util.List;


public class RegisterController extends AppCompatActivity {
    private String type = "";
    private Spinner spinner;

    RegistrationMechanicsFragment registrationMechanicsFragment = new RegistrationMechanicsFragment();
    RegistrationUsersFragment registrationUsersFragment = new RegistrationUsersFragment();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        spinner = (Spinner) findViewById(R.id.spnUser);
        getSupportActionBar().hide();
        fillSpinner();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView)adapterView.getChildAt(0)).setTextColor(Color.WHITE);
                sharedPref(adapterView.getItemAtPosition(i).toString());
                Toast.makeText(RegisterController.this, adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
                switch (adapterView.getItemAtPosition(i).toString()) {
                    case "VEHICLE OWNER":
                        getSupportFragmentManager().beginTransaction().replace(R.id.registerContainer, registrationUsersFragment).commit();
                        break;
                    case "AUTO-MECHANIC":
                        getSupportFragmentManager().beginTransaction().replace(R.id.registerContainer, registrationMechanicsFragment).commit();
                       break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    public void fillSpinner() {
        ArrayAdapter<String> adapter;
        List<String> list;list = new ArrayList<>();
        list.add("VEHICLE OWNER");list.add("AUTO-MECHANIC");
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void sharedPref(String userType) {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("userType", userType);
        myEdit.commit();
    }


}
