package com.example.postit.myactivities;

import android.graphics.Color;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.VolleyError;
import com.example.postit.R;
import com.example.postit.requests.EventRequests;
import com.example.postit.utils.BottomNavMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class MyActivitiesActivity extends AppCompatActivity implements BottomNavMenu.NavActivity {

    private static final String TAG = "MyActivitiesActivity";

    private SectionStatePagerAdapter mSectionStatePagerAdapter;
    private ViewPager mViewPager;
    private BottomNavigationView navigation;
    private BottomNavMenu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_myactivities);

        mSectionStatePagerAdapter = new SectionStatePagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.viewpager_container_myactivities);

        //setup the pager, taking the previous viewpager as an argument
        setupViewPager(mViewPager);
        navigation = findViewById(R.id.navbar_myactivities);
        menu = new BottomNavMenu(this, navigation);
    }

    private void setupViewPager(ViewPager viewpager){
        SectionStatePagerAdapter adapter = new SectionStatePagerAdapter(getSupportFragmentManager());
        //by default it will expand the first fragment that you
        //add to the adapter

        adapter.addFragment(new Fragment2(), "Fragment2");
        viewpager.setAdapter(adapter);
        System.out.println("Adaptor has been set!!!!");
        System.out.println("Is this an important piece of information?");

    }

    //pass fragment index to navigate to another fragment
    //this function will be called inside onclicklistener
    public void setViewPager(int fragmentNumber){
        mViewPager.setCurrentItem(fragmentNumber);
    }

    @Override
    public void exit() {

    }


}
