package com.example.kidolearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kidolearning.MainActivity;
import com.example.kidolearning.R;

public class registeration_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration_page);

        EditText username, password, repassword, fName, lName;
        Button btnSignup;
        TextView btnSignin;
        DatabaseHelper db;

        fName = (EditText) findViewById(R.id.firstName);
        lName = (EditText) findViewById(R.id.lastName);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        btnSignin = (TextView) findViewById(R.id.btnSignin);
        db = new DatabaseHelper(this);


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fN = fName.getText().toString();
                String lN = lName.getText().toString();
                String UN = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if (fN.isEmpty()) {
                    fName.setError("Your first name is not valid!");
                    fName.requestFocus();
                }
                else if (lN.isEmpty()) {
                    lName.setError("Your last name is not valid!");
                    lName.requestFocus();
                }

                else if (UN.isEmpty() || !UN.contains("@") || !UN.contains(".com")) {
                    username.setError("Email is not valid!");
                    username.requestFocus();
                }
                else if (pass.isEmpty() || pass.length() < 8) {
                    password.setError("Password should contain at least 8 characters.");
                    password.requestFocus();
                }
                else if (repass.isEmpty() || !repass.equals(pass)) {
                    repassword.setError("Passwords Don't match!");
                    repassword.requestFocus();
                    repassword.setText("");
                }
                else {
                    boolean exists = db.checkUser(UN);
                    if (!exists)
                    {
                        boolean regResult = db.insertData(UN, pass);
                        if (regResult)
                        {
                            Toast.makeText(registeration_page.this, "Registration Successful!", Toast.LENGTH_LONG).show();
                            //Intent intent = new Intent(registerActivity.this, HomeActivity.class);
                            //startActivity(intent);
                        }
                        else {
                            Toast.makeText(registeration_page.this, "Registration Failed!", Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(registeration_page.this, "User already exists!\n Please Sign In", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }


}