package com.example.mangayo.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import androidx.fragment.app.Fragment;

import com.example.mangayo.R;
import com.example.mangayo.controller.LoginController;
import com.example.mangayo.util.Url;

public class RegistrationUsersFragment extends Fragment {
    private String user, pass, firstName, lastName, phone, type,address;
    private Intent intent;
    private SharedPreferences sharedPreferences;
    private EditText usernameEDT, passwordEDT, firstNameEDT, lastNameEDT, phoneNumberEDT, addressEDT;
    private Button registerUserBTN, backBTN;
    private int status = 0;
   // private String urlString = "http://192.168.254.113:9999/Mangayo-Admin/mobile/mobileRegisterUser.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_users_fragment, container, false);
        setView(view);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        getSharedPref();

        registerUserBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isEmpty();
                registerUsers();
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

    public void registerUsers() {
        Url.registerVehicleOwner += "?Fname=" + firstName + "&Lname=" + lastName + "&email=" + user + "&phone_num="
                + phone + "&password=" + pass + "&userType=" + type + "&address=" + address;
        if (status == 1) {
            try {
                Log.d("URL",  Url.registerVehicleOwner);
                URL url = new URL( Url.registerVehicleOwner);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                String message = br.readLine();
                String message = "SUCCESSFULLY ADDED";
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

    public void getSharedPref() {
        sharedPreferences = getContext().getSharedPreferences("MySharedPref", getContext().MODE_APPEND);
        type = sharedPreferences.getString("userType", "");
        sharedPreferences.edit().remove("userType").commit();
    }

    public void toLoginController() {
        intent = new Intent(getContext(), LoginController.class);
        startActivity(intent);
        getActivity().finish();
    }

    public void setView(View view) {
        usernameEDT = view.findViewById(R.id.edtUsersUsername);
        passwordEDT = view.findViewById(R.id.edtUsersPassword);
        firstNameEDT = view.findViewById(R.id.edtUsersFirstName);
        lastNameEDT = view.findViewById(R.id.edtUsersLastName);
        phoneNumberEDT = view.findViewById(R.id.edtUsersPhoneNumber);
        addressEDT = view.findViewById(R.id.edtUsersAddress);
        registerUserBTN = view.findViewById(R.id.btnRegisterUser);
        backBTN = view.findViewById(R.id.btnUserBack);
    }
}
