package com.example.postit.myactivities;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.android.volley.VolleyError;
import com.example.postit.R;
import com.example.postit.requests.EventRequests;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Map;


public class Fragment2 extends Fragment {

    public int convert(int a) {
        return ((int) (a * (getActivity()).getResources().getDisplayMetrics().density));
    }

    private static final String TAG = "Fragment2";

    LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_myactivities, container, false);

        //View view = findViewById(R.layout.activity_myactivities);

        linearLayout = view.findViewById(R.id.titlelinear);

        /*
        TextView recommended = new TextView(getActivity());
        LinearLayout.LayoutParams params0 = new LinearLayout.LayoutParams((LinearLayout.LayoutParams.WRAP_CONTENT),
                LinearLayout.LayoutParams.WRAP_CONTENT);

        params0.setMargins(convert(20), 20, 0, 0);
        recommended.setLayoutParams(params0);
        recommended.setText("My Activities");

        recommended.setTextColor(Color.parseColor("#303F9F"));
        recommended.setTextSize(16);
        recommended.setTypeface(null, Typeface.BOLD);

        linearLayout.addView(recommended);

        */

        //Make the call, execute async task to get list of maps(pass the linearlayout to the async task, modify it inside)
//
//        Tester2 t2 = new Tester2();
//        t2.linlay = linearLayout;
//        t2.execute("https://api.myjson.com/bins/1bemb0");

        String username = "mihir"; // TODO: Remove hardcoded username
        EventRequests.getUserEvents(username, (Object obj) -> {
            try{
                JSONObject res = (JSONObject) obj;
            }catch(Exception e){
                System.out.println("JSON Conversion failed in fragment 2 starting");
            }
            JSONObject res = (JSONObject) obj;
            System.out.println("Successfull MyActivity Response of Jsonlist");
            String jsonString = res.toString();
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
                ArrayList ids = data_ids.get(username);
                for (int i = 0; i <ids.size(); i++) {

                    Double id = (Double)(ids.get(i));
                    System.out.println("This is the id");
                    System.out.println(id);
                    EventRequests.getEventDetails(id, (Object obj2) -> {
                        JSONArray res2 = (JSONArray) obj2;

                        try {
                            JSONObject data = res2.getJSONObject(0);

                            String title = (String) data.getString("title");
                            String date = (String) data.getString("date_activity");
                            //Integer num_ppl = (Integer)data.get(i).get("people");
                            //Integer max_ppl = (Integer)data.get(i).get("max_people");
                            String imguri = (String) data.getString("image_uri");
                            System.out.println("Image Link!!!");
                            System.out.println(imguri);
                            String location = (String) data.getString("venue");
                            String category = (String) data.getString("category");
                            //String time = (String)data.get("time");
                            String time = "18:45";
                            String organizer = (String) data.getString("creator");
                        /*
                        String jsonString2 = res2.to();
                        System.out.println(jsonString2);

                        ArrayList<Map<String, Object>> datalist = new Gson().fromJson(
                                jsonString2, new TypeToken<ArrayList<Map<String, Object>>>() {}.getType()
                        );

                        Map<String, Object> data=datalist.get(0);
                        String title = (String)data.get("title");
                        String date = (String)data.get("date_activity");
                        //Integer num_ppl = (Integer)data.get(i).get("people");
                        //Integer max_ppl = (Integer)data.get(i).get("max_people");
                        String imguri = (String) data.get("image_uri");
                        System.out.println("Image Link!!!");
                        System.out.println(imguri);
                        String location = (String) data.get("venue");
                        String category = (String)data.get("category");
                        //String time = (String)data.get("time");
                        String time = "18:45";
                        String organizer = (String)data.get("creator");

                         */


                            CardView cardView = new CardView(getActivity());
                            CardView.LayoutParams params = new CardView.LayoutParams(CardView.LayoutParams.MATCH_PARENT,
                                    convert(130));
                            params.setMargins(convert(10), convert(10), convert(10), 0);


                            cardView.setCardBackgroundColor(Color.parseColor("#91C2E2"));
                            cardView.setLayoutParams(params);
                            cardView.setRadius(15);

                            //add textview for act name to card
                            TextView textView = new TextView(getActivity());
                            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams((LinearLayout.LayoutParams.WRAP_CONTENT),
                                    LinearLayout.LayoutParams.WRAP_CONTENT);

                            params2.setMargins(convert(22), convert(11), 0, convert(10));
                            textView.setLayoutParams(params2);

                            textView.setText(title);
                            textView.setTextSize(24);
                            textView.setTypeface(null, Typeface.BOLD);
                            textView.setTextColor(Color.parseColor("#303F9F"));

                            cardView.addView(textView);

                            //add image to card
                            ImageView imageView = new ImageView(getActivity());
                            LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(convert(86),
                                    convert(82)); //left, top, right, bottom
                            params3.setMargins(convert(255), convert(19), 0, convert(10));
                            imageView.setLayoutParams(params3);

                            com.example.postit.notifications.getImage task = new com.example.postit.notifications.getImage();
                            task.imageView = imageView;
                            task.execute(imguri);

                            cardView.addView(imageView);

                            //add event description to card
                            TextView eventdescp = new TextView(getActivity());
                            LinearLayout.LayoutParams params5 = new LinearLayout.LayoutParams(convert(177),
                                    convert(70));
                            params5.setMargins(convert(22), convert(44), 0, 10);
                            eventdescp.setLayoutParams(params5);

                            String sourcedate = "<b>Date:</b> " + date + "<br/>";
                            String sourceorganizer = time + " || " + "<b>"+organizer+"</b> " + "<br/>";
                            String sourcelocation = "<b>Location:</b> " + location + "<br/>";
                            String sourcecategory = "<b>Category:</b> " + category;

                            eventdescp.setText(Html.fromHtml(sourcedate  +
                                    sourceorganizer +
                                    sourcelocation +
                                    sourcecategory));
                            cardView.addView(eventdescp);

                            //add accept button to card

                    /*Button b2 = new Button(getActivity());
                    LinearLayout.LayoutParams params6 = new LinearLayout.LayoutParams((LinearLayout.LayoutParams.WRAP_CONTENT),
                            (LinearLayout.LayoutParams.WRAP_CONTENT));

                    params6.setMargins(convert(160), convert(10), convert(40), convert(10));
                    b2.setLayoutParams(params6);
                    b2.setText("Accept");
                    cardView.addView(b2); */

                            //add card to linearlayout
                            linearLayout.addView(cardView);
                            Log.i(TAG, "Added notification");


                        }catch(Exception e1){
                            System.out.println("Failed to convert JSONObject");
                        }
                            }, (Exception err, Object objn) -> {
                        Log.e(TAG, err.getMessage());
                    });



                }
            }catch(Exception e){
                System.out.println(e.toString());

            }



            //end of the outer HTTP Call, keep end of inner one before this
        }, (Exception err, Object obj) -> {
            Log.e(TAG, err.getMessage());

        });


        return view;

    }
}

