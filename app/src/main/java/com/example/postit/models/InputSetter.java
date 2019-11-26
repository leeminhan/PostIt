package com.example.postit.models;

import android.view.View;

import java.util.Map;

public interface InputSetter {
    String getInput();
    String getErrorCause();
    Map<String, Object> getExtra();
    String getViewKey();
}
