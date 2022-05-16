package com.example.mangayo;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginController extends AppCompatActivity {
    private String urlString, message, user, pass, type;
    private EditText password, email;private Intent intent;
    private RadioGroup radioGroup;
    private RadioButton radUser, radMechanic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
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
        Toast.makeText(LoginController.this, user + " " + pass + " " + type, Toast.LENGTH_SHORT).show();
        urlString = "http://192.168.254.114:9999/Mangayo-Admin/loginCheck.php?username=" + user + "&password=" + pass + "&userType=" + type;
        try {
            Log.d("URL", urlString);
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            message = br.readLine();
            Log.d("LoginController", "login: " + message);
            Toast.makeText(LoginController.this, message, Toast.LENGTH_SHORT).show();
            br.close();
            conn.disconnect();
            if (message.contentEquals("LOGIN ACCEPTED")) {
                switch (type) {
                    case "USER":
                        intent = new Intent(this, HomepageController.class);
                        startActivity(intent);
                        finish();
                        break;
                    case "MECHANIC":
                        intent = new Intent(this, MechanicHomepage.class);
                        startActivity(intent);
                        finish();
                        break;
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
