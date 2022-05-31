package com.example.mangayo;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
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

import model.TransactionModel;
import view.TransactionAdapter;

public class TransactionHistoryController extends AppCompatActivity {
    private ListView lv;
    private TransactionAdapter transactionAdapter;
    private ArrayList<TransactionModel> list = new ArrayList<TransactionModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transation_history);


        lv = findViewById(R.id.listTransHistory);
        transactionAdapter = new TransactionAdapter(TransactionHistoryController.this, list);
        lv.setAdapter(transactionAdapter);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        getTransactionHistory();


    }


    public void getTransactionHistory() {
        String uid = "1";
        String urlString = "http://192.168.254.113:9999/Mangayo-Admin/mobile/getTransactionHistory.php?userID="+uid;
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String transactions = br.readLine();
            br.close();conn.disconnect();

            JSONObject json1 = new JSONObject(transactions);
            JSONArray transactionArray = json1.getJSONArray("transaction");
            for (int i = 0; i < transactionArray.length(); i++) {
                JSONObject json = transactionArray.getJSONObject(i);
                String transNum = json.getString("transaction_id");
                String date = json.getString("date_service");
                String serviceType = json.getString("service_type");
                String payCost = json.getString("service_cost");
                String payType = json.getString("payment_type");
                TransactionModel model = new TransactionModel(transNum, date, serviceType, payType, payCost);
                list.add(model);
            }
            transactionAdapter.notifyDataSetChanged();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
