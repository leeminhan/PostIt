package com.example.postit.models;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

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

    public View getView() {
        try {
            return (View) extra.get(s.getViewKey());
        } catch (ClassCastException ex) {
            return null;
        }
    }
}
