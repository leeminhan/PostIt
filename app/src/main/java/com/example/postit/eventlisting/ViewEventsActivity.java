package com.example.postit.eventlisting;

import android.os.Bundle;
import android.util.Log;
import com.android.volley.VolleyError;
import com.example.postit.models.Event;
import com.example.postit.requests.EventRequests;
import com.example.postit.utils.GenUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import com.example.postit.R;
import com.example.postit.utils.BottomNavMenu;

import java.text.ParseException;
import java.util.Map;

public class ViewEventsActivity extends AppCompatActivity implements BottomNavMenu.NavActivity {
    public static final String CURRENT_FRAGMENT = "CURRENT_FRAGMENT_VIEW_EVENT_ACTIVITY";
    private static final String TAG = "ViewEventsActivity";
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

        for (Map.Entry<Event.Category, String> entry : Event.categoryMapping.entrySet()) {
            String category = entry.getValue();
            Event.Category categoryEnum = entry.getKey();
            EventRequests.getEventsCategory(category, (Object events) -> onGetEventBackend(categoryEnum, fragment, (Event[]) events),
                    (Exception err, Object obj) -> {
                        Log.e(TAG, String.format("Failed to get events from backend, using defaults %s", category));
                        Event event = new Event();

                        try {
                            event.setTitle("testing")
                                    .setDate("12/12/2019")
                                    .setTime("12:12")
                                    .setWebImgUrl("https://static.toiimg.com/photo/64697339.cms");
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }

                        Event event2 = new Event();
                        try {
                            event2.setTitle("testing2")
                                    .setDate("12/12/2219")
                                    .setTime("12:12")
                                    .setWebImgUrl("https://images.theconversation.com/files/279768/original/file-20190617-118505-9ov3gw.jpg?ixlib=rb-1.1.0&q=45&auto=format&w=926&fit=clip");
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }

                        Event event3 = new Event();
                        try {
                            event3.setTitle("testing2")
                                    .setDate("12/12/2219")
                                    .setTime("12:12")
                                    .setWebImgUrl("http://danielfooddiary.com/wp-content/uploads/2019/02/mynonna2.jpg");
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }

                        Event[] events = {event, event2, event3};
//                        onGetEventBackend(categoryEnum, fragment, events);
                    });
        }

    }

    @Override
    public void exit() {
        finish();
    }

    private void onGetEventBackend(Event.Category category, EventListFragment fragment, Event[] res) {
        fragment.setEvents(category, res);
    }

}
