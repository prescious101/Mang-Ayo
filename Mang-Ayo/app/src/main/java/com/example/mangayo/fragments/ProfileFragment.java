package com.example.mangayo.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mangayo.R;
import com.example.mangayo.controller.FeedbackController;
import com.example.mangayo.controller.TransactionHistoryController;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.example.mangayo.model.UserModel;

public class ProfileFragment extends Fragment {
    private Intent intent; private String type,email;
    private Button btnTransact,btnFeedback;
    private TextView tvname, tvemail, tvphone;
    private View view; private UserModel userModel;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.profile_fragment, container, false);
        tvname = view.findViewById(R.id.txtProfileName);
        tvemail = view.findViewById(R.id.txtProfileEmail);
        tvphone = view.findViewById(R.id.txtProfilePhone);
        btnTransact = view.findViewById(R.id.btnTransHistory);
        btnFeedback = view.findViewById(R.id.btnReviewMechanics);

        sharedPreferences = getContext().getSharedPreferences("MySharedPref", Context.MODE_APPEND);
        type = sharedPreferences.getString("userType", "");
        email = sharedPreferences.getString("email", "");

        getUserData();

        btnTransact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getContext(), TransactionHistoryController.class);
                startActivity(intent);
            }
        });

        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getContext(), FeedbackController.class);
                startActivity(intent);
            }
        });


        return view;
    }


    public void getUserData() {
        String urlString = "http://192.168.254.113:9999/Mangayo-Admin/getUserProfile.php?email="+email+"&userType="+type;
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String users = br.readLine();
            Log.d("Profile", "getUserData: "+ users);
            br.close();
            conn.disconnect();

            JSONObject userData = new JSONObject(users);
            JSONObject userView = userData.getJSONObject("users");
            tvname.setText(userView.getString("Fname") +" "+ userView.getString("Lname"));
            tvemail.setText(userView.getString("email"));
            tvphone.setText(userView.getString("phone_num"));

        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }

    }
}
