package com.example.sudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableLayout table = (TableLayout) findViewById(R.id.tableLayout);
        Button[][] buttons = new Button[9][9];
        BoardGenerator board = new BoardGenerator();


        for (int i = 0; i < 9; i++) {
            TableRow tableRow = new TableRow(this);

            for (int j = 0; j < 9; j++) {
                buttons[i][j] = new Button(this);

                buttons[i][j].setTextSize(30);
//                buttons[i][j].setBackgroundColor(Color.rgb(255, 255, 255));

                int number = board.get(i, j);

                if (Math.random() <= 0.60) {
                    buttons[i][j].setText(Integer.toString(number));
                }

                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1.0f);

                int left = 5;
                int top = 5;
                int right = 5;
                int bottom = 5;

                if (i == 3 || i == 6) {
                    top = 15;
                }
                if (j == 3 || j == 6) {
                    left = 15;
                }

                layoutParams.setMargins(left, top, right, bottom);
                buttons[i][j].setLayoutParams(layoutParams);
                tableRow.addView(buttons[i][j]);
            }
            table.addView(tableRow);
        }
    }
}