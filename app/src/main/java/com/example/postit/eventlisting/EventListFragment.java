package com.example.postit.eventlisting;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.postit.R;
import com.example.postit.models.Event;
import com.example.postit.models.FragmentTransition;
import com.example.postit.models.HoriztontalSpaceItemDecoration;

import java.text.ParseException;

public class EventListFragment extends Fragment implements FragmentTransition {
    private static final int frame = R.id.view_events_container;
    private RecyclerView sportsRecycler;
    private RecyclerView gamesRecycler;
    private RecyclerView eatingRecycler;
    private RecyclerView clubbingRecycler;
    private RecyclerView shoppingRecycler;
    private RecyclerView.Adapter sportsAdapter;
    private RecyclerView.Adapter gamesAdapter;
    private RecyclerView.Adapter eatingAdapter;
    private RecyclerView.Adapter clubbingAdapter;
    private RecyclerView.Adapter shoppingAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_event_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        final int LINEAR_SPACING = 48;

        super.onActivityCreated(savedInstanceState);

        Event event = new Event();

        try {
            event.setTitle("testing")
                    .setDate("12/12/2019")
                    .setTime("12:12")
                    .setBitmap(getContext(), R.drawable.sport);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        Event event2 = new Event();
        try {
            event2.setTitle("testing2")
                    .setDate("12/12/2219")
                    .setTime("12:12")
                    .setBitmap(getContext(), R.drawable.sport);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        Event event3 = new Event();
        try {
            event3.setTitle("testing2")
                    .setDate("12/12/2219")
                    .setTime("12:12")
                    .setBitmap(getContext(), R.drawable.sport);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        Event[] events = { event, event2, event3 };

        sportsRecycler = getView().findViewById(R.id.recycler_sports_list);
        gamesRecycler = getView().findViewById(R.id.recycler_games_list);
        eatingRecycler = getView().findViewById(R.id.recycler_eating_list);
        clubbingRecycler = getView().findViewById(R.id.recycler_clubbing_list);
        shoppingRecycler = getView().findViewById(R.id.recycler_shopping_list);
        sportsRecycler.addItemDecoration(new HoriztontalSpaceItemDecoration(LINEAR_SPACING));

        sportsAdapter = new EventListAdapter(events);
        gamesAdapter = new EventListAdapter(events);
        eatingAdapter = new EventListAdapter(events);
        clubbingAdapter = new EventListAdapter(events);
        shoppingAdapter = new EventListAdapter(events);

        sportsRecycler.setAdapter(sportsAdapter);
        gamesRecycler.setAdapter(gamesAdapter);
        eatingRecycler.setAdapter(eatingAdapter);
        clubbingRecycler.setAdapter(clubbingAdapter);
        shoppingRecycler.setAdapter(shoppingAdapter);
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
