package com.example.storemanagemet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    EditText retypePassword;
    Button btnReset;
    RadioButton male;
    RadioButton female;
    CheckBox tennis;
    CheckBox football;
    CheckBox others;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.edtUsername);
        password = findViewById(R.id.edtPassword);
        retypePassword = findViewById(R.id.edtPasswordRetype);
        male = findViewById(R.id.radioButton_male);
        female = findViewById(R.id.radioButton_female);
        tennis = findViewById(R.id.tennis);
        football = findViewById(R.id.footbal);
        others = findViewById(R.id.others);

        btnReset = findViewById(R.id.btnReset);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username.setText("");
                password.setText("");
                retypePassword.setText("");
                female.setChecked(false);
                male.setChecked(false);
                tennis.setChecked(false);
                football.setChecked(false);
                others.setChecked(false);
            }
        });
    }
}