package com.example.postit.createevent;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.postit.R;
import com.example.postit.models.Event;
import com.example.postit.models.EventCategory;
import com.example.postit.models.EventTemplate;
import com.example.postit.utils.UIUtils;

import java.text.ParseException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class NewEventFragment extends Fragment {

    private OnCreateEventListener eventListener;
    private static final int frame = R.id.create_event_container;
    private EditText eventTitleField;
    private EventDetailRowView eventCategoryField;
    private EventDetailRowView eventDateField;
    private EventDetailRowView eventTimeField;
    private EventDetailRowView eventLocationField;
    private EventDetailRowView eventDescripField;
    private EventDetailRowView eventMaxPplField;
    private TextView createEventButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_event, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setViewRefs();
        setEventTemplate();
        createEventButton.setOnClickListener((View v) -> {
            Event event = gatherEventDetails();
            eventListener.onCreateEvent(event);
        });
    }

    public interface OnCreateEventListener {
        void onCreateEvent(Event event);
    }

    public void setOnCreateEventListener(OnCreateEventListener callback) {
        eventListener = callback;
    }

    private void setViewRefs() {
        eventTitleField = getView().findViewById(R.id.event_title_field);
        eventCategoryField = getView().findViewById(R.id.event_category_field);
        eventDateField = getView().findViewById(R.id.event_date_field);
        eventTimeField = getView().findViewById(R.id.event_time_field);
        eventLocationField = getView().findViewById(R.id.event_location_field);
        eventDescripField = getView().findViewById(R.id.event_descrip_field);
        eventMaxPplField = getView().findViewById(R.id.event_max_ppl_field);
        createEventButton = getView().findViewById(R.id.create_event_button);
    }

    private void setEventTemplate() {
        EventCategory category;
        try {
            category = (EventCategory) getArguments()
                    .getSerializable(EventTemplate.EVENT_CATEGORY) ;
        } catch (NullPointerException ex) {
            category = null;
        }

        EventTemplate template = EventTemplate.createEventTemplate(category);

        eventTitleField.setText(template.getDefaultTitle());
        eventCategoryField.setText(template.getDefaultCategory());
        eventDateField.setText(template.getDefaultDate());
        eventTimeField.setText(template.getDefaultTime());
        eventLocationField.setText(template.getDefaultLocation());
        eventDescripField.setText(template.getDefaultDescrip());
        if (template.getDefaultMaxPpl() >= 0) eventMaxPplField.setText(template.getDefaultMaxPpl());

        if (category != null) {
            UIUtils.disableEditText(eventCategoryField.rowField);
        }
    }

    private Event gatherEventDetails() {
        Event event = new Event();
        event.setCategory(eventCategoryField.getText().toString())
                .setTitle(eventTitleField.getText().toString())
                .setLocation(eventLocationField.getText().toString())
                .setDescrip(eventDescripField.getText().toString());

        try {
            event.setDate(eventDateField.getText().toString());
        } catch (Exception ex) {
            eventDateField.rowField.setTextColor(Color.RED);
            Toast.makeText(getContext(), getString(R.string.invalid_date), Toast.LENGTH_SHORT).show();
        }

        try {
            event.setTime(eventTimeField.getText().toString());

        } catch (Exception ex) {
            eventTimeField.rowField.setTextColor(Color.RED);
            Toast.makeText(getContext(), getString(R.string.invalid_time), Toast.LENGTH_SHORT).show();
        }

        return event;
    }

}
