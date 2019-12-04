package com.example.postit.models;

import android.graphics.Rect;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

public class HoriztontalSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private final int horizontalSpaceHeight;

    public HoriztontalSpaceItemDecoration(int horizontalSpaceHeight) {
        this.horizontalSpaceHeight = horizontalSpaceHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.right = horizontalSpaceHeight;
    }
}