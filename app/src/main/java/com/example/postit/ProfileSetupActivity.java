package com.example.postit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.example.postit.eventlisting.ViewEventsActivity;
import com.example.postit.utils.GenUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class ProfileSetupActivity extends AppCompatActivity {

    ImageButton uploadProfileImageBtn;
    ImageButton shoppingBtn;
    ImageButton sportsBtn;
    ImageButton gamesBtn;
    ImageButton foodBtn;
    ImageButton othersBtn;
    Button nextBtn;
    RequestQueue requestQueue;
    String url;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_preferences_setup);

        uploadProfileImageBtn = findViewById(R.id.uploadProfileImageBtn);
        shoppingBtn = findViewById(R.id.shoppingBtn);
        sportsBtn = findViewById(R.id.sportsBtn);
        gamesBtn = findViewById(R.id.gamesBtn);
        foodBtn = findViewById(R.id.foodBtn);
        othersBtn = findViewById(R.id.othersBtn);
        nextBtn = findViewById(R.id.nextBtn);

        requestQueue = Volley.newRequestQueue(ProfileSetupActivity.this);

        HashMap<String, String> jsonParams = new HashMap<String, String>();

        //HardCode
        jsonParams.put("username","profile_preferences");
        jsonParams.put("password","profile_preferences");
        jsonParams.put("telegram","tele");


        // Upload Profile Images
        uploadProfileImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Todo: prompt user to upload from gallery
                // Selected image upload to database

                GenUtils.chooseImage(ProfileSetupActivity.this);

            }
        });

        shoppingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParams.put("preference", "shopping");

                url = "http://13.67.95.12:8080/auth/register";
                Log.d("request", "error");
                JsonObjectRequest postRequest = new JsonObjectRequest( Request.Method.POST, url,
                        new JSONObject(jsonParams),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    Log.d("JSON",  "onResponse" + response.getString("title"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //   Handle Error
                                Log.d("Error","onErrorResponse:" + error.getMessage());
                            }
                        });

                requestQueue.add(postRequest);
            }
        });

        sportsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParams.put("sports", "1");
            }
        });

        gamesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParams.put("games", "1");
            }
        });

        foodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParams.put("food", "1");
            }
        });

        othersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParams.put("others", "1");
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Todo: Go to EventsMarketPlace
                Intent intent = new Intent(getApplicationContext(), ViewEventsActivity.class);
                startActivity(intent);            }
        });


//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    Log.d("JSON",  "onResponse" + response.getString("title"));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("Error","onErrorResponse:" + error.getMessage());
//
//            }
//        });

//        url = "http://13.67.95.12:8080/register";
//        Log.d("request", "error");
//        JsonObjectRequest postRequest = new JsonObjectRequest( Request.Method.POST, url,
//                new JSONObject(jsonParams),
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            Log.d("JSON",  "onResponse" + response.getString("title"));
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        //   Handle Error
//                        Log.d("Error","onErrorResponse:" + error.getMessage());
//                    }
//        });
//
//        requestQueue.add(postRequest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case GenUtils.PICK_IMAGE_REQUEST:
                if (resultCode != RESULT_OK || data == null || data.getData() == null) return;
                onPickImageResult(data);
        }
    }

    public void onPickImageResult(Intent data){
        try{
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
            uploadProfileImageBtn.setImageBitmap(bitmap);
        }catch (IOException ex){
            Log.e("error", ex.getMessage());
        }

    }
}
