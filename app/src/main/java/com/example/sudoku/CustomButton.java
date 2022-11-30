package com.example.sudoku;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;


public class CustomButton extends FrameLayout {
    int row;
    int col;
    int value;

    int boxRow;
    int boxCol;

    TextView textView;

    public CustomButton(Context context) {
        super(context);
    }

    public CustomButton(Context context, int row, int col) {
        super(context);
        this.row = row;
        this.col = col;

        textView = new TextView(context);

        textView.setTextSize(44);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setClickable(true);
        textView.setBackgroundResource(R.drawable.button_selector);
        addView(textView);
    }

    public void set(int a) {
        if (a == 0) {
            this.value = 0;
            textView.setText(null);
        } else {
            this.value = a;
            textView.setText(String.valueOf(a));
        }
    }

}