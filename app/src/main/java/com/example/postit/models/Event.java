package com.example.postit.models;

import android.support.annotation.Nullable;

import java.net.URI;
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

    private URI imagePath;

    public Event() { }

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
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Event setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Event setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getDate() {
        return date.toString();
    }

    public Event setDate(Date date) {
        this.date = date;
        return this;
    }

    public Event setDate(String date) throws ParseException {
        this.date = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        return this;
    }

    public String getTime() {
        return time.toString();
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Event setTime(String time) throws ParseException {
        this.time = new Time(new SimpleDateFormat("HH:mm").parse(time).getTime());
        return this;
    }

    public String getLocation() {
        return location;
    }

    public Event setLocation(String location) {
        this.location = location;
        return this;
    }

    public int getPpl() {
        return ppl;
    }

    public Event setPpl(int ppl) {
        this.ppl = ppl;
        return this;
    }

    public int getMaxPpl() {
        return maxPpl;
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

    public URI getImagePath() {
        return imagePath;
    }

    public Event setImagePath(URI imagePath) {
        this.imagePath = imagePath;
        return this;
    }


}