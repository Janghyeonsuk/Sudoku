package com.example.sudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity {
    BoardGenerator board = new BoardGenerator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.numberpad);

        TableLayout table = (TableLayout) findViewById(R.id.tableLayout);

        TableLayout numberPad = (TableLayout) findViewById(R.id.numberPad);

        numberPad.setVisibility(View.INVISIBLE);

        CustomButton[][] buttons = new CustomButton[9][9];

        for (int i = 0; i < 9; i++) {
            TableRow tableRow = new TableRow(this);

            for (int j = 0; j < 9; j++) {
                buttons[i][j] = new CustomButton(this, i, j);

                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        numberPad.setVisibility(View.VISIBLE);
                    }
                });

                int number = board.get(i, j);

                if (Math.random() <= 0.60) {
                    buttons[i][j].set(number);
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
                    top = 35;
                }
                if (j == 3 || j == 6) {
                    left = 20;
                }

                layoutParams.setMargins(left, top, right, bottom);
                buttons[i][j].setLayoutParams(layoutParams);
                tableRow.addView(buttons[i][j]);
            }
            table.addView(tableRow);
        }
    }

    // Conflict
    public void setConflict() {

    }

    // No conflict
    public void unsetConflict() {

    }
}