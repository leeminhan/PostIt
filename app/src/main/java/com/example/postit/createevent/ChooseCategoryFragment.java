package com.example.postit.createevent;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.postit.models.EventCategory;
import com.example.postit.models.EventTemplate;
import com.example.postit.models.FragmentTransition;
import com.example.postit.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class ChooseCategoryFragment extends Fragment implements FragmentTransition {

    private static final int frame = R.id.create_event_container;
    private ImageCardView sportsCard;
    private ImageCardView gamesCard;
    private ImageCardView eatingCard;
    private ImageCardView clubbingCard;
    private ImageCardView shoppingCard;
    private ImageCardView othersCard;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_category, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setViewRefs();
        sportsCard.setOnClickListener((v) -> goToEventFragment(EventCategory.SPORTS_EVENT));
        gamesCard.setOnClickListener((v) -> goToEventFragment(EventCategory.GAMES_EVENT));
        eatingCard.setOnClickListener((v) -> goToEventFragment(EventCategory.EATING_EVENT));
        clubbingCard.setOnClickListener((v) -> goToEventFragment(EventCategory.CLUBBING_EVENT));
        shoppingCard.setOnClickListener((v) -> goToEventFragment(EventCategory.SHOPPING_EVENT));
        othersCard.setOnClickListener((v) -> goToEventFragment(null));
    }

    private void goToEventFragment(@Nullable EventCategory category) {
        Fragment fragment = new NewEventFragment();
        Bundle args = new Bundle();
        args.putSerializable(EventTemplate.EVENT_CATEGORY, category);
        fragment.setArguments(args);
        changeFragment(fragment);
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

    public void setViewRefs() {
        sportsCard = getView().findViewById(R.id.sports_card);
        gamesCard = getView().findViewById(R.id.games_card);
        eatingCard = getView().findViewById(R.id.eating_card);
        clubbingCard = getView().findViewById(R.id.clubbing_card);
        shoppingCard = getView().findViewById(R.id.shopping_card);
        othersCard = getView().findViewById(R.id.others_card);

    }

}
