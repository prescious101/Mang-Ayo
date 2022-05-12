package com.example.mangayo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth fAuth;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fAuth = FirebaseAuth.getInstance();

        Thread background = new Thread() {
            public void run() {
                try {
                    sleep(1 * 1000);
                    intent = new Intent(MainActivity.this, LoginController.class);
                    startActivity(intent);

                    finish();
                } catch (Exception e) {
                }
            }
        };
        background.start();
    }

    public void toHomepageController() {
        intent = new Intent(this, HomepageController.class);
        startActivity(intent);
        finish();
    }
}