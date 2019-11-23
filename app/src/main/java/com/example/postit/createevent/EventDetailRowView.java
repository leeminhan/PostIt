package com.example.postit.createevent;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.example.postit.R;
import org.w3c.dom.Text;

/**
 * TODO: document your custom view class.
 */
public class EventDetailRowView extends LinearLayout {
    private String mExampleString; // TODO: use a default from R.string...
    private Drawable mExampleDrawable;
    private boolean onlyNumberInput;
    public ImageView rowImage;
    public EditText rowField;


    public EventDetailRowView(Context context) {
        super(context);
        init(null, 0);
    }

    public EventDetailRowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public EventDetailRowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }


    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.EventDetailRowView, defStyle, 0);

        mExampleString = a.getString(
                R.styleable.EventDetailRowView_rowField);

        if (a.hasValue(R.styleable.EventDetailRowView_onlyNumberInput))
            onlyNumberInput = a.getBoolean(R.styleable.EventDetailRowView_onlyNumberInput, false);

        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.


        if (a.hasValue(R.styleable.EventDetailRowView_rowImage)) {
            mExampleDrawable = a.getDrawable(
                    R.styleable.EventDetailRowView_rowImage);
            mExampleDrawable.setCallback(this);
        }

        a.recycle();


        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.event_detail_row, this, true);


        rowImage = findViewById(R.id.row_image);
        rowField = findViewById(R.id.row_text);


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if (mExampleDrawable != null) {
            rowImage.setImageDrawable(mExampleDrawable);
        }

        if (mExampleString != null) {
            rowField.setHint(mExampleString);
        }

        if (this.getParent() instanceof LinearLayout
                && ((LinearLayout) this.getParent()).getOrientation() == HORIZONTAL) {
            rowField.setSingleLine();
        }

        if (onlyNumberInput) {
            rowField.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
    }

    public void setText(String text) {
        rowField.setText(text);
    }

    public void setText(Number n) {
        setText(String.valueOf(n));
    }

    public Editable getText() {
        return rowField.getText();
    }
}
