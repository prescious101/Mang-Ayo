package com.example.mangayo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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


import com.example.mangayo.R;

import java.util.ArrayList;
import java.util.List;

public class RegistrationMechanicsFragment extends Fragment {
    private String user, pass, firstName, lastName, phone, specialty, type,address;
    private String urlString = "http://192.168.254.104:9999/Mangayo-Admin/mobileRegisterUser.php";
    private int status = 0;
    private Intent intent;
    private SharedPreferences sharedPreferences;
    private EditText usernameEDT, passwordEDT, firstNameEDT, lastNameEDT, phoneNumberEDT,addressEDT;
    private Button backBTN, registerBTN;
    private Spinner specialtySPN;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_mechanics_fragment, container, false);
        setView(view); fillSpinner(); getSharedPref();
        ((TextView) specialtySPN.getSelectedItem()).setTextColor(Color.WHITE);
        registerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isEmpty();
                if(status == 1){
                    registerMechanics();
                }else
                    Toast.makeText(getContext(), "Pls Fill all data", Toast.LENGTH_SHORT).show();
            }
        });


        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toLoginController();
            }
        });
        return view;
    }


    public void isEmpty() {
        user = usernameEDT.getText().toString().trim();
        pass = passwordEDT.getText().toString().trim();
        firstName = firstNameEDT.getText().toString().trim();
        lastName = lastNameEDT.getText().toString().trim();
        phone = phoneNumberEDT.getText().toString().trim();
        address = addressEDT.getText().toString().trim();
        specialty = specialtySPN.getSelectedItem().toString().trim();

        if (TextUtils.isEmpty(user)) {
            usernameEDT.setError("Empty: Must Fill");
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            passwordEDT.setError("Empty: Must Fill");
            return;
        }
        if (TextUtils.isEmpty(firstName)) {
            firstNameEDT.setError("Empty: Must Fill");
            return;
        }
        if (TextUtils.isEmpty(lastName)) {
            lastNameEDT.setError("Empty: Must Fill");
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            phoneNumberEDT.setError("Empty: Must Fill");
            return;
        }
        if (TextUtils.isEmpty(address)) {
            addressEDT.setError("Empty: Must Fill");
            return;
        }
        status = 1;

    }

    public void fillSpinner() {
        ArrayAdapter<String> adapter;
        List<String> list;
        list = new ArrayList<>();
        list.add("2 Wheels");list.add("3 Wheels");list.add("4 Wheels");list.add("4+ Wheels");
        adapter = new ArrayAdapter<String>(getContext(),R.layout.spinner_item, list);
        specialtySPN.setAdapter(adapter);
    }

    public void toLoginController() {
        intent = new Intent(getContext(), LoginController.class);
        startActivity(intent);
        getActivity().finish();
    }

    public void getSharedPref() {
        sharedPreferences = getContext().getSharedPreferences("MySharedPref", getContext().MODE_APPEND);
        type = sharedPreferences.getString("userType", "");
        sharedPreferences.edit().remove("userType").commit();
    }

    public void setView(View view) {
        usernameEDT = view.findViewById(R.id.edtMechanicUsername);
        passwordEDT = view.findViewById(R.id.edtMechanicPassword);
        firstNameEDT = view.findViewById(R.id.edtMechanicFirstName);
        lastNameEDT = view.findViewById(R.id.edtMechanicLastName);
        phoneNumberEDT = view.findViewById(R.id.edtMechanicPhone);
        addressEDT = view.findViewById(R.id.edtMechanicAddress);
        backBTN = view.findViewById(R.id.btnMechanicBack);
        specialtySPN = view.findViewById(R.id.spnSpecialty);
        registerBTN = view.findViewById(R.id.btnRegisterMechanic);
    }

    public void registerMechanics() {
        urlString += "?Fname=" + firstName + "&Lname=" + lastName + "&email=" + user + "&phone_num="
                + phone + "&password=" + pass + "&userType=" + type + "&address=" + address + "&specialty=" + specialty;
        try {
            Log.d("URL", urlString);
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String message = br.readLine();
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            br.close();conn.disconnect();
            if(message == "SUCCESSFULLY ADDED") {
                toLoginController();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
