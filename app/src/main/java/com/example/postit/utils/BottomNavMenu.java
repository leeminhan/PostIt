package com.example.postit.utils;

import android.app.Activity;
import android.content.Intent;
import com.example.postit.myactivities.MyActivitiesActivity;
import com.example.postit.notifications.NotificationActivity;
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
    public MenuItem[] menuItems;

    private HashMap<Integer, Class> activityMap = new HashMap<Integer, Class>() {{
        put(R.id.navigation_view_events, ViewEventsActivity.class);
        put(R.id.navigation_create_event, CreateEventActivity.class);
        put(R.id.navigation_my_activites, MyActivitiesActivity.class);
        put(R.id.navigation_notifications, NotificationActivity.class);
    }};

    public HashMap<Class, MenuItem> classMap;

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

        menuItems = new MenuItem[menu.size()];
        classMap = new HashMap<>();
        for (int i = 0; i < menu.size(); ++i) {
            MenuItem menuItem = menu.getItem(i);
            menuItems[i] = menuItem;
            menuItem.setOnMenuItemClickListener((item) -> {
                Activity callerAct = (Activity) caller;
                changeActivity(callerAct, menuItem);
                return true;
            });
        }
    }

    private void changeActivity(Activity caller, MenuItem menuItem) {
        if (!activityMap.containsKey(menuItem.getItemId())) return;

        Intent intent = new Intent(caller, activityMap.get(menuItem.getItemId()));
        caller.startActivity(intent);
        caller.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        ((NavActivity) caller).exit();
    }

    public void clickMenuItem(Activity activity, int itemId) {
        for (MenuItem item : menuItems) {
            if (itemId == item.getItemId()) {
                changeActivity(activity, item);
                return;
            }
        }
    }

}
