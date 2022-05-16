package com.example.mangayo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mangayo.R;

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
import java.util.HashMap;
import java.util.Map;

import model.AutoMechanicModel;
import view.MechanicListAdapter;

public class FeedbackController extends AppCompatActivity {

    private ListView lv;
    private Intent intent;
    private MechanicListAdapter mechanicListAdapter;
    private ArrayList<AutoMechanicModel> list = new ArrayList<AutoMechanicModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mechanics);

        lv = findViewById(R.id.listMechanics);
        mechanicListAdapter = new MechanicListAdapter(FeedbackController.this, list);
        lv.setAdapter(mechanicListAdapter);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        getlistofMechanics();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent = new Intent(FeedbackController.this, WriteFeedbackController.class);
                intent.putExtra("mechanic_id",list.get(i).getId());
                startActivity(intent);
            }
        });

    }

    public void getlistofMechanics() {
        String urlString = "http://192.168.254.114:9999/Mangayo-Admin/getListOfMechanic.php";
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String mechanics = br.readLine();
            br.close();
            conn.disconnect();
            Log.d("LISTOFMECHANICS", "getListofMechanics: "+ mechanics);
            JSONArray mechanicsArray = new JSONArray(mechanics);
            Log.d("LISTOFMECHANICS", "JSONARRAY: " + mechanicsArray);

            for (int i = 0; i < mechanicsArray.length(); i++) {
                JSONObject json = mechanicsArray.getJSONObject(i);
                String id = json.getString("mechanic_id");
                String fName = json.getString("Fname");
                String lName = json.getString("Lname");
                String email = json.getString("email");
                String address = json.getString("address");
                String specialty = json.getString("specialty");
                list.add(new AutoMechanicModel(id, fName, lName, email, address, specialty));
            }

            Log.d("ARRAYLIST", "LIST: "+list.toArray());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
