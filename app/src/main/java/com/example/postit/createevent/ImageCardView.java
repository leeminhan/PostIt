package com.example.postit.createevent;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.postit.R;

/**
 * TODO: document your custom view class.
 */
public class ImageCardView extends CardView {
    private String mExampleString = getResources().getString(R.string.image_card_view_default_text);
    private Drawable mExampleDrawable;
    private ImageView cardImageView;
    private TextView cardTextView;

    public ImageCardView(Context context) {
        super(context);
        init(null, 0);
    }

    public ImageCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public ImageCardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.ImageCardView, defStyle, 0);

        mExampleString = a.getString(
                R.styleable.ImageCardView_cardString);

        if (a.hasValue(R.styleable.ImageCardView_cardDrawable)) {
            mExampleDrawable = a.getDrawable(
                    R.styleable.ImageCardView_cardDrawable);
            mExampleDrawable.setCallback(this);
        }
        a.recycle();

        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.image_card_view, this, true);


        cardImageView = (ImageView) findViewById(R.id.card_image);
        cardTextView = findViewById(R.id.card_text);

        if (mExampleDrawable != null) {
            cardImageView.setImageDrawable(mExampleDrawable);
        }

        if (mExampleString != null) {
            cardTextView.setText(mExampleString);
        }


        // Set up a default TextPaint object
//        mTextPaint = new TextPaint();
//        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
//        mTextPaint.setTextAlign(Paint.Align.LEFT);
//
//        // Update TextPaint and text measurements from attributes
//        invalidateTextPaintAndMeasurements();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /**
     * Gets the example string attribute value.
     *
     * @return The example string attribute value.
     */
    public String getExampleString() {
        return mExampleString;
    }

    /**
     * Gets the example drawable attribute value.
     *
     * @return The example drawable attribute value.
     */
    public Drawable getExampleDrawable() {
        return mExampleDrawable;
    }

    /**
     * Sets the view's example drawable attribute value. In the example view, this drawable is
     * drawn above the text.
     *
     * @param exampleDrawable The example drawable attribute value to use.
     */
    public void setExampleDrawable(Drawable exampleDrawable) {
        mExampleDrawable = exampleDrawable;
    }
}
