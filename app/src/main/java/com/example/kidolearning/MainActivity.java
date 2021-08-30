package com.example.kidolearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kidolearning.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView signUpHere = (TextView) findViewById(R.id.signUpHere);
        signUpHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, registeration_page.class);
                startActivity(intent);
            }
        });

        EditText username, password;
        Button btnLogin;
        DatabaseHelper db;

        username = (EditText) findViewById(R.id.usernameLogin);
        password = (EditText) findViewById(R.id.passwordLogin);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        db = new DatabaseHelper(MainActivity.this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UN = username.getText().toString();
                String pass = password.getText().toString();

                if(UN.equals(""))
                {
                    username.setError("Please fill this empty field!");
                    username.requestFocus();
                }
                else if(pass.equals(""))
                {
                    password.setError("Please fill this empty field!");
                    password.requestFocus();
                }
                else
                {
                    boolean exists = db.login(UN, pass);
                    if(exists)
                    {
                        //move to home page
                        Toast.makeText(MainActivity.this, "You Logged In Successfully!", Toast.LENGTH_LONG).show();
                        //Intent intent = new Intent(getApplicationContext(), //home);
                        //startActivity(intent);
                    }
                    else
                    {
                        password.setError("Invalid Email or Password!");
                        password.requestFocus();
                        password.setText("");
                    }
                }
            }
        });
    }
}