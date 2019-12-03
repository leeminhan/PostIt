package com.example.postit.models;

import androidx.annotation.Nullable;

import java.util.Map;

public class InvalidInputException extends Exception {
    private Map<String, Object> extra;
    private InputSetter s;

    public InvalidInputException(String finalMsg) {
        super(finalMsg);
    }

    public InvalidInputException(InputSetter s, @Nullable String msg) {
        super(String.format("%s: Field is Invalid.%s", s.getErrorCause(), msg != null ? String.format(" %s", msg) : ""));
        extra = s.getExtra();
        this.s = s;
    }

    public Object getCauseObject() {
        try {
            return extra.get(s.getCauseKey());
        } catch (ClassCastException ex) {
            return null;
        }
    }
}
