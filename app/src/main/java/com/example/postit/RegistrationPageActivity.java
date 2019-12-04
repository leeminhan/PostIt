package com.example.postit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.postit.eventlisting.ViewEventsActivity;

public class RegistrationPageActivity extends AppCompatActivity {

    EditText newUserName;
    EditText newEmailAddress;
    EditText newPassword;
    Button next;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_activity);

        newUserName = findViewById(R.id.newUserName);
        newPassword = findViewById(R.id.newPassword);
        newEmailAddress = findViewById(R.id.newEmail);
        next = findViewById(R.id.button);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileSetupActivity.class);
                startActivity(intent);
            }
        });

    }


}
