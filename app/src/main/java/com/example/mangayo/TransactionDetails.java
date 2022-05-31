package com.example.mangayo;

import android.os.Bundle;

import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.ReferenceQueue;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class TransactionDetails extends AppCompatActivity {
    private String email, type;
    private Spinner paymentTypeSPN;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction_details);
        paymentTypeSPN = (Spinner) findViewById(R.id.spnPaymentType);


    }

//    public void fillSpinner() {
//        ArrayAdapter<String> adapter;
//        List<String> list;
//        View view;
//        list = new ArrayList<>();
//        list.add("CASH");list.add("PAYPMAYA");list.add("GCASH");
//        adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, list);
//        serviceType.setAdapter(adapter);
//    }

    public void getService(){
        String urlString="http://192.168.254.113:9999/Mangayo-Admin/mobile/getUserProfile.php?email="+email+"&userType="+type;
        try {
            URL url =new URL(urlString);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String message=br.readLine();
            br.close();conn.disconnect();
//
//            JSONObject userData = new JSONObject(mechanicData);
//            JSONArray userView = userData.getJSONArray("mechanic");
//            JSONObject json = userView.getJSONObject(0);
//            mechanic_id = json.getString("mechanic_id");
//            sharedPref(mAddress,lat,lng,mechanic_id);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        catch (JSONException e){
//            e.printStackTrace();
//        }
    }
}
