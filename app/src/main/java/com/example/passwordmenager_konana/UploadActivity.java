package com.example.passwordmenager_konana;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UploadActivity extends AppCompatActivity {

    EditText platform, username, password;
    Button saveButton;
    EncryptionHelper encryptionHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_upload);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        platform = findViewById(R.id.uploadPlatform);
        username = findViewById(R.id.uploadUsername);
        password = findViewById(R.id.uploadPassword);
        saveButton = findViewById(R.id.saveButton);

        int userID = getIntent().getIntExtra("useridKey2", -1);
        String pin = getIntent().getStringExtra("userPin");

        System.out.println("UPLOAD ACTIVITY USER ID USER ID USER ID: " + userID);

        encryptionHelper = new EncryptionHelper();
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plat = platform.getText().toString();
                String user = username.getText().toString();
                String pass = password.getText().toString();

                String pin = getIntent().getStringExtra("userpin");

                System.out.println(pin);
                pin = "1234";

                try {
                    // Encrypt the original text
                    byte[] encryptedData = EncryptionHelper.encrypt(pass, pin);
                    String encryptedText = Base64.encodeToString(encryptedData, Base64.DEFAULT);
                    Log.d(TAG, "Encrypted Text: " + encryptedText);
                    System.out.println("gshdhdfffffff");
                    System.out.println("gshdhdfffffff");
                    System.out.println("gshdhdfffffff");
                    System.out.println("gshdhdfffffff");
                    System.out.println("gshdhdfffffff");
                    System.out.println("gshdhdfffffff");
                    System.out.println("gshdhdfffffff");
                    System.out.println("gshdhdfffffff");


                    // Decrypt the encrypted text
                    byte[] decodedData = Base64.decode(encryptedText, Base64.DEFAULT);
                    String decryptedText = EncryptionHelper.decrypt(decodedData, pin);
                    Log.d(TAG, "Decrypted Text: " + decryptedText);
                    System.out.println("gshdhdfffffff");
                    System.out.println("gshdhdfffffff");
                    System.out.println("gshdhdfffffff");
                    System.out.println("gshdhdfffffff");
                    System.out.println("gshdhdfffffff");


                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("problem");
                }

                databaseHelper db = new databaseHelper(UploadActivity.this);
                UserDataTable userdataTable;
                try {
                    userdataTable = new UserDataTable(3, plat, user, pass, userID);
                    Toast.makeText(UploadActivity.this, "saved data", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UploadActivity.this, MainActivity.class);
                    intent.putExtra("useridKey", userID);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(UploadActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                    userdataTable = new UserDataTable(0, "ERROR", "Error", "error", -1);
                }
                //sends us too login page
                db.sendToDatabase2(userdataTable);

            }
        });


    }


}