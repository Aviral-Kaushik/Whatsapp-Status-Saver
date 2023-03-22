package com.aviral.whatsappstatussaver.Utils;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewMargin extends RecyclerView.ItemDecoration {

    private int margin;
    public RecyclerViewMargin(int mMargin) {
        margin = mMargin;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.right = margin;

        outRect.left = margin;

        outRect.bottom = margin;

        outRect.top = margin;
    }
}
