package com.example.postit.eventlisting;

import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
 import androidx.fragment.app.FragmentManager;
 import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import com.example.postit.R;
import com.example.postit.utils.BottomNavMenu;

public class ViewEventsActivity extends AppCompatActivity implements BottomNavMenu.NavActivity {

    // strings for logcat,for Log.i(TAG, msg)
    private static final String COMMON_TAG = "CombinedLifeCycle";
    private static final String ACTIVITY_NAME = ViewEventsActivity.class.getSimpleName();
    private static final String TAG = COMMON_TAG;

//    // clickable tv
//    TextView textView;

    private BottomNavigationView navigation;
    private BottomNavMenu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_events);

        navigation = (BottomNavigationView) findViewById(R.id.navbar_view_events);
        menu = new BottomNavMenu(this, navigation);

        addFragment();
    }

    private void addFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        EventListFragment eventListFragment = new EventListFragment();
        fragmentTransaction.add(R.id.flEventListContainer, eventListFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void exit() {
        finish();
    }
}
