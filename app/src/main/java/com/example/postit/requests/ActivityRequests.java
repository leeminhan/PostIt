package com.example.postit.requests;

import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.postit.App;
import com.example.postit.R;
import com.example.postit.models.Event;
import com.example.postit.utils.ReqUtil;
import org.json.JSONObject;

public class ActivityRequests {
    protected static final String backendBaseUrl = App.getContext().getString(R.string.backend_base_url);
    protected static final String backendBaseUrl3 = App.getContext().getString(R.string.backend_base_url3);

    public interface RequestSuccessListener {
        void onSuccess(Object obj);
    }

    public interface RequestErrorListener {
        void onError(Exception ex, @Nullable Object obj);
    }
 }