package com.example.postit.createevent;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.postit.R;
import com.example.postit.models.Event;
import com.example.postit.models.EventTemplate;
import com.example.postit.utils.ReqUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONObject;

public class CreateEventActivity extends AppCompatActivity implements NewEventFragment.OnCreateEventListener {

    private static final String TAG = "CreateEventActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        Fragment fragment = new ChooseCategoryFragment();
        FragmentManager fManager = getSupportFragmentManager();
        FragmentTransaction transaction = fManager.beginTransaction();

        transaction.add(R.id.create_event_container, fragment);
        transaction.addToBackStack("chooseCategoryFragment");
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof NewEventFragment) {
            ((NewEventFragment) fragment).setOnCreateEventListener(this);
        }
    }

    @Override
    public void onCreateEvent(Event event) {
        publishEvent(event);
    }

    public void publishEvent(Event event) {
        // TODO: Replace Url with actual backend API Url
        final String url = getString(R.string.backend_base_url);
        final JSONObject json = new JSONObject(event.toMap());
        final Gson gson = new Gson();
        String test = gson.toJson(event);
        JsonObject json2 = new JsonParser().parse(test).getAsJsonObject();

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, json, (JSONObject res) -> {
            Toast.makeText(getApplicationContext(), "Event Published", Toast.LENGTH_SHORT).show();
            Log.d(TAG, res.toString());
        }, (VolleyError err) -> {
            Log.e(TAG, err.getMessage() == null ? "Unknown Error" : err.getMessage());
            Toast.makeText(getApplicationContext(), "Error publishing event", Toast.LENGTH_SHORT).show();
        });
        ReqUtil.getInstance(this).addToRequestQueue(req);

        finish();
    }
}