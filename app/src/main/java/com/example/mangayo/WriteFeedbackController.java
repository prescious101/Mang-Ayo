package com.example.mangayo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WriteFeedbackController extends AppCompatActivity {
    private EditText writeReview; private Spinner getScore; private Button addReview;

    private String user_id="1",mechanic_id,feedback_description,feedback_score,date;
    private String urlString = "http://192.168.254.104:9999/Mangayo-Admin/sendFeedback.php";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_feedback);
        writeReview = findViewById(R.id.edtWriteFeedback);
        getScore = findViewById(R.id.spnScore);
        addReview = findViewById(R.id.btnSendFeedback);
        fillSpinner();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Date dt = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date = dateFormat.format(dt);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mechanic_id = extras.getString("mechanic_id");
            Log.d("Mechanic_id", "onCreate: "+mechanic_id);
        }

        addReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getlistofMechanics();
                finish();
            }
        });
    }

    public void getlistofMechanics() {
        feedback_description = writeReview.getText().toString().trim();
        feedback_score = getScore.getSelectedItem().toString();

        urlString+="?user_id="+user_id+"&mechanic_id="+mechanic_id+"&feedback_description="+
                feedback_description+"&feedback_score="+feedback_score+"&feedback_date="+date;

        System.out.println(urlString);
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String status = br.readLine();
            Log.d("Status", "getListofMechanics: "+ status);
            br.close(); conn.disconnect();
            Toast.makeText(WriteFeedbackController.this, status, Toast.LENGTH_SHORT).show();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fillSpinner() {
        ArrayAdapter<String> adapter;
        List<String> list;
        list = new ArrayList<String>();
        list.add("1");list.add("2");list.add("3");list.add("4");list.add("5");
        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getScore.setAdapter(adapter);
    }



}
