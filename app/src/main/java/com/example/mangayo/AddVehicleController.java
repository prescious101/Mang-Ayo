package com.example.mangayo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

public class AddVehicleController extends AppCompatActivity {
    private EditText vehicleBrand,vehicleModel,fuelType;
    private String brand, model, fuel,urlString,message,uploadImage;
    private Button saveVehicleData,getVehicleImage;
    private ImageView setVehiclePhoto;
    private Intent intent;
    private Uri filePath;
    private Bitmap bitmap;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_vehicle);

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
                intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, 3);
            }
        });



    }


    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && data!=null){
            try{
                filePath = data.getData();
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                setVehiclePhoto.setImageURI(filePath);
            }catch (IOException e){
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
        urlString="http://192.168.254.104:9999/Mangayo-Admin/mobileAddVehicle.php?brand="+brand+"&model="+model+"&fuel="+fuel+
                "&vehicle_image="+fuel;
        try {
            Log.d("URL",urlString);
            URL url=new URL(urlString);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            message=br.readLine();
            Log.d("LoginController", "addVehicle: "+message);
            Toast.makeText(AddVehicleController.this,message, Toast.LENGTH_SHORT).show();
            br.close();conn.disconnect();
            intent = new Intent(AddVehicleController.this, HomepageController.class);
            startActivity(intent);
            finish();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
