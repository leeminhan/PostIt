package com.example.postit.eventlisting;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.postit.R;
import com.example.postit.models.Event;
import com.example.postit.models.FragmentTransition;

public class EventListFragment extends Fragment implements FragmentTransition {
    private static final int frame = R.id.view_events_container;
    private final int LINEAR_SPACING = 36;
    private RecyclerView sportsRecycler;
    private RecyclerView gamesRecycler;
    private RecyclerView eatingRecycler;
    private RecyclerView musicRecycler;
    private RecyclerView shoppingRecycler;
    private RecyclerView recommendedRecycler;
    private RecyclerView.Adapter sportsAdapter;
    private RecyclerView.Adapter gamesAdapter;
    private RecyclerView.Adapter eatingAdapter;
    private RecyclerView.Adapter musicAdapter;
    private RecyclerView.Adapter shoppingAdapter;
    private RecyclerView.Adapter recommendedAdapter;
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

        super.onActivityCreated(savedInstanceState);

        sportsRecycler = getView().findViewById(R.id.recycler_sports_list);
        gamesRecycler = getView().findViewById(R.id.recycler_games_list);
        eatingRecycler = getView().findViewById(R.id.recycler_eating_list);
        musicRecycler = getView().findViewById(R.id.recycler_music_list);
        shoppingRecycler = getView().findViewById(R.id.recycler_shopping_list);
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

    public void setEvents(Event.Category category, Event[] events) {

        switch(category) {
            case SPORTS:
                sportsAdapter = new EventListAdapter(events);
                sportsRecycler.setAdapter(sportsAdapter);
            case GAMES:
                gamesAdapter = new EventListAdapter(events);
                gamesRecycler.setAdapter(gamesAdapter);
            case EATING:
                eatingAdapter = new EventListAdapter(events);
                eatingRecycler.setAdapter(eatingAdapter);
            case MUSIC:
                musicAdapter = new EventListAdapter(events);
                musicRecycler.setAdapter(musicAdapter);
            case SHOPPING:
                shoppingAdapter = new EventListAdapter(events);
                shoppingRecycler.setAdapter(shoppingAdapter);

        }
    }

    public void setRecommendedEvents(Event[] events) {

    }

}
