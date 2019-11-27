package com.example.postit.utils;

import android.graphics.Color;
import android.widget.EditText;

public class UIUtils {
    public static void disableEditText(EditText editText) {
        editText.setFocusable(false);
        editText.setEnabled(false);
        editText.setCursorVisible(false);
        editText.setKeyListener(null);
        editText.setBackgroundColor(Color.TRANSPARENT);
        editText.setTextColor(Color.DKGRAY);
    }

}
