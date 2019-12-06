package com.example.postit.eventlisting;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import com.android.volley.VolleyError;
import com.example.postit.User;
import com.example.postit.models.Event;
import com.example.postit.requests.EventRequests;
import com.example.postit.utils.GenUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import com.example.postit.R;
import com.example.postit.utils.BottomNavMenu;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

public class ViewEventsActivity extends AppCompatActivity implements BottomNavMenu.NavActivity {
    public static final String CURRENT_FRAGMENT = "CURRENT_FRAGMENT_VIEW_EVENT_ACTIVITY";
    private static final String TAG = "ViewEventsActivity";
    private BottomNavigationView navigation;
    private BottomNavMenu menu;

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
            return;
        }
        super.onBackPressed();
    }

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
                        onGetEventBackend(categoryEnum, fragment, events);
                    });
        }

        getRecommendations(fragment);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void exit() {
        finish();
    }

    private void onGetEventBackend(Event.Category category, EventListFragment fragment, Event[] res) {
        fragment.setEvents(category, res);
    }

    private void getRecommendations(EventListFragment fragment) {
        EventRequests.getUserRecommendations("mihir", (Object obj) -> {
            JSONObject res = (JSONObject) obj;
            String jsonString = res.toString();
            System.out.println("Success Recommendation response!");
            System.out.println(jsonString);

            Map<String, ArrayList> data_ids = new Gson().fromJson(
                    jsonString, new TypeToken<Map<String, ArrayList>>() {}.getType()
            );

            //ArrayList<Map<String, Object>> data = new Gson().fromJson(
            //                    jsonString, new TypeToken<Map<String, ArrayList>>() {}.getType()
            //            );
            //
            //
            //

            try{
                ArrayList ids = data_ids.get("ids_list");
                ArrayList<Event> events = new ArrayList<>();
                for (int i = 0; i <ids.size(); i++) {


                    Double id = (Double)ids.get(i);
                    Log.d("test6", "Test");
                    EventRequests.getEventDetails(id, (Object obj2) -> {

                        Log.d("test10",  "aadasf");


                        JSONArray res3 = (JSONArray) obj2;

                        Log.d("test7", res3.toString());
                        try {
                            JSONObject data = res3.getJSONObject(0);

                            String title = (String) data.getString("title");
                            String date = (String) data.getString("date_activity");

                            String year = date.substring(0,4);
                            String month = date.substring(4,6);
                            String day = date.substring(6,8);
                            String time = date.substring(8,10) + ":" + date.substring(10,12);

                            date = day+"."+month+"."+year;

                            //201912031600

                            //Integer num_ppl = (Integer)data.get(i).get("people");
                            //Integer max_ppl = (Integer)data.get(i).get("max_people");
                            String imguri = (String) data.getString("image_uri");
                            if (imguri == null || imguri.equals("null")) {
                                imguri="https://i.ibb.co/y85SFTK/micro1.jpg";
                            }
                            Log.d("test3", "Test");
                            String location = (String) data.getString("venue");
                            String category = (String) data.getString("category");
                            //String time = (String)data.get("time");
                            String organizer = (String) data.getString("creator");
                            Event event = new Event().setId((int)(double) data.getDouble("unq_id"))
                                    .setTitle((String) data.getString("title"))
                                    .setCategory((String) data.getString("category"))
                                    .setDate((String) data.getString("date_activity"))
                                    .setCreator((String) data.getString("creator"))
                                    .setLocation((String) data.getString("venue"))
                                    .setPpl((int)(double) data.getDouble("ppl"))
                                    .setTelegramGroup((String) data.getString("telegram_group"))
                                    .setDescrip((String) data.getString("description"))
                                    .setMaxPpl((int)(double) data.getDouble("max_ppl"))
                                    .setWebImgUrl((String) imguri);
                            Log.d("check1", "asdf");
                            events.add(event);

                        } catch (Exception e1) {
                            System.out.println("Failed to convert JSONObject");
                        }


                    }, (Exception err, Object objn) -> {
                        //Log.e(TAG, err.getMessage());
                    });

                }
                for (Event event : events) {
                    if (event == null) {
                        events.remove(event);
                    }

                }
                Event[] events2 = new Event[events.size()];
                for (int j = 0; j < events.size(); ++j) {
                    events2[j] = events.get(j);
                    Log.d("events2", events2[j].toString());
                }
//            fragment.setRecommendedEvents(events2);

            } catch (JsonParseException | JSONException ex) {
            }
        },(Exception err, Object obj) -> {
            // Log.e(TAG, err.getMessage());
        });
    }

}
