package com.example.postit.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.widget.EditText;
import com.example.postit.createevent.EventDetailRowView;
import com.example.postit.utils.GenUtils;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Event {
    private String title;

    private String category;
    private Date date;
    private Time time;
    private String location;

    private int ppl;
    private Integer maxPpl;
    private String descrip;

    private Uri imagePath;
    private Bitmap bitmap;

    public Event() {
    }

    public Map toMap() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("category", getCategory());
        map.put("title", getTitle());
        map.put("date", getDate());
        map.put("time", getTime());
        map.put("location", getLocation());
        map.put("max_ppl", String.valueOf(getPpl()));
        map.put("descrip", getDescrip());

        return map;
    }

    public Event(String title) {
        this();
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Event setTitle(String title) {
        this.title = title;
        return this;
    }

    public Event setTitle(InputSetter s) {
        return setTitle(s.getInput());
    }

    public String getCategory() {
        return category;
    }

    public Event setCategory(String category) {
        this.category = category;
        return this;
    }

    public Event setCategory(InputSetter s) {
        return setCategory(s.getInput());
    }

    public String getDate() {
        return date.toString();
    }

    public Event setDate(Date date) {
        this.date = date;
        return this;
    }

    public Event setDate(String date) throws ParseException {
        return setDate(GenUtils.getValidDate(date, GenUtils.DateFormat.STANDARD));
    }

    public Event setDate(InputSetter s) throws InvalidInputException {
        try {
            return setDate(s.getInput());
        } catch (ParseException ex) {
            throw new InvalidInputException(s, null);
        }
    }

    public String getTime() {
        return time.toString();
    }

    public Event setTime(Time time) {
        this.time = time;
        return this;
    }

    public Event setTime(String time) throws ParseException {
        return setTime(GenUtils.getValidTime(time, GenUtils.TimeFormat.STANDARD));
    }

    public Event setTime(InputSetter s) throws InvalidInputException {
        try {
            return setTime(s.getInput());
        } catch (ParseException ex) {
            throw new InvalidInputException(s, null);
        }
    }

    public String getLocation() {
        return location;
    }

    public Event setLocation(String location) {
        this.location = location;
        return this;
    }

    public Event setLocation(InputSetter s) {
        return setLocation(s.getInput());
    }

    public int getPpl() {
        return ppl;
    }

    public Event setPpl(int ppl) {
        this.ppl = ppl;
        return this;
    }

    public Event setPpl(String ppl) throws NumberFormatException {
        return setPpl(Integer.parseInt(ppl));
    }

    public Event setPpl(InputSetter s) throws InvalidInputException {
        try {
            return setPpl(s.getInput());
        } catch (NumberFormatException ex) {
            throw new InvalidInputException(s, null);
        }
    }

    public int getMaxPpl() {
        return maxPpl;
    }

    public Event setMaxPpl(String maxPplStr) throws NumberFormatException {
        return setMaxPpl(Integer.parseInt(maxPplStr));
    }

    public Event setMaxPpl(InputSetter s) throws InvalidInputException {
        try {
            return setMaxPpl(s.getInput());
        } catch (NumberFormatException ex) {
            throw new InvalidInputException(s, null);
        }
    }

    public Event setMaxPpl(int maxPpl) {
        this.maxPpl = maxPpl == -1 ? null : maxPpl;
        return this;
    }

    public String getDescrip() {
        return descrip;
    }

    public Event setDescrip(String descrip) {
        this.descrip = descrip;
        return this;
    }

    public Event setDescrip(InputSetter s) {
        return setDescrip(s.getInput());
    }

    public Uri getImagePath() {
        return imagePath;
    }

    public Event setImagePath(Uri imagePath) {
        this.imagePath = imagePath;
        return this;
    }

    public Event setImagePath(String imagePathStr) throws NullPointerException {
        return setImagePath(Uri.parse(imagePathStr));
    }

    public Event setImagePath(InputSetter s) throws InvalidInputException {
        try {
            return setImagePath(s.getInput());
        } catch (NullPointerException ex) {
            throw new InvalidInputException(s, null);
        }
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Bitmap setBitmap(Context context, int id) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), id);
        this.bitmap = bitmap;
        return bitmap;
    }

    public static class ByViewSetter implements InputSetter {

        public static final String VIEW_KEY = "view_key";
        private EditText editText;

        public ByViewSetter(EditText v) {
            editText = v;
        }

        public ByViewSetter(EventDetailRowView v) {
            this(v.rowField);
        }

        @Override
        public String getInput() {
            return editText.getText().toString();
        }

        @Override
        public String getErrorCause() {
            return editText.getHint().toString();
        }

        @Override
        public Map<String, Object> getExtra() {
            Map<String, Object> extra = new HashMap<String, Object>();
            extra.put(VIEW_KEY, editText);

            return extra;
        }

        @Override
        public String getCauseKey() {
            return VIEW_KEY;
        }
    }

}