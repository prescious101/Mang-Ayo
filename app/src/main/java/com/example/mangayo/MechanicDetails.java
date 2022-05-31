package com.example.mangayo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import model.AutoMechanicModel;

public class MechanicDetails extends AppCompatActivity {
    private String mechanic_id,lat,lng,services,booking_status = "pending",
            desc,cost,user_id="1",vehicle_id,service_id = String.valueOf(Math.random());
    private ArrayList<AutoMechanicModel> list = new ArrayList<AutoMechanicModel>();
    private Intent intent;private Bundle bundle;
    private TextView nameTXT,emailTXT,phoneTXT,specialtyTXT,addressTXT;
    private Button backBTN,bookBTN;
    private SharedPreferences sharedPreferences ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mechanic_details);
        getSupportActionBar().setTitle("BOOK MECHANIC");
        sharedPreferences = MechanicDetails.this.getSharedPreferences("MySharedPref", Context.MODE_APPEND);
        nameTXT = findViewById(R.id.txtMDName);
        emailTXT = findViewById(R.id.txtMDEmail);
        phoneTXT = findViewById(R.id.txtMDPhone);
        specialtyTXT = findViewById(R.id.txtMDSpecialty);
        addressTXT = findViewById(R.id.txtMDAddress);
        backBTN = findViewById(R.id.btnBackToMechanicsLoc);
        bookBTN = findViewById(R.id.btnConfirmBook);
        bundle = getIntent().getExtras();
        mechanic_id = bundle.getString("mechanic_id");
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        getMechanicDetails();

        nameTXT.setText(list.get(0).getfName()+" "+list.get(0).getlName());
        emailTXT.setText(list.get(0).getEmail());
        phoneTXT.setText(list.get(0).getPhoneNum());
        addressTXT.setText(list.get(0).getAddress());
        specialtyTXT.setText(list.get(0).getSpecialty());

        bookBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookMehanic();
            }
        });

    }


    public void getMechanicDetails(){
        String urlString="http://192.168.254.113:9999/Mangayo-Admin/mobile/getDetails.php";
        Log.d("TAG", "getMechanicDetails: "+ mechanic_id);
        try {
            urlString+="?mechanic_id="+mechanic_id+"&userType=MECHANIC";
            URL url=new URL(urlString);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String message=br.readLine();
            br.close();conn.disconnect();

            JSONObject json1 =  new JSONObject(message);
            JSONArray mechanicArray = json1.getJSONArray("mechanic");
            for (int i = 0; i < mechanicArray.length(); i++) {
                JSONObject json = mechanicArray.getJSONObject(i);
                mechanic_id = json.getString("mechanic_id");
                String lName = json.getString("Lname");
                String fName = json.getString("Fname");
                String email = json.getString("email");
                String address = json.getString("address");
                String specialty = json.getString("specialty");
                String phone = json.getString("phone_num");
                AutoMechanicModel model = new AutoMechanicModel(lName,fName,email,phone,address,specialty);
                list.add(model);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }
    }



    public void bookMehanic(){
        String urlString="http://192.168.254.113:9999/Mangayo-Admin/mobile/addBooking.php";
        lat = sharedPreferences.getString("latitude","");
        lng = sharedPreferences.getString("longitude","");
        vehicle_id = "1";


        try {
            urlString+="?user_id="+user_id+"&mechanic_id="+mechanic_id+"&service_id="
                +service_id+"&vehicle_id="+vehicle_id+"&latitude="+lat+"&longitude="+lng+"&booking_status="+booking_status;
            URL url=new URL(urlString);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String message=br.readLine();
            Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
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
