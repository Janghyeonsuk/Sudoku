package com.example.sudoku;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;


public class CustomButton extends FrameLayout {
    int row;
    int col;
    int value;

    public CustomButton(Context context, int row, int col) {
        super(context);
        this.row = row;
        this.col = col;
    }
        public void set (int a){

        }

        // Conflict
        public void setConflict () {

        }
        // No conflict
        public void unsetConflict () {

        }
    }
}