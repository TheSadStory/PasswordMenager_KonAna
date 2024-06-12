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

public class LoginActivity extends AppCompatActivity {
    Button loginbutton;
    EditText usernameET, passwordET;
    TextView signupLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        loginbutton = findViewById(R.id.login_button);
        usernameET = findViewById(R.id.login_email);
        passwordET = findViewById(R.id.login_password);
        signupLink = findViewById(R.id.signupRedirectText);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper db = new databaseHelper(LoginActivity.this);
                //CHECKS IF USERNAME AND PASSWORD ARE CORRECT
                String user = usernameET.getText().toString();
                String pass = passwordET.getText().toString();

                //HashPassword
                PasswordHasher pw = new PasswordHasher();
                String passwordHashed = pw.hash(pass);

                boolean b = db.loginUser(user, passwordHashed);

                if(b == true){
                    Intent intent = new Intent(LoginActivity.this, PinActivity.class);
                    int userID = db.getUserId(user); //our Method that gets our ID

                    intent.putExtra("useridKey",userID);//
                    db.close();
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "Diese nix gut", Toast.LENGTH_SHORT).show();
                }
                // CHECKS AND SAVES ID FROM USER



            }
        });

        signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignActivity.class);
                startActivity(intent);
            }
        });




    }
}