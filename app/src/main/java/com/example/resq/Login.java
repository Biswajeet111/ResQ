package com.example.resq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        Button emer, sign;
        sign = findViewById(R.id.sign);
        emer = findViewById(R.id.emer);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent next = new Intent(Login.this,Information.class);
//                startActivity(next);
            }
        });

        emer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent next = new Intent(Login.this,MapsActivity.class);
//                startActivity(next);
            }
        });
    }
}

