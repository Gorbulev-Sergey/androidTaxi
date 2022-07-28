package ru.gorbulevsv.androidtaxi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {
    Button buttonDriver, buttonClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        buttonDriver = findViewById(R.id.buttonDriver);
        buttonDriver.setOnClickListener(view -> {
            startActivity(new Intent(WelcomeActivity.this, DriverRegisterLoginActivity.class));
        });
        buttonClient = findViewById(R.id.buttonClient);
        buttonClient.setOnClickListener(view -> {
            startActivity(new Intent(WelcomeActivity.this, ClientRegisterLoginActivity.class));
        });
    }
}