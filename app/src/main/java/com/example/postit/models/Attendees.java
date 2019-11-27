package com.example.postit.models;

import androidx.annotation.NonNull;

import java.util.Iterator;
import java.util.List;

public class Attendees implements Iterable<Attendee> {

    protected List<Attendee> attendees;

    public Attendees(List<Attendee> attendees) {
        this.attendees = attendees;
    }

    @NonNull
    @Override
    public Iterator<Attendee> iterator() {
        return attendees.iterator();
    }
}
