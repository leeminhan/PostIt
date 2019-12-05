package com.example.postit.eventlisting;

import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.postit.R;
import com.example.postit.models.Event;
import com.squareup.picasso.Picasso;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventListViewHolder> {

    private Event[] events;
    private EventListFragment fragment;

    public static class EventListViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView title;
        public TextView time;
        public CardView card;

        public EventListViewHolder(CardView v) {
            super(v);
            image = v.findViewById(R.id.event_card_image);
            title = v.findViewById(R.id.event_card_title);
            time = v.findViewById(R.id.event_card_time);
            card = v.findViewById(R.id.event_card_view);
        }
    }

    public EventListAdapter(EventListFragment fragment, Event[] events) {
        if (events.length == 0) {
            Event[] eventDummy = new Event[2];
            eventDummy[0] = new Event().setTitle("Dummy Event");
            eventDummy[1] = new Event().setTitle("Dummy Event");
            this.events = eventDummy;
            this.fragment = fragment;
            return;
        }
        this.events = events;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public EventListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView view = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_card_view, parent, false);
        return new EventListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventListViewHolder eventListViewHolder, int i) {

        if (events[i].getTitle().equals("Dummy Event")) {
            eventListViewHolder.title.setText("No events");
            eventListViewHolder.title.setGravity(Gravity.CENTER);
            eventListViewHolder.time.setVisibility(View.GONE);

            return;
        }

        if (events[i].getBitmap() != null) {
            eventListViewHolder.image.setImageBitmap(events[i].getBitmap());
        } else {
            Picasso.get().load(events[i].getWebImgUrl()).into(eventListViewHolder.image);
        }
        eventListViewHolder.title.setText(events[i].getTitle());
        String timeText = String.format("%s %s", events[i].getShortDate(), events[i].getShortTime());
        eventListViewHolder.time.setText(timeText);
        eventListViewHolder.card.setOnClickListener((View v) -> {
            // TODO: replace with Aobos create event function
            Event.setCurrentEvent(events[i]);
            fragment.changeFragment(new EventDetailsFragment());
        });
    }

    @Override
    public int getItemCount() {
        return events.length;
    }

}
