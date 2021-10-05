package com.example.storemanagemet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Calendar;
import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    EditText retypePassword;
    EditText tvBirthDay;
    Button btnReset;
    Button btnSelect;
    Button btnSignup;
    RadioButton male;
    RadioButton female;
    CheckBox tennis;
    CheckBox football;
    CheckBox others;

    Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            updateLabel();
        }
    };


    private void handleClickResetButton() {
        username.setText("");
        password.setText("");
        retypePassword.setText("");
        female.setChecked(false);
        male.setChecked(false);
        tennis.setChecked(false);
        football.setChecked(false);
        others.setChecked(false);

        myCalendar = Calendar.getInstance();
        updateLabel();
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);

        tvBirthDay.setText(sdf.format(myCalendar.getTime()));
    }

    private void initialLoadingElement() {
        username = findViewById(R.id.edtUsername);
        password = findViewById(R.id.edtPassword);
        retypePassword = findViewById(R.id.edtPasswordRetype);
        male = findViewById(R.id.radioButton_male);
        female = findViewById(R.id.radioButton_female);
        tennis = findViewById(R.id.tennis);
        football = findViewById(R.id.footbal);
        others = findViewById(R.id.others);
        tvBirthDay = findViewById(R.id.tvDate);
        updateLabel();
        btnReset = findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleClickResetButton();
            }
        });

        btnSelect = findViewById(R.id.btnSelectDate);
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(view.getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        tvBirthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(view.getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnSignup = findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textPassword = password.getText().toString();
                String textRetypePassword = retypePassword.getText().toString();

                if (!textPassword.equals(textRetypePassword)) {
                    Toast.makeText(view.getContext(),"Retype Password does not match!", Toast.LENGTH_LONG).show();
                    return;
                }

                String textBirthDate = tvBirthDay.getText().toString();

                String strPattern = "^\\d{2}/\\d{2}/\\d{4}$";

                if (!textBirthDate.matches(strPattern)) {
                    Toast.makeText(view.getContext(), "Birthdate is invalid format!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent registerIntent = new Intent(view.getContext(), RegisterActivity.class);
                User registerUser = new User();
                registerUser.setUsername(username.getText().toString());
                registerUser.setPassword(textPassword);
                registerUser.setBirthdate(textBirthDate);

                if (male.isChecked()) {
                    registerUser.setGender("Male");
                } else if (female.isChecked()){
                    registerUser.setGender("Female");
                } else {
                    registerUser.setGender("Undefined");
                }

                String hobbies = "";
                if (football.isChecked()) {
                    hobbies = hobbies + " Football";
                }
                if (tennis.isChecked()) {
                    hobbies = hobbies + " Tennis";
                }
                if (others.isChecked()) {
                    hobbies = hobbies + " Others";
                }
                registerUser.setHobbies(hobbies.trim());

                registerIntent.putExtra("registerUser", new Gson().toJson(registerUser));
                startActivity(registerIntent);
                finish();
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialLoadingElement();

    }
}