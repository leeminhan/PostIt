package com.example.postit;

import androidx.annotation.Nullable;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.postit.models.Event;
import org.json.JSONObject;

public class ActivityRequests {
    private static final String backendBaseUrl = App.getContext().getString(R.string.backend_base_url);
    private static final String createEventEndpoint = App.getContext().getString(R.string.create_event_endpoint);

    public interface RequestSuccessListener {
        void onSuccess(Object obj);
    }

    public interface RequestErrorListener {
        void onError(Error err, @Nullable Object obj);
    }

    private static void getEventsBackend() {
        getEventsBackend(null);
    }

    private static void getEventsBackend(RequestSuccessListener successL) {
        getEventsBackend(successL, null);
    }

    private static void getEventsBackend(RequestSuccessListener successL, RequestErrorListener errorL) {
        final String url = String.format("%s%s", backendBaseUrl, createEventEndpoint);

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null, (JSONObject res) -> {

        }, (VolleyError err) -> {});
    }
}
