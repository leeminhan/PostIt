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
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.postit.eventlisting.ViewEventsActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginPageActivity extends AppCompatActivity {

    private EditText emailAddress, password;
    private Button loginBtn;
    Button signUpBtn;
    // TODO change the URL to our url
//    private static String URL_LOGIN = "http://10.12.115.166/newPHP/login.php";
    private static String URL_LOGIN = "http://13.67.95.12/auth/login";
    // http://13.67.95.12/auth/login


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
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String sucess = jsonObject.getString("status");
                            Toast.makeText(LoginPageActivity.this, "Login Success!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LoginPageActivity.this, ViewEventsActivity.class);
                            startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginPageActivity.this, "Error "+e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginPageActivity.this, "Error "+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
