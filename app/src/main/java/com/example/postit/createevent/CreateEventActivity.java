package com.example.postit.createevent;

import android.content.Intent;
import android.net.Uri;
import androidx.annotation.Nullable;
import com.example.postit.FirebaseStorageController;
import com.example.postit.utils.GenUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.postit.R;
import com.example.postit.models.Event;
import com.example.postit.utils.BottomNavMenu;
import com.example.postit.utils.ReqUtil;
import org.json.JSONObject;

public class CreateEventActivity extends AppCompatActivity implements NewEventFragment.OnCreateEventListener,
    BottomNavMenu.NavActivity {

    private static final String TAG = "CreateEventActivity";
    private BottomNavigationView navigation;
    private BottomNavMenu menu;
    public static final String CURRENT_FRAGMENT = "CURRENT_FRAGMENT_CREATE_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        Fragment fragment = new ChooseCategoryFragment();
        FragmentManager fManager = getSupportFragmentManager();
        FragmentTransaction transaction = fManager.beginTransaction();

        transaction.add(R.id.create_event_container, fragment, CURRENT_FRAGMENT);
        transaction.addToBackStack("chooseCategoryFragment");
        transaction.commit();

        navigation = findViewById(R.id.navbar_create_event);
        menu = new BottomNavMenu(this, navigation);
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
        FirebaseStorageController.uploadImage(event.getImagePath(), event.getTitle(),
            new FirebaseStorageController.UploadImageListener() {
                @Override
                public void onSuccess(Uri uploadImgUri) {
                    Log.i(TAG, "Image uploaded to Firebase");
                    event.setWebImgUrl(uploadImgUri);
                    publishEvent(event);
                }

                @Override
                public void onError(String err) {
                    Log.e(TAG, "Unable to upload image to Firebase, using default image instead");
                    Uri uploadImgUri = Uri.parse(getResources().getString(R.string.default_event_image_url));
                    event.setWebImgUrl(uploadImgUri);
                    publishEvent(event);
                }
            });
    }

    private void publishEvent(Event event) {
        // TODO: Replace Url with actual backend API Url
        final String url = getString(R.string.backend_base_url);
        final JSONObject json = new JSONObject(event.toMap());

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

    @Override
    public void exit() {
        finish();
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

    private void onPickImageResult(Intent data) {
        try {
            ((NewEventFragment) getSupportFragmentManager().findFragmentByTag(CURRENT_FRAGMENT))
                    .setBitmap(data.getData());
            Toast.makeText(this,"Image Changed", Toast.LENGTH_SHORT).show();
        } catch (NullPointerException | ClassCastException ex) {
            Log.e(TAG, ex.getMessage());
            Toast.makeText(this,"Failed to change images", Toast.LENGTH_SHORT).show();
        }
    }


}