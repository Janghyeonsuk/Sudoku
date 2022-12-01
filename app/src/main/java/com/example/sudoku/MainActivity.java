package com.example.sudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity {
    BoardGenerator board = new BoardGenerator();

    CustomButton clickedCustomButton;
    TableLayout numberPad;

    CustomButton[][] buttons = new CustomButton[9][9];
    Button reset = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.numberpad);

        TableLayout table = (TableLayout) findViewById(R.id.tableLayout);
        TableLayout numberPad = (TableLayout) findViewById(R.id.numberPad);

        numberPad.setVisibility(View.INVISIBLE);



        for (int i = 0; i < 9; i++) {
            TableRow tableRow = new TableRow(this);

            for (int j = 0; j < 9; j++) {

                buttons[i][j] = new CustomButton(this, i, j);

                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        clickedCustomButton = (CustomButton) view;
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
                    top = 30;
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
        reset = new Button(this);
        reset.setText("RESET");
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BoardGenerator reGame = new BoardGenerator();

                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        int number = reGame.get(i,j);
                        buttons[i][j].set(number);
                    }
                }
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (Math.random() <= 0.40) {
                            buttons[i][j].set(0);
                        }
                    }
                }
            }
        });
        table.addView(reset);
    }

    public void onClickNum1(View view) {
        clickedCustomButton.set(1);
        numberPad = (TableLayout) findViewById(R.id.numberPad);
        numberPad.setVisibility(View.INVISIBLE);
    }

    public void onClickNum2(View view) {
        clickedCustomButton.set(2);
        numberPad = (TableLayout) findViewById(R.id.numberPad);
        numberPad.setVisibility(View.INVISIBLE);
    }

    public void onClickNum3(View view) {
        clickedCustomButton.set(3);
        numberPad = (TableLayout) findViewById(R.id.numberPad);
        numberPad.setVisibility(View.INVISIBLE);
    }

    public void onClickNum4(View view) {
        clickedCustomButton.set(4);
        numberPad = (TableLayout) findViewById(R.id.numberPad);
        numberPad.setVisibility(View.INVISIBLE);
    }

    public void onClickNum5(View view) {
        clickedCustomButton.set(5);
        numberPad = (TableLayout) findViewById(R.id.numberPad);
        numberPad.setVisibility(View.INVISIBLE);
    }

    public void onClickNum6(View view) {
        clickedCustomButton.set(6);
        numberPad = (TableLayout) findViewById(R.id.numberPad);
        numberPad.setVisibility(View.INVISIBLE);
    }

    public void onClickNum7(View view) {
        clickedCustomButton.set(7);
        numberPad = (TableLayout) findViewById(R.id.numberPad);
        numberPad.setVisibility(View.INVISIBLE);
    }

    public void onClickNum8(View view) {
        clickedCustomButton.set(8);
        numberPad = (TableLayout) findViewById(R.id.numberPad);
        numberPad.setVisibility(View.INVISIBLE);
    }

    public void onClickNum9(View view) {
        clickedCustomButton.set(9);
        numberPad = (TableLayout) findViewById(R.id.numberPad);
        numberPad.setVisibility(View.INVISIBLE);
    }

    public void onClickCancel(View view) {
        numberPad = (TableLayout) findViewById(R.id.numberPad);
        numberPad.setVisibility(View.INVISIBLE);
    }

    public void onClickDel(View view) {
        clickedCustomButton.set(0);
        numberPad = (TableLayout) findViewById(R.id.numberPad);
        numberPad.setVisibility(View.INVISIBLE);
    }

    // Conflict
    public void setConflict() {

    }

    // No conflict
    public void unsetConflict() {

    }
}