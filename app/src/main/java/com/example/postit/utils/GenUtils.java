package com.example.postit.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.AnyRes;
import androidx.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.json.JSONObject;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class GenUtils {
    public enum DateFormat { STANDARD }
    public enum TimeFormat { STANDARD }
    public static final int PICK_IMAGE_REQUEST = 100;

    public static Date getValidDate(String date, DateFormat df) throws ParseException {
        switch (df) {
            case STANDARD:
                if (date.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
                    return new SimpleDateFormat("dd/MM/yyyy").parse(date);
                } else if (date.matches("([0-9]{2})-([0-9]{2})-([0-9]{4})")) {
                    return new SimpleDateFormat("dd-MM-yyyy").parse(date);
                } else {
                    throw new ParseException("Invalid Date", 0);
                }
            default:
                throw new ParseException("Invalid Date", 0);

        }
    }

    public static Time getValidTime(String time, TimeFormat tf) throws ParseException {
        switch(tf) {
            case STANDARD:
                if (time.matches("([0-9]{2}):([0-9]{2})")) {
                    return new Time(new SimpleDateFormat("HH:mm").parse(time).getTime());
                } else {
                    throw new ParseException("Invalid Date", 0);
                }
            default:
                throw new ParseException("Invalid Time", 0);
        }
    }

    public static void chooseImage(Activity activity) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
    }

    public static final Uri getUriToDrawable(@NonNull Context context,
                                             @AnyRes int drawableId) {
        Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + context.getResources().getResourcePackageName(drawableId)
                + '/' + context.getResources().getResourceTypeName(drawableId)
                + '/' + context.getResources().getResourceEntryName(drawableId) );
        return imageUri;
    }

    public static <T> T[] getArrayFromObject(Object obj) {
        JsonArray jArr = ((JsonObject) obj).getAsJsonArray();
        T[] array = (T[]) new Object[jArr.size()];
        try {
            for (int i = 0; i < jArr.size(); ++i) {
                array[i] = (T) jArr.get(i);
            }
            return array;
        } catch (ClassCastException ex) {
            return array;
        }

    }

    public static HashMap<String, Object> getMapFromObject(JSONObject obj) {
        return new Gson().fromJson(obj.toString(), HashMap.class);
    }
}
