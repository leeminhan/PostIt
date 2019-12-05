package com.example.postit.eventlisting;

import android.content.Context;
import android.net.Uri;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.postit.models.EventCategory;
import com.example.postit.utils.ReqUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;

public class EventDetailsFetcher {

    String eventCategory;
    String eventlocation;
    String eventDate;
    String eventDescription;
    String eventTime;
    String eventImage;

    // things are not displayed in user interfaces
    int eventUnqId;
    // max people for the event

    public EventDetailsFetcher(Context context, String s, jsonParseListener listener) {
        jsonParse(context, s, listener);
    }

    interface jsonParseListener {
        void onResponse(String eventDate, String eventCategory, String eventDescription,
                        String eventLocation, String eventTime, String eventImage);
    }

    private void jsonParse(Context context, String url, jsonParseListener listener){

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String category = response.getString("category");
                            String time = response.getString("time");
                            String date_activity = response.getString("date_activity");
                            String description = response.getString("description");
                            String location = response.getString("venue");
                            String image_uri = response.getString("image_uri");
                            int eventUnqId = response.getInt("unq_id");

                            eventCategory = category;
                            eventlocation = location;
                            eventTime = time;
                            eventDate = date_activity;
                            eventDescription = description;
                            eventImage = image_uri;
                            listener.onResponse(eventDate, eventCategory, eventDescription,
                                    eventlocation, eventTime, eventImage);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        ReqUtil.getInstance(context).getRequestQueue().add(request);
    }
}
