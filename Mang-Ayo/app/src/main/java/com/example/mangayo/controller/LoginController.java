package com.example.mangayo.controller;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mangayo.R;
import com.example.mangayo.util.Url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import Service.ForegroundService;

public class LoginController extends AppCompatActivity {
    private String urlString, message, user, pass, type;
    private EditText password, email;private Intent intent;
    private RadioGroup radioGroup;
    private RadioButton radUser, radMechanic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        getSupportActionBar().hide();

        radioGroup = findViewById(R.id.radUserType);
        radUser = findViewById(R.id.radUser);
        radMechanic = findViewById(R.id.radMechanic);
        email = (EditText) findViewById(R.id.edtUsername);
        password = (EditText) findViewById(R.id.edtPassword);
        Button signIn = (Button) findViewById(R.id.btnSignIn);
        TextView register = (TextView) findViewById(R.id.txtSignUp);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = email.getText().toString().trim();
                pass = password.getText().toString().trim();

                if (radUser.isChecked()) {
                    type = radUser.getText().toString().trim();
                } else if (radMechanic.isChecked()) {
                    type = radMechanic.getText().toString().trim();
                }

                if (TextUtils.isEmpty(user)) {
                    email.setError("Empty: Must Fill");
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    password.setError("Empty: Must Fill");
                    return;
                }
                if (TextUtils.isEmpty(type)) {
                    Toast.makeText(LoginController.this, "Pls Select User Type", Toast.LENGTH_SHORT).show();
                    return;
                }
                sharedPref();
                login();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toRegisterController();
            }
        });

    }


    public void login() {
        StringRequest request = new StringRequest(Request.Method.POST, Url.login, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    if(response.equals("success")){
                        switch (type) {
                        case "USER":
                        intent = new Intent(getApplicationContext(), HomepageController.class);
                        startActivity(intent);
                        finish();
                        break;
                        case "MECHANIC":
                        if(!foregroundServiceRunning()){
                        startService();
                        intent = new Intent(getApplicationContext(), MechanicHomepage.class);
                        startActivity(intent);
                        finish();
                         }
                        break;
                        }

                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_SHORT).show();
                    }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", user);
                params.put("password", pass);
                params.put("userType", type);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }

    public void startService(){
        if(!foregroundServiceRunning()) {
            intent = new Intent(this, ForegroundService.class);
            startService(intent);
        }
    }

    public boolean foregroundServiceRunning(){
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for(ActivityManager.RunningServiceInfo serviceInfo : am.getRunningServices(Integer.MAX_VALUE)){
            if(ForegroundService.class.getName().equals(serviceInfo.service.getClassName())){
                return true;
            }
        }
        return false;
    }

    public void sharedPref() {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("email", user);
        myEdit.putString("userType", type);
        myEdit.commit();
    }


    public void toRegisterController() {
        intent = new Intent(this, RegisterController.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        stopService(new Intent(this,ForegroundService.class));
    }
}
