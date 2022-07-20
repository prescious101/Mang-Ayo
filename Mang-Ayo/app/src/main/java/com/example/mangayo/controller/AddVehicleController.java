package com.example.mangayo.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
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

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AddVehicleController extends AppCompatActivity {
    private EditText vehicleBrand,vehicleModel,fuelType;
    private String brand, model, fuel,urlString,message,uploadImage;
    private Button saveVehicleData,getVehicleImage;
    private ImageView setVehiclePhoto;
    private Intent intent;
    private Uri filePath;
    private Bitmap bitmap;
    private SharedPreferences sharedPreferences;
    private static final int PICK_IMAGE = 100;
    Uri uriValidId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_vehicle);

        getSupportActionBar().setTitle("ADD VEHICLE");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        vehicleBrand = findViewById(R.id.edtVehicleBrand);
        vehicleModel = findViewById(R.id.edtVehicleModel);
        fuelType = findViewById(R.id.edtFuelType);
        saveVehicleData = findViewById(R.id.btnSaveVehicle);
        getVehicleImage = findViewById(R.id.btnUploadVehicleImage);
        setVehiclePhoto = findViewById(R.id.imgvVehicleImage);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        saveVehicleData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                brand = vehicleBrand.getText().toString().trim();
                model = vehicleModel.getText().toString().trim();
                fuel = fuelType.getText().toString().trim();
                setSaveVehicleData();
            }
        });

        getVehicleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(); //blind intent
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            uriValidId = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriValidId);
                setVehiclePhoto.setImageURI(uriValidId);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes,Base64.DEFAULT);
        return encodedImage;
    }

    public void setSaveVehicleData(){
        StringRequest request = new StringRequest(Request.Method.POST, Url.addVehicle, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("responseString", response);
                if(response.equals("success")){
                    Log.d("success", response);
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
                params.put("brand", brand);
                params.put("model", model);
                params.put("fuel", fuel);
                params.put("image", getStringImage(bitmap));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }


}
