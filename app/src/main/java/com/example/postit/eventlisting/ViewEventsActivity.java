package com.example.postit.eventlisting;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;
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
