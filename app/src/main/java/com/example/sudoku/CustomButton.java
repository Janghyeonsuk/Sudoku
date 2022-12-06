package com.example.sudoku;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class CustomButton extends FrameLayout {
    int row;
    int col;
    int value;

    int boxRow;
    int boxCol;

    TextView textView;
    TableLayout memo;
    TextView[] memos;
    public CustomButton(Context context, int row, int col) {
        super(context);
        this.row = row;
        this.col = col;

        boxRow = (row / 3) * 3;
        boxCol = (col / 3) * 3;

        textView = new TextView(context);

        textView.setTextSize(20);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundResource(R.drawable.button_selector);

        addView(textView);

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        memo = (TableLayout) layoutInflater.inflate(R.layout.layout_memo, null);
//        memo.setVisibility(INVISIBLE);
        addView(memo);

        int k=0;
        memos = new TextView[9];
        for(int i = 0; i < 3; i++) {
            TableRow tableRow = (TableRow) memo.getChildAt(i);
            for(int j = 0; j < 3; j++, k++) {
                TextView textView =  (TextView) tableRow.getChildAt(j);
                textView.setVisibility(INVISIBLE);
                memos[k] = textView;
            }
        }
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