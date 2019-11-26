package com.example.postit.models;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class People implements Iterable<Person> {
    protected List<Person> people;

    public People() {
        people = new ArrayList<Person>();
    }

    @NonNull
    @Override
    public Iterator<Person> iterator() {
        return people.iterator();
    }

}
