package com.example.postit.myactivities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.example.postit.R;
import com.example.postit.utils.BottomNavMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MyActivitiesActivity extends AppCompatActivity implements BottomNavMenu.NavActivity {

    private static final String TAG = "NotificationActivity";

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
