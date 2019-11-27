package com.example.postit.models;

import androidx.annotation.Nullable;

public class Attendee {
    protected int studentId;
    protected String name;
    protected Integer contactNo;

    public Attendee(int studentId, String name, @Nullable Integer contactNo) {
        this.studentId = studentId;
        this.name = name;
        this.contactNo = contactNo;
    }
}
