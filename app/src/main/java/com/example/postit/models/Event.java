package com.example.postit.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.widget.EditText;
import com.example.postit.R;
import com.example.postit.createevent.EventDetailRowView;
import com.example.postit.utils.GenUtils;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Event {
    public enum Category {
        SPORTS, SHOPPING, GAMES, FOOD, MUSIC, OTHERS
    }

    private HashMap<Category, String> categoryMapping = new HashMap<Category, String>() {{
       put(Category.SPORTS, "sports");
       put(Category.SHOPPING, "shopping");
       put(Category.GAMES, "games");
       put(Category.FOOD, "food");
       put(Category.MUSIC, "music");
       put(Category.OTHERS, "others");
    }};

    private String title;
    private String id;
    private String creator;

    private Category category;
    private Date date;
    private Time time;
    private String location;
    private String telegramGroup;

    private int ppl;
    private Integer maxPpl;
    private String descrip;

    private int drawableId;
    private Uri imagePath;
    private Bitmap bitmap;

    private Uri webImgUrl;

    public Event() {
        this.id = GenUtils.genUuid();
    }

    public Map toMap() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("creator", getCreator());
        map.put("category", getCategoryStr());
        map.put("title", getTitle());
        map.put("date_activity", getDate());
        map.put("time", getTime());
        map.put("venue", getLocation());
        map.put("ppl", getPplStr());
        map.put("max_ppl", String.valueOf(getPpl()));
        map.put("description", getDescrip());
        map.put("image_uri", getWebImgUrl().toString());
        map.put("telegram_group", getTelegramGroup());
        map.put("unq_id", getId());
        Log.i("MYMAP", map.toString());
        return map;
    }

    public Event(String title) {
        this();
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public Event setId(String id) {
        this.id = id;
        return this;
    }

    public Event setCreator(String creator) {
        this.creator = creator;
        return this;
    }

    public String getCreator() {
        return creator;
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

    public String getCategoryStr() {
        return categoryMapping.get(category);
    }

    public Event setCategory(String category) {
        for (Map.Entry<Category, String> entry: categoryMapping.entrySet()) {
            if (category.toLowerCase().equals(entry.getValue())) {
                this.category = entry.getKey();
                return this;
            }
        }
        return this;
    }

    public Event setCategory(Category category) {
        this.category = category;
        return this;
    }

    public Event setCategory(InputSetter s) {
        return setCategory(s.getInput());
    }

    public String getDate() {
        return date.toString();
    }

    public String getShortDate() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM");
            return dateFormat.format(dateFormat.parse(date.toString()));
        } catch (ParseException ex) {
            return "09/11";
        }
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

    public String getShortTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        return timeFormat.format(time);

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

    public String getPplStr() {
        return String.valueOf(getPpl());
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
            if (s.getInput() == null || s.equals("")) return setMaxPpl(-1);
            return setMaxPpl(s.getInput());
        } catch (NumberFormatException ex) {
            throw new InvalidInputException(s, null);
        }
    }

    public Event setMaxPpl(int maxPpl) {
        this.maxPpl = maxPpl == -1 ? null : maxPpl;
        return this;
    }

    public String getTelegramGroup() {
        return telegramGroup;
    }

    public Event setTelegramGroup(String telegramGroup) {
        this.telegramGroup = telegramGroup;
        return this;
    }

    public Event setTelegramGroup(InputSetter s) {
        return setTelegramGroup(s.getInput());
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

    public Event setImagePath(Context context, int drawableId) {
        setDrawableId(drawableId);
        setBitmap(BitmapFactory.decodeResource(context.getResources(), drawableId));
        return setImagePath(GenUtils.getUriToDrawable(context, drawableId));
    }

    public Event setImagePath(InputSetter s) throws InvalidInputException {
        try {
            return setImagePath(s.getInput());
        } catch (NullPointerException ex) {
            throw new InvalidInputException(s, null);
        }
    }

    public Uri getWebImgUrl() {
        return webImgUrl;
    }

    public Event setWebImgUrl(Uri webImgUrl) {
        this.webImgUrl = webImgUrl;
        return this;
    }

    public Event setWebImgUrl(String webImgUrlStr) {
        return setWebImgUrl(Uri.parse(webImgUrlStr));
    }


    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Bitmap setBitmap(Context context, int id) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), id);
        return setBitmap(bitmap);
    }

    public Bitmap setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        return this.bitmap;
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