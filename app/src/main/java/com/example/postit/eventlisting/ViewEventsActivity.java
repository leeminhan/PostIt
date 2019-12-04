package com.example.postit.eventlisting;

import android.os.Bundle;
import com.android.volley.VolleyError;
import com.example.postit.models.Event;
import com.example.postit.requests.EventRequests;
import com.example.postit.utils.GenUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import com.example.postit.R;
import com.example.postit.utils.BottomNavMenu;
import com.google.gson.JsonObject;

public class ViewEventsActivity extends AppCompatActivity implements BottomNavMenu.NavActivity {
    public static final String CURRENT_FRAGMENT = "CURRENT_FRAGMENT_VIEW_EVENT_ACTIVITY";
    private BottomNavigationView navigation;
    private BottomNavMenu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_events);

        navigation = (BottomNavigationView) findViewById(R.id.navbar_view_events);
        menu = new BottomNavMenu(this, navigation);

        EventListFragment fragment = new EventListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.view_events_container, fragment, CURRENT_FRAGMENT)
                .addToBackStack(null)
                .commit();


//        EventRequests.getEventsBackend((Object obj) -> {
//            Event[] events = GenUtils.getArrayfromObject(obj);
//            fragment.setEvents(events);
//        }, (VolleyError err) -> {
//
//        });

    }

    @Override
    public void exit() {
        finish();
    }

}
