package com.example.postit.eventlisting;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.postit.R;
import com.example.postit.models.Event;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventListViewHolder> {

    private Event[] events;

    public static class EventListViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView title;
        public TextView time;

        public EventListViewHolder(CardView v) {
            super(v);
            image = v.findViewById(R.id.event_card_image);
            title = v.findViewById(R.id.event_card_title);
            time = v.findViewById(R.id.event_card_time);
        }
    }

    public EventListAdapter(Event[] events) {
        this.events = events;
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
        eventListViewHolder.image.setImageBitmap(events[i].getBitmap());
        eventListViewHolder.title.setText(events[i].getTitle());
        String timeText = String.format("%s %s", events[i].getShortDate(), events[i].getTime());
        eventListViewHolder.time.setText(timeText);
    }

    @Override
    public int getItemCount() {
        return events.length;
    }

}
