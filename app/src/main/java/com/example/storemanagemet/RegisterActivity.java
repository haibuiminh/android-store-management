package com.example.storemanagemet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

public class RegisterActivity extends AppCompatActivity {

    TextView username;
    TextView password;
    TextView birthdate;
    TextView gender;
    TextView hobbies;
    Button btnExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.tvRegisterUsername);
        password = findViewById(R.id.tvRegisterPassword);
        birthdate = findViewById(R.id.tvRegisterBirthday);
        gender = findViewById(R.id.tvRegisterGender);
        hobbies = findViewById(R.id.tvRegisterHobbies);

        Intent registerIntent = getIntent();
        Gson gson = new Gson();
        User registerUser = gson.fromJson(registerIntent.getStringExtra("registerUser"), User.class);

        username.setText(registerUser.getUsername());
        password.setText(registerUser.getPassword());
        birthdate.setText(registerUser.getBirthdate());
        gender.setText(registerUser.getGender());
        hobbies.setText(registerUser.getHobbies());
        btnExit = findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
    }
}