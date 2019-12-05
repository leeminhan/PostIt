package com.example.postit.models;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.Nullable;
import android.util.Log;
import com.example.postit.App;
import com.example.postit.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EventTemplate extends Event {

    private static final String TAG = "Event Template";
    public static final String EVENT_CATEGORY = "Event Category";
    private String defaultTitle;

    private String defaultCategory;
    private String defaultDateStr;
    private String defaultTimeStr;
    private String defaultLocation;

    private int defaultMaxPpl;
    private String defaultDescrip;

    private Uri defaultImagePath;
    private String defaultTelegramGroup;

    public static EventTemplate createEventTemplate(@Nullable EventCategory category) {
        if (category == null) {
            return emptyEvent();
        }

        switch (category) {
            case SPORTS_EVENT:
                return sportsEvent();
            case GAMES_EVENT:
                return gamesEvent();
            case EATING_EVENT:
                return eatingEvent();
            case MUSIC_EVENT:
                return musicEvent();
            case SHOPPING_EVENT:
                return shoppingEvent();
            default:
                return emptyEvent();
        }
    }

    public EventTemplate(Context context,
                         String defaultCategory,
                         String defaultTitle,
                         String defaultDateStr,
                         String defaultTimeStr,
                         String defaultLocation,
                         String defaultMaxPpl,
                         String defaultDescrip,
                         String defaultTelegramGroup,
                         int defaultDrawableId) {

        setDefaultCategory(defaultCategory);
        setDefaultTitle(defaultTitle);
        setDefaultDate(defaultDateStr);
        setDefaultTime(defaultTimeStr);
        setDefaultLocation(defaultLocation);
        setDefaultMaxPpl(defaultMaxPpl);
        setDefaultDescrip(defaultDescrip);
        setDefaultImagePath(context, defaultDrawableId);
        setDefaultTelegramGroup(defaultTelegramGroup);
    }

    public static EventTemplate emptyEvent() {
        final Context context = App.getContext();
        return new EventTemplate(context,
                "",
                "",
                getCurrentDateStr(),
                getCurrentTimeStr(),
                "",
                "",
                "",
                context.getString(R.string.event_eating_default_telegram),
                R.drawable.default_event_picture);
    }

    public static EventTemplate sportsEvent() {
        final Context context = App.getContext();
        return new EventTemplate(context,
                                context.getString(R.string.event_sports_default_category),
                                context.getString(R.string.event_sports_default_title),
                                getCurrentDateStr(),
                                getCurrentTimeStr(),
                                context.getString(R.string.event_sports_default_location),
                                "",
                                "",
                                context.getString(R.string.event_sports_default_telegram),
                                R.drawable.default_event_picture);
    }

    public static EventTemplate gamesEvent() {
        final Context context = App.getContext();
        return new EventTemplate(context,
                context.getString(R.string.event_games_default_category),
                context.getString(R.string.event_games_default_title),
                getCurrentDateStr(),
                getCurrentTimeStr(),
                context.getString(R.string.event_games_default_location),
                "",
                "",
                context.getString(R.string.event_games_default_telegram),
                R.drawable.default_event_picture);
    }

    public static EventTemplate eatingEvent() {
        final Context context = App.getContext();
        return new EventTemplate(context,
                context.getString(R.string.event_eating_default_category),
                context.getString(R.string.event_eating_default_title),
                getCurrentDateStr(),
                getCurrentTimeStr(),
                context.getString(R.string.event_eating_default_location),
                "",
                context.getString(R.string.event_eating_default_descrip),
                context.getString(R.string.event_eating_default_telegram),
                R.drawable.default_event_picture);
    }

    public static EventTemplate musicEvent() {
        final Context context = App.getContext();
        return new EventTemplate(context,
                context.getString(R.string.event_music_default_category),
                context.getString(R.string.event_music_default_title),
                getCurrentDateStr(),
                getCurrentTimeStr(),
                context.getString(R.string.event_music_default_location),
                "",
                context.getString(R.string.event_music_default_descrip),
                context.getString(R.string.event_music_default_telegram),
                R.drawable.default_event_picture);
    }

    public static EventTemplate shoppingEvent() {
        final Context context = App.getContext();
        return new EventTemplate(context,
                context.getString(R.string.event_shopping_default_category),
                context.getString(R.string.event_shopping_default_title),
                getCurrentDateStr(),
                getCurrentTimeStr(),
                context.getString(R.string.event_shopping_default_location),
                "",
                "",
                context.getString(R.string.event_shopping_default_telegram),
                R.drawable.default_event_picture);
    }

    private static String getCurrentDateStr() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        Date c = calendar.getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(c);
    }

    private static String getCurrentTimeStr() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        Date c = calendar.getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        return df.format(c);
    }

    public String getDefaultCategory() {
        if (defaultCategory.length() < 2) return defaultCategory;
        return defaultCategory.substring(0, 1).toUpperCase() + defaultCategory.substring(1);
    }

    public String getDefaultTitle() {
        return defaultTitle;
    }

    public String getDefaultDate() {
        return defaultDateStr;
    }

    public String getDefaultTime() {
        return defaultTimeStr;
    }

    public String getDefaultLocation() {
        return defaultLocation;
    }

    public int getDefaultMaxPpl() {
        return defaultMaxPpl;
    }

    public String getDefaultDescrip() {
        return defaultDescrip;
    }

    public Uri getDefaultImagePath() {
        return defaultImagePath;
    }

    public String getDefaultTelegramGroup() {
        return defaultTelegramGroup;
    }

    private void setDefaultCategory(String category) {
        this.defaultCategory = category;
    }

    private void setDefaultTitle(String title) {
        this.defaultTitle = title;
    }

    private void setDefaultDate(String dateStr) {
        this.defaultDateStr = dateStr;
    }

    private void setDefaultTime(String timeStr) {
        this.defaultTimeStr = timeStr;
    }

    private void setDefaultLocation(String location) {
        this.defaultLocation = location;
    }

    private void setDefaultTelegramGroup(String telegramGroup) {
        this.defaultTelegramGroup = telegramGroup;
    }

    private void setDefaultMaxPpl(String maxPpl) {
        try {
            this.defaultMaxPpl = Integer.parseInt(maxPpl);
        } catch (NumberFormatException ex) {
            Log.i(TAG, "Invalid default max ppl");
            this.defaultMaxPpl = -1;
        }
    }

    private void setDefaultDescrip(String descrip) {
        this.defaultDescrip = descrip;
    }

    public void setDefaultImagePath(String path) {
        try {
            this.defaultImagePath = Uri.parse(path);
        } catch (Exception ex) {
            ex.printStackTrace();
            this.defaultImagePath = Uri.parse("");
        }
    }

    public void setDefaultImagePath(Context context, int drawableId) {
        setImagePath(context, drawableId);
    }



}
