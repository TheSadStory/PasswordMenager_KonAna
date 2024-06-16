package com.example.passwordmenager_konana;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class PinActivity extends AppCompatActivity {

    Button loginToMain;
    TextView userText;
    databaseHelper DatabaseHelper;
    EditText userPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loginToMain = findViewById(R.id.loginToMain);
        userText = findViewById(R.id.userText);
        userPin = findViewById(R.id.userPin);
        DatabaseHelper = new databaseHelper(this);

        //we bring our userID we sent here
        int userID = getIntent().getIntExtra("useridKey", -1);
        userText.setText("user ID: " + userID);

        System.out.println("bhb"+ userPin.getText().toString());


        loginToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pin = userPin.getText().toString();


                    Intent intent = new Intent(PinActivity.this, MainActivity.class);
                    intent.putExtra("useridKey", userID);
                    intent.putExtra("userPin", pin);
                    startActivity(intent);

            }
        });
    }




}