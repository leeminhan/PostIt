package com.example.postit.eventlisting;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.postit.R;
import com.example.postit.utils.BottomNavMenu;

public class ViewEventsActivity extends AppCompatActivity implements BottomNavMenu.NavActivity {

    private BottomNavigationView navigation;
    private BottomNavMenu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_events);

        navigation = (BottomNavigationView) findViewById(R.id.navbar_view_events);
        menu = new BottomNavMenu(this, navigation);
    }

    @Override
    public void exit() {
        finish();
    }
}
