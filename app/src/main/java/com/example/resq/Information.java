package com.example.resq;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class Information extends AppCompatActivity {

    private EditText msg;
    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_information);

        // Initialize UI elements
        msg = findViewById(R.id.messageInput);
        send = findViewById(R.id.sendButton);

        send.setOnClickListener(view -> {
            String userMessage = msg.getText().toString().trim();

            if (!userMessage.isEmpty()) {
                new Thread(() -> {
                    String response = sendApiRequest(userMessage);
                    runOnUiThread(() -> {
                        Toast.makeText(this, "Bot Response: " + response, Toast.LENGTH_SHORT).show();
                    });
                }).start();
            } else {
                Toast.makeText(this, "Please enter a message!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String sendApiRequest(String userMessage) {
        OkHttpClient client = new OkHttpClient();

        RequestBody payload = RequestBody.create(
                "{\"inputs\":\"" + userMessage + "\"}",
                MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
                .url("API_ENDPOINT") // Replace with actual endpoint
                .post(payload)
                .addHeader("Authorization", "Bearer YOUR_API_KEY")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            } else {
                return "API request failed with code: " + response.code();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error fetching response: " + e.getMessage();
        }
    }
}