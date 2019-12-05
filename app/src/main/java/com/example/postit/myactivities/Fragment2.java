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
    static Integer count;


    public int convert(int a) {
        return ((int) (a * (getActivity()).getResources().getDisplayMetrics().density));
    }

    private static final String TAG = "Fragment2";

    LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        if(count==null){
            count=1;
        }else{
            count+=1;
            System.out.println("The second fragment has been increased again");
            System.out.println(count);
        }
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

                            String year = date.substring(0,4);
                            String month = date.substring(4,6);
                            String day = date.substring(6,8);
                            String time = date.substring(8,10) + ":" + date.substring(10,12);

                            date = day+"."+month+"."+year;

                            //Integer num_ppl = (Integer)data.get(i).get("people");
                            //Integer max_ppl = (Integer)data.get(i).get("max_people");

                            String imguri="https://i.ibb.co/y85SFTK/micro1.jpg";

                            //String imguri = (String) data.getString("image_uri");
                            System.out.println("Image Link!!!");
                            System.out.println(imguri);
                            String location = (String) data.getString("venue");
                            String category = (String) data.getString("category");
                            //String time = (String)data.get("time");

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


                            cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                            //cardView.setCardBackgroundColor(getResources().getColor(R.color.LoginRegistrationPreferences));

                            cardView.setLayoutParams(params);
                            cardView.setRadius(15);

                            //add textview for act name to card
                            TextView textView = new TextView(getActivity());
                            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams((LinearLayout.LayoutParams.WRAP_CONTENT),
                                    LinearLayout.LayoutParams.WRAP_CONTENT);

                            params2.setMargins(convert(19), convert(8), 0, convert(10));
                            textView.setLayoutParams(params2);

                            textView.setText(title);
                            textView.setTextSize(25);
                            textView.setTypeface(null, Typeface.BOLD);
                            textView.setTextColor(Color.parseColor("#303F9F"));

                            cardView.addView(textView);

                            //add image to card



                            ImageView imageView = new ImageView(getActivity());
                            LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(convert(120),
                                    convert(75)); //left, top, right, bottom

                            params3.setMargins(convert(18), convert(44), 0, convert(10));

                            imageView.setLayoutParams(params3);

                            com.example.postit.notifications.getImage task = new com.example.postit.notifications.getImage();
                            task.imageView = imageView;
                            task.execute(imguri);

                            cardView.addView(imageView);

                            //add event description to card
                            TextView eventdescp = new TextView(getActivity());
                            LinearLayout.LayoutParams params5 = new LinearLayout.LayoutParams(convert(177),
                                    convert(70));
                            params5.setMargins(convert(235), convert(34), 0, 10);
                            eventdescp.setLayoutParams(params5);

                            String sourcedate = "<b><strong>Date:</strong></b> " + date + "<br/>";
                            String sourceorganizer = time + " || " + "<b><strong>"+organizer+"</strong></b> " + "<br/>";
                            String sourcelocation = "<b><strong>Location:</strong></b> " + location + "<br/>";
                            String sourcecategory = "<b><strong>Category:</strong></b> " + category;

                            eventdescp.setText(Html.fromHtml(sourcedate  +
                                    sourceorganizer +
                                    sourcelocation +
                                    sourcecategory));

                            eventdescp.setTextColor(Color.parseColor("#303F9F"));

                            eventdescp.setTextSize(15);
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
                     //   Log.e(TAG, err.getMessage());
                    });



                }
            }catch(Exception e){
              //  System.out.println(e.toString());

            }



            //end of the outer HTTP Call, keep end of inner one before this
        }, (Exception err, Object obj) -> {
           // Log.e(TAG, err.getMessage());

        });


        return view;

    }
}

