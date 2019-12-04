package com.example.postit.notifications;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Map;


public class Fragment1 extends Fragment {


    private static final String TAG = "Fragment1";

    LinearLayout linearLayout;
//
//    public class Tester2 extends AsyncTask<String, Void, String> {
//
//
//        public String doInBackground(String... urls){
//            RestTemplate restTemplate = new RestTemplate();
//            String fooResourceUrl = urls[0];
//            //String fooResourceUrl = "http://13.67.95.12:8080/home";
//            ResponseEntity<String> response
//                    = restTemplate.getForEntity(fooResourceUrl + "/", String.class);
//            //assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
//            String jsonString  = response.getBody();
//            ObjectMapper mapper = new ObjectMapper();
//            return jsonString;
//        }
//
//        protected void onPostExecute(String jsonString){
//            System.out.println("Successfully finished running HTTP");
//            System.out.println(jsonString);
//
//            //ArrayList<Map<String, String>> data = gson.fromJson(reader, type);
//
//            ArrayList<Map<String, Object>> data = new Gson().fromJson(
//                    jsonString, new TypeToken<ArrayList<Map<String, Object>>>() {}.getType()
//            );
//
//            try{
//                for (int i = 0; i <data.size(); i++) {
//                    String title = (String)data.get(i).get("title");
//                    String date = (String)data.get(i).get("date_activity");
//                    //Integer num_ppl = (Integer)data.get(i).get("people");
//                    //Integer max_ppl = (Integer)data.get(i).get("max_people");
//                    String imguri = (String) data.get(i).get("imageURI");
//                    String location = (String) data.get(i).get("location");
//                    String category = (String)data.get(i).get("category");
//                    String time = (String)data.get(i).get("time");
//                    String organizer = (String)data.get(i).get("organizer");
//
//
//                    CardView cardView = new CardView(getActivity());
//                    CardView.LayoutParams params = new CardView.LayoutParams(CardView.LayoutParams.MATCH_PARENT,
//                            CardView.LayoutParams.MATCH_PARENT);
//                    params.setMargins(convert(30), convert(10), convert(30), 0);
//
//
//                    cardView.setCardBackgroundColor(Color.parseColor("#91C2E2"));
//                    cardView.setLayoutParams(params);
//                    cardView.setRadius(0);
//
//                    //add textview for act name to card
//                    TextView textView = new TextView(getActivity());
//                    LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams((LinearLayout.LayoutParams.WRAP_CONTENT),
//                            LinearLayout.LayoutParams.WRAP_CONTENT);
//
//                    params2.setMargins(convert(20), convert(10), 0, convert(10));
//                    textView.setLayoutParams(params2);
//                    textView.setText(title);
//
//                    textView.setTextColor(Color.parseColor("#303F9F"));
//
//                    cardView.addView(textView);
//
//                    //add image to card
//
//
//                    ImageView imageView = new ImageView(getActivity());
//                    LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(convert(85),
//                            convert(90));
//                    params3.setMargins(convert(25), convert(40), 0, convert(10));
//                    imageView.setLayoutParams(params3);
//
//                    getImage task = new getImage();
//                    task.imageView = imageView;
//                    task.execute(imguri);
//
//                    //imageView.setImageResource(R.drawable.sutdmap);
//
//                    cardView.addView(imageView);
//
//                    /*//add reject button to card
//                    Button b1 = new Button(getActivity());
//
//                    LinearLayout.LayoutParams params4 = new LinearLayout.LayoutParams((LinearLayout.LayoutParams.WRAP_CONTENT),
//                            (LinearLayout.LayoutParams.WRAP_CONTENT));
//
//                    params4.setMargins(convert(270), convert(10), convert(20), convert(10));
//                    b1.setLayoutParams(params4);
//                    b1.setText("Reject");
//                    cardView.addView(b1);*/
//
//                    //add event description to card
//
//                    TextView eventdescp = new TextView(getActivity());
//                    LinearLayout.LayoutParams params5 = new LinearLayout.LayoutParams(convert(177),
//                            convert(70));
//                    params5.setMargins(convert(154), convert(58), 0, 10);
//                    eventdescp.setLayoutParams(params5);
//                    eventdescp.setText("Date :" + date + "\n" +
//                            "Location :"+location+ "\n" +
//                            "Organizer : Shin-chan\n" +
//                            "Category :" + category);
//                    cardView.addView(eventdescp);
//
//                    //add accept button to card
//
//                    /*Button b2 = new Button(getActivity());
//                    LinearLayout.LayoutParams params6 = new LinearLayout.LayoutParams((LinearLayout.LayoutParams.WRAP_CONTENT),
//                            (LinearLayout.LayoutParams.WRAP_CONTENT));
//
//                    params6.setMargins(convert(160), convert(10), convert(40), convert(10));
//                    b2.setLayoutParams(params6);
//                    b2.setText("Accept");
//                    cardView.addView(b2); */
//
//                    //add card to linearlayout
//                    linlay.addView(cardView);
//
//
//                }
//            }catch(Exception e){
//                System.out.println(e.toString());
//
//            }
//        }
//
//    }

    public int convert(int a) {
        return ((int) (a * (getActivity()).getResources().getDisplayMetrics().density));
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_notifications, container, false);

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

//        Tester2 t2 = new Tester2();
//        t2.execute("https://api.myjson.com/bins/1bemb0");

        String username = "mihir"; // TODO: Remove hardcoded username
        EventRequests.getUserEvents(username, (Object obj) -> {
            JsonObject res = (JsonObject) obj;
            String jsonString = res.toString();

            ArrayList<Map<String, Object>> data = new Gson().fromJson(
                    jsonString, new TypeToken<ArrayList<Map<String, Object>>>() {}.getType()
            );

            try{
                for (int i = 0; i <data.size(); i++) {
                    String title = (String)data.get(i).get("title");
                    String date = (String)data.get(i).get("date_activity");
                    //Integer num_ppl = (Integer)data.get(i).get("people");
                    //Integer max_ppl = (Integer)data.get(i).get("max_people");
                    String imguri = (String) data.get(i).get("imageURI");
                    String location = (String) data.get(i).get("location");
                    String category = (String)data.get(i).get("category");
                    String time = (String)data.get(i).get("time");
                    String organizer = (String)data.get(i).get("organizer");


                    CardView cardView = new CardView(getActivity());
                    CardView.LayoutParams params = new CardView.LayoutParams(CardView.LayoutParams.MATCH_PARENT,
                            CardView.LayoutParams.MATCH_PARENT);
                    params.setMargins(convert(30), convert(10), convert(30), 0);


                    cardView.setCardBackgroundColor(Color.parseColor("#91C2E2"));
                    cardView.setLayoutParams(params);
                    cardView.setRadius(0);

                    //add textview for act name to card
                    TextView textView = new TextView(getActivity());
                    LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams((LinearLayout.LayoutParams.WRAP_CONTENT),
                            LinearLayout.LayoutParams.WRAP_CONTENT);

                    params2.setMargins(convert(20), convert(10), 0, convert(10));
                    textView.setLayoutParams(params2);
                    textView.setText(title);

                    textView.setTextColor(Color.parseColor("#303F9F"));

                    cardView.addView(textView);

                    //add image to card


                    ImageView imageView = new ImageView(getActivity());
                    LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(convert(85),
                            convert(90));
                    params3.setMargins(convert(25), convert(40), 0, convert(10));
                    imageView.setLayoutParams(params3);

                    getImage task = new getImage();
                    task.imageView = imageView;
                    task.execute(imguri);

                    //imageView.setImageResource(R.drawable.sutdmap);

                    cardView.addView(imageView);

                    /*//add reject button to card
                    Button b1 = new Button(getActivity());

                    LinearLayout.LayoutParams params4 = new LinearLayout.LayoutParams((LinearLayout.LayoutParams.WRAP_CONTENT),
                            (LinearLayout.LayoutParams.WRAP_CONTENT));

                    params4.setMargins(convert(270), convert(10), convert(20), convert(10));
                    b1.setLayoutParams(params4);
                    b1.setText("Reject");
                    cardView.addView(b1);*/

                    //add event description to card

                    TextView eventdescp = new TextView(getActivity());
                    LinearLayout.LayoutParams params5 = new LinearLayout.LayoutParams(convert(177),
                            convert(70));
                    params5.setMargins(convert(154), convert(58), 0, 10);
                    eventdescp.setLayoutParams(params5);
                    eventdescp.setText("Date :" + date + "\n" +
                            "Location :"+location+ "\n" +
                            "Organizer : Shin-chan\n" +
                            "Category :" + category);
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


                }
            }catch(Exception e){
                System.out.println(e.toString());

            }

        }, (Exception err, Object obj) -> {
            Log.e(TAG, err.getMessage());
        });

        return view;

    }
}
