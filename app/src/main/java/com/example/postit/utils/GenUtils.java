package com.example.postit.utils;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GenUtils {
    public enum DateFormat { STANDARD }
    public enum TimeFormat { STANDARD }

    public static Date getValidDate(String date, DateFormat df) throws ParseException {
        switch (df) {
            case STANDARD:
                if (date.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
                    return new SimpleDateFormat("dd/MM/yyyy").parse(date);
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

}
