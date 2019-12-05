package com.example.postit.requests;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.postit.App;
import com.example.postit.R;
import com.example.postit.models.Event;
import com.example.postit.utils.ReqUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class EventRequests extends ActivityRequests {
    private static final String createEventEndpoint = App.getContext().getString(R.string.create_event_endpoint);
    private static final String getEventCategoryEndpoint = App.getContext().getString(R.string.get_event_category_endpoint);
    private static final String getUserEventsEndpoint = App.getContext().getString(R.string.get_user_activities_endpoint);

    // TEMP root_link
    private static final String getUserEventsEndpoint2 =  App.getContext().getString(R.string.root_link) + App.getContext().getString(R.string.get_user_activities_endpoint2);
    private static final String getEventDetailEndpoint_base = App.getContext().getString(R.string.root_link) + App.getContext().getString(R.string.get_event_by_id_endpoint2);
    private static final String getRecommendationEndpoint = App.getContext().getString(R.string.root_link) + App.getContext().getString(R.string.get_user_recommendations_endpoint);
    private static final String getUserRemindersEndpoint = App.getContext().getString(R.string.root_link)+ App.getContext().getString(R.string.get_user_reminders);

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
        final String url = getUserEventsEndpoint2 + username;
        HashMap<String, String> map = new HashMap<>();
        map.put("username", username);
        final JSONObject json = new JSONObject(map);

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, json, successL::onSuccess,
                (VolleyError err) -> {
                    err.printStackTrace();
                    errorL.onError(err, null);
                });
        ReqUtil.getInstance(App.getContext()).addToRequestQueue(req);
    }

    public static void getUserReminders(String username, RequestSuccessListener successL, RequestErrorListener errorL) {
        // final String url = String.format("%s%s", backendBaseUrl, getUserEventsEndpoint);
        final String url = getUserRemindersEndpoint + username;
        System.out.println("The user reminder URL is:!@#$");
        System.out.println(url);
        //

        HashMap<String, String> map = new HashMap<>();
        map.put("username", username);
        final JSONObject json = new JSONObject(map);

        JSONArray json2 = new JSONArray();
        json2.put(json);

        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url, json2, successL::onSuccess,
                (VolleyError err) -> {
                    err.printStackTrace();
                    errorL.onError(err, null);
                });
        ReqUtil.getInstance(App.getContext()).addToRequestQueue(req);

    }

    public static void getEventDetails(Double id, RequestSuccessListener successL, RequestErrorListener errorL) throws JSONException

    {
        Integer id2 = id.intValue();
        // final String url = String.format("%s%s", backendBaseUrl, getUserEventsEndpoint);
        final String url = getEventDetailEndpoint_base + id2.toString();
        System.out.println("URL called is");
        System.out.println(url);
        HashMap<String, String> map = new HashMap<>();
        map.put("id", id2.toString());
        final JSONObject json = new JSONObject(map);

        JSONArray json2 = new JSONArray();
        json2.put(json);

        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url, json2, successL::onSuccess,
                (VolleyError err) -> {
                    err.printStackTrace();
                    errorL.onError(err, null);
                });
        ReqUtil.getInstance(App.getContext()).addToRequestQueue(req);
    }

    public static void getUserRecommendations(String username, RequestSuccessListener successL, RequestErrorListener errorL) {
        // final String url = String.format("%s%s", backendBaseUrl, getUserEventsEndpoint);
        final String url = getRecommendationEndpoint + username;
        System.out.println("recommendation url called is!!@@!!@@");
        System.out.println(url);
        HashMap<String, String> map = new HashMap<>();
        map.put("username", username);
        final JSONObject json = new JSONObject(map);

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, json, successL::onSuccess,
                (VolleyError err) -> {
                    err.printStackTrace();
                    errorL.onError(err, null);
                });
        ReqUtil.getInstance(App.getContext()).addToRequestQueue(req);
    }
}
