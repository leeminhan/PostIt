package com.example.postit.myactivities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.example.postit.R;

public class MyActivitiesActivity extends AppCompatActivity {

    private static final String TAG = "NotificationActivity";

    private SectionStatePagerAdapter mSectionStatePagerAdapter;
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionStatePagerAdapter = new SectionStatePagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);

        //setup the pager, taking the previous viewpager as an argument
        setupViewPager(mViewPager);
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
}
