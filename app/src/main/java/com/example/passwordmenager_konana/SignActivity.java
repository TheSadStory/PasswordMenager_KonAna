package com.example.passwordmenager_konana;

import android.content.Intent;
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

public class SignActivity extends AppCompatActivity {

        EditText usernameET, passwordET, passwordETconfirm;
        Button signinButton;
        TextView loginLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        signinButton = findViewById(R.id.singup_button);
        usernameET = findViewById(R.id.singup_email);
        passwordET = findViewById(R.id.singup_password);
        passwordETconfirm = findViewById(R.id.singup_confirm);
        loginLink = findViewById(R.id.loginRedirectText);

        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Checks if passwords are the same
                if(!passwordET.getText().toString().equals(passwordETconfirm.getText().toString())){
                    Toast.makeText(SignActivity.this, "Password are not the same", Toast.LENGTH_SHORT).show();
                    return;
                }



                //create table
                UserTable userTable;
                //checks if the username already exist
                databaseHelper db = new databaseHelper(SignActivity.this);
                if (db.checkUserExists(usernameET.getText().toString())) {
                    Toast.makeText(SignActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();
                    return;
                }

                // if everything ok data being saved in table
                try {
                    userTable = new UserTable(usernameET.getText().toString(),passwordET.getText().toString(),-1);
                    Toast.makeText(SignActivity.this, "Singup successful ", Toast.LENGTH_SHORT).show();


                }catch (Exception e) {
                    Toast.makeText(SignActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                    userTable = new UserTable("ERROR","Error",-1);
                }
                db.sendToDatabase(userTable);
                db.close();
                //sends us too login page
                Intent intent = new Intent(SignActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}