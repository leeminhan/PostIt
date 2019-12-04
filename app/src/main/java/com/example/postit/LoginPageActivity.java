package com.example.postit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.postit.eventlisting.ViewEventsActivity;

public class LoginPageActivity extends AppCompatActivity {

    Button loginBtn;
    Button signUpBtn;
    EditText emailAddress;
    EditText password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        loginBtn = findViewById(R.id.loginBtn);
        signUpBtn = findViewById(R.id.signUpBtn);
        emailAddress = findViewById(R.id.emailAddress);
        password = findViewById(R.id.password);

    signUpBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), RegistrationPageActivity.class);
            startActivity(intent);
        }
    });

    }
}
