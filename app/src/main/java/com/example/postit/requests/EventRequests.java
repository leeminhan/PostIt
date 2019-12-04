package com.example.postit.requests;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.postit.App;
import com.example.postit.R;
import com.example.postit.models.Event;
import com.example.postit.utils.GenUtils;
import com.example.postit.utils.ReqUtil;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.HashMap;

public class EventRequests extends ActivityRequests {
    private static final String createEventEndpoint = App.getContext().getString(R.string.create_event_endpoint);
    private static final String getEventCategoryEndpoint = App.getContext().getString(R.string.get_event_category_endpoint);
    private static final String getUserEventsEndpoint = App.getContext().getString(R.string.get_user_activities_endpoint);

    // TEMP
    private static final String getUserEventsEndpoint2 = App.getContext().getString(R.string.get_user_activities_endpoint2);


    public static void getEventsBackend() {
        getEventsBackend(null);
    }

    public static void getEventsBackend(RequestSuccessListener successL) {
        getEventsBackend(successL, null);
    }

    public static void getEventsBackend(RequestSuccessListener successL, RequestErrorListener errorL) {
        final String url = String.format("%s%s", backendBaseUrl, getEventCategoryEndpoint);

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null, successL::onSuccess,
                (VolleyError err) -> {
                    err.printStackTrace();
                    errorL.onError(err, null);
                });
        ReqUtil.getInstance(App.getContext()).addToRequestQueue(req);
    }

    public static void createEvent(Event event) {
        createEvent(event, null);
    }

    public static void createEvent(Event event, RequestSuccessListener successL) {
        createEvent(event, successL, null);
    }

    public static void createEvent(Event event, RequestSuccessListener successL, RequestErrorListener errorL) {
        // TODO: Replace Url with actual backend API Url
        final String url = String.format("%s%s", backendBaseUrl, createEventEndpoint);
        final JSONObject json = new JSONObject(event.toMap());

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, json, successL::onSuccess,
                (VolleyError err) -> {
                    err.printStackTrace();
                    errorL.onError(err, null);
                });
        ReqUtil.getInstance(App.getContext()).addToRequestQueue(req);
    }

    public static void getUserEvents(String username, RequestSuccessListener successL, RequestErrorListener errorL) {
        // final String url = String.format("%s%s", backendBaseUrl, getUserEventsEndpoint);
        final String url = getUserEventsEndpoint2;
        HashMap<String, String> map = new HashMap<>();
        map.put("username", username);
        final JSONObject json = new JSONObject(map);

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, json, (JSONObject obj) -> {
            try {
                successL.onSuccess(processGetUserEvents(obj));
            } catch (Exception ex) {
                errorL.onError(ex, null);
            }
        }, (VolleyError err) -> {
            err.printStackTrace();
            errorL.onError(err, null);
        });
        ReqUtil.getInstance(App.getContext()).addToRequestQueue(req);
    }

    private static Event[] processGetUserEvents(JSONObject res) {
        JSONObject[] objArr = GenUtils.getArrayFromObject(res);
        Event[] events = new Event[objArr.length];
        try {
            for (int i = 0; i < objArr.length; ++i) {
                JSONObject obj = objArr[i];
                HashMap<String, Object> map = GenUtils.getMapFromObject(obj);
                Event event = new Event().setId((String) map.get("unq_id"))
                        .setCategory((String) map.get("category"))
                        .setDate((String) map.get("date"))
                        .setCreator((String) map.get("creator"))
                        .setLocation((String) map.get("location"))
                        .setPpl(Integer.parseInt((String) map.get("ppl")))
                        .setImagePath((String) map.get("image_uri"))
                        .setDescrip((String) map.get("description"))
                        .setMaxPpl(Integer.parseInt((String) map.get("max_ppl")));
                events[i] = event;
            }
        } catch (ParseException | NullPointerException ex) {
            ex.printStackTrace();
        }
        return events;

    }
}
