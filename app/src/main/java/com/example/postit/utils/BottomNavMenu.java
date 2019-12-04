package com.example.postit.utils;

import android.app.Activity;
import android.content.Intent;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.view.Menu;
import android.view.MenuItem;

import com.example.postit.R;
import com.example.postit.createevent.CreateEventActivity;
import com.example.postit.eventlisting.ViewEventsActivity;

import java.util.HashMap;
import java.util.Map;

public class BottomNavMenu {

    public interface NavActivity {
        void exit();
    }

    private NavActivity caller;
    private Menu menu;

    private HashMap<Integer, Class> activityMap = new HashMap<Integer, Class>() {{
        put(R.id.navigation_view_events, ViewEventsActivity.class);
        put(R.id.navigation_create_event, CreateEventActivity.class);
    }};

    public BottomNavMenu(NavActivity caller, BottomNavigationView v) {
        menu = v.getMenu();
        this.caller = caller;
        initMenu(v);
    }

    private void initMenu(BottomNavigationView v) {

        for (Map.Entry<Integer, Class> entry : activityMap.entrySet()) {
            if (entry.getValue() == caller.getClass()) {
                v.setSelectedItemId(entry.getKey());
                break;
            }
        }

        for (int i = 0; i < menu.size(); ++i) {
            MenuItem menuItem = menu.getItem(i);

            menuItem.setOnMenuItemClickListener((item) -> {
                Activity callerAct = (Activity) caller;
                if (!activityMap.containsKey(menuItem.getItemId())) return true;

                Intent intent = new Intent(callerAct, activityMap.get(menuItem.getItemId()));
                callerAct.startActivity(intent);
                callerAct.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                caller.exit();

                return true;
            });
        }
    }

}
