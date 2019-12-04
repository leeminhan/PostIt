package com.example.postit;

import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.postit.models.Event;
import com.example.postit.utils.ReqUtil;
import org.json.JSONObject;

public class ActivityRequests {
    private static final String backendBaseUrl = App.getContext().getString(R.string.backend_base_url);


    public interface RequestSuccessListener {
        void onSuccess(Object obj);
    }

    public interface RequestErrorListener {
        void onError(VolleyError err, @Nullable Object obj);
    }

    public static class Events {
        private static final String createEventEndpoint = App.getContext().getString(R.string.create_event_endpoint);
        private static final String getEventCategoryEdnpoint = App.getContext().getString(R.string.get_event_category_endpoint);

        public static void getEventsBackend() {
            getEventsBackend(null);
        }

        public static void getEventsBackend(RequestSuccessListener successL) {
            getEventsBackend(successL, null);
        }

        public static void getEventsBackend(RequestSuccessListener successL, RequestErrorListener errorL) {
            final String url = String.format("%s%s", backendBaseUrl, getEventCategoryEdnpoint);

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
    }


}
