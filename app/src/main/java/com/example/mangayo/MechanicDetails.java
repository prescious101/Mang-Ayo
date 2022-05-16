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
    private String mechanic_id,urlString,message;
    private Intent intent;
    private TextView nameTXT,emailTXT,phoneTXT,specialtyTXT;
    private Button backBTN,bookBTN;
//    private Bundle extras = getIntent().getExtras();;

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

        bookBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookMehanic();
            }
        });

//        if (extras != null) {
//            mechanic_id = extras.getString("mechanic_id");
//            Log.d("Mechanic_id", "onCreate: "+mechanic_id);
//        }
//
//        Toast.makeText(MechanicDetails.this, "The mechanic id is "+ mechanic_id, Toast.LENGTH_SHORT).show();

    }


    public void bookMehanic(){
        urlString="http://192.168.254.104:9999/Mangayo-Admin/sendSmsNotif.php";
        try {
            Log.d("URL",urlString);
            URL url=new URL(urlString);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            message=br.readLine();
            Log.d("LoginController", "addVehicle: "+message);
            Toast.makeText(MechanicDetails.this,message, Toast.LENGTH_SHORT).show();
            br.close();conn.disconnect();
            intent = new Intent(MechanicDetails.this, HomepageController.class);
            startActivity(intent);
            finish();
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
