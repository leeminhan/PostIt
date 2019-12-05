package com.example.postit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.postit.eventlisting.ViewEventsActivity;
import com.example.postit.utils.ReqUtil;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginPageActivity extends AppCompatActivity {

    private EditText emailAddress, password;
    private Button loginBtn;
    Button signUpBtn;
    // TODO change the URL to our url (our url could be wrong)
//    private static String URL_LOGIN = "http://10.12.115.166/newPHP/login.php";
//    private static String URL_LOGIN = "https://api.myjson.com/bins/uda24";
     private static String URL_LOGIN = "http://13.67.95.12:8080/auth/login";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        loginBtn = findViewById(R.id.loginBtn);
        signUpBtn = findViewById(R.id.signUpBtn);
        emailAddress = findViewById(R.id.emailAddress);
        password = findViewById(R.id.password);

        // TODO delete the hardcode for testing
        emailAddress.setText("mihir");
        password.setText("skydiving");

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mUsername = emailAddress.getText().toString().trim();
                String mPassword = password.getText().toString().trim();


                if(!mUsername.isEmpty() || !mPassword.isEmpty()){
                    Login(mUsername, mPassword);
                } else {
                    emailAddress.setError("Please insert email");
                    password.setError("Please insert password");

                }
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), RegistrationPageActivity.class);
            startActivity(intent);

        }
    });

    }

    private void Login(final String username, final String password) {
        JSONObject jsonObject = new JSONObject(new HashMap<String, String>(){{
            put("username", username);
            put("password", password);
        }});
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL_LOGIN, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            int success = response.getInt("status");
                            if (success == 1) {
                                Intent intent = new Intent(LoginPageActivity.this, ViewEventsActivity.class);
                                startActivity(intent);
                                Toast.makeText(LoginPageActivity.this, "Login Success!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String message = null;
                        if (error instanceof NetworkError) {
                            message = "Cannot connect to Internet...Please check your connection!#####";
                        } else if (error instanceof ServerError) {
                            message = "The server could not be found. Please try again after some time!!";
                        } else if (error instanceof AuthFailureError) {
                            message = "Cannot connect to Internet...Please check your connection!";
                        } else if (error instanceof ParseError) {
                            message = "Parsing error! Please try again after some time!!";
                        } else if (error instanceof NoConnectionError) {
                            message = "Cannot connect to Internet...Please check your connection!";
                        } else if (error instanceof TimeoutError) {
                            message = "Connection TimeOut! Please check your internet connection.";
                        }
                        Toast.makeText(LoginPageActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                });


        ReqUtil.getInstance(this).addToRequestQueue(request);
    }
}
