package com.example.postit.createevent;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
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
import com.example.postit.models.InvalidInputException;
import com.example.postit.utils.GenUtils;
import com.example.postit.utils.UIUtils;

import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class NewEventFragment extends Fragment {

    private final static String TAG = "NewEventFragment";

    private OnCreateEventListener eventListener;
    private ImageView imageView;
    private Bitmap bitmap;
    private EditText eventTitleField;
    private EventDetailRowView eventCategoryField;
    private EventDetailRowView eventDateField;
    private EventDetailRowView eventTimeField;
    private EventDetailRowView eventLocationField;
    private EventDetailRowView eventDescripField;
    private EventDetailRowView eventMaxPplField;
    private TextView createEventButton;
    private Uri imagePath;

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
            Event event;
            try {
                event = gatherEventDetails();
            } catch (InvalidInputException ex) {
                TextView view = (TextView) ex.getCauseObject();
                view.setTextColor(Color.RED);
                Toast.makeText(getContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "Invalid Input");
                return;
            }
            eventListener.onCreateEvent(event);
        });

        imageView.setOnClickListener((View v) -> {
            GenUtils.chooseImage(getActivity());
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
        imageView = getView().findViewById(R.id.new_event_image);
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

        imagePath = template.getImagePath();

        if (template.getDefaultMaxPpl() >= 0) eventMaxPplField.setText(template.getDefaultMaxPpl());

        if (category != null) {
            UIUtils.disableEditText(eventCategoryField.rowField);
        }
    }

    private Event gatherEventDetails() throws InvalidInputException {
        Event event = new Event();
        event.setCategory(new Event.ByViewSetter(eventCategoryField))
                .setTitle(new Event.ByViewSetter(eventTitleField))
                .setLocation(new Event.ByViewSetter(eventLocationField))
                .setDate(new Event.ByViewSetter(eventDateField))
                .setTime(new Event.ByViewSetter(eventTimeField))
                .setDescrip(new Event.ByViewSetter(eventDescripField))
                .setMaxPpl(new Event.ByViewSetter(eventMaxPplField))
                .setImagePath(imagePath);

        return event;
    }


    public void setBitmap(Uri filePath) {
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), filePath);
            imageView.setImageBitmap(bitmap);
            imagePath = filePath;
        } catch (IOException ex) {
            Log.e(TAG, ex.getMessage());
        }
    }

}
