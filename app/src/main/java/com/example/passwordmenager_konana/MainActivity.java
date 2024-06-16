package com.example.passwordmenager_konana;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton button;
    ListView Liste;
    TextView userText, pinText;
    databaseHelper DatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        button= findViewById(R.id.fabutton);
        Liste = findViewById(R.id.Liste);
        userText = findViewById(R.id.userText);
        pinText = findViewById(R.id.pinText);
        DatabaseHelper = new databaseHelper(this);

        //we bring our userID we sent here
        int userID = getIntent().getIntExtra("useridKey",-1);
        userText.setText("user ID: "+userID);

        String pin = getIntent().getStringExtra("userpin");



        Cursor cursor = DatabaseHelper.getData(userID);


        String[] seeData = {"PlatformName", "Username", "Password"};
        int[] DatafromID = {R.id.seePlattform,R.id.seeUsername,R.id.seePassword}; //ka wieso int, string hat fehler angezeigt
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.activity_list, cursor,seeData,DatafromID,0){

        };
        Liste.setAdapter(adapter);

        System.out.println(pin);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UploadActivity.class);
                intent.putExtra("useridKey2",userID);
                intent.putExtra("userPin", pin);
                startActivity(intent);
            }


        });



    }
}