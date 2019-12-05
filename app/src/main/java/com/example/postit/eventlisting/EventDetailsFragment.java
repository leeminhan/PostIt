package com.example.postit.eventlisting;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.postit.R;
import com.example.postit.createevent.EventDetailRowView;
import com.example.postit.models.Event;
import com.example.postit.models.FragmentTransition;
import com.squareup.picasso.Picasso;


public class EventDetailsFragment extends Fragment implements FragmentTransition {

    // strings for logcat,for Log.i(TAG, msg)
    private static final int frame = R.id.view_events_container;
    private static final String COMMON_TAG = "CombinedLifeCycle";
    private static final String ACTIVITY_NAME = EventDetailsFragment.class.getSimpleName();
    private static final String TAG = COMMON_TAG;

    // initialise Views
    TextView tvEventDetailsTitle;
    ImageView ivEventDetailsImage;
    TextView tvEventDetailsCategory;
    TextView tvEventDetailsDate;
    TextView tvEventDetailsTime;
    TextView tvEventDetailsLocation;
    TextView tvEventDetailsDescription;
    TextView tvJoinTelegramGroupButton;
    private Event event;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_event_details, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        event = Event.getCurrentEvent();
        Log.d("successs", event.toMap().toString());
        setViewRefs();
        setEventDetails();

        tvJoinTelegramGroupButton = getView().findViewById(R.id.tvJoinTelegramGroupButton);
        tvJoinTelegramGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // hard code to one group TODO fetch from the database (string formatting the URL)
                Intent openTelegramIntent =  new Intent(Intent.ACTION_VIEW, Uri.parse(Event.getCurrentEvent().getTelegramGroup()));
                startActivity(openTelegramIntent);
            }
        });
        Log.i(TAG, ACTIVITY_NAME+" onCreate");
    }

    private void setViewRefs() {
        // bind the xml widget which this activity
        tvEventDetailsTitle = getView().findViewById(R.id.tvEventDetailsTitle);
        ivEventDetailsImage = getView().findViewById(R.id.ivEventDetailsImage);
        tvEventDetailsCategory = getView().findViewById(R.id.tvEventDetailsCategory);
        tvEventDetailsDate = getView().findViewById(R.id.tvEventDetailsDate);
        tvEventDetailsTime = getView().findViewById(R.id.tvEventDetailsTime);
        tvEventDetailsLocation = getView().findViewById(R.id.tvEventDetailsLocation);
        tvEventDetailsDescription = getView().findViewById(R.id.tvEventDetailsDescription);
    }

    private void setEventDetails() {
        tvEventDetailsTitle.setText(event.getTitle());
        tvEventDetailsDate.setText(event.getDateStr());
        tvEventDetailsCategory.setText(event.getCategoryStr(true));
        Picasso.get().load(event.getWebImgUrl()).into(ivEventDetailsImage);
        tvEventDetailsTime.setText(event.getTime());
        tvEventDetailsLocation.setText(event.getLocation());
        tvEventDetailsDescription.setText(event.getDescrip());
    }

    @Override
    public void changeFragment(Fragment fragment) {
        FragmentManager fManager = getActivity().getSupportFragmentManager();
        fManager.beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left,
                        R.anim.enter_from_left, R.anim.exit_to_right)
                .replace(frame, fragment)
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }
}
