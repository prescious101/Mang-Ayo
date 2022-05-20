package com.example.mangayo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.os.StrictMode;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;

public class MechanicDetails extends AppCompatActivity {
    private String urlString,message,mechanic_id,services,desc,cost,user_id;
    private Intent intent;private Bundle bundle;
    private TextView nameTXT,emailTXT,phoneTXT,specialtyTXT;
    private Button backBTN,bookBTN;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mechanic_details);
        nameTXT = findViewById(R.id.txtMDName);
        emailTXT = findViewById(R.id.txtMDEmail);
        phoneTXT = findViewById(R.id.txtMDPhone);
        specialtyTXT = findViewById(R.id.txtMDSpecialty);
        backBTN = findViewById(R.id.btnBackToMechanicsLoc);
        bookBTN = findViewById(R.id.btnConfirmBook);
        bundle = getIntent().getExtras();
        bookBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookMehanic();
            }
        });

    }


    public void bookMehanic(){
        urlString="http://192.168.254.104:9999/Mangayo-Admin/mobileServices.php";
        try {
            urlString+="?insertName="+services+"&insertDescription="+desc+"&insertCost="+cost+"&user_id="+user_id+"&mechanic_id="+mechanic_id;
            Log.d("URL",urlString);
            URL url=new URL(urlString);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            message=br.readLine();
            Toast.makeText(MechanicDetails.this,message,Toast.LENGTH_SHORT).show();
            br.close();conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
