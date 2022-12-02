package com.example.sudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    BoardGenerator board = new BoardGenerator();
    CustomButton clickedCustomButton;
    TableLayout numberPad;

    CustomButton[][] buttons = new CustomButton[9][9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableLayout table = (TableLayout) findViewById(R.id.tableLayout);
        TableLayout numberPad = (TableLayout) findViewById(R.id.numberPad);

        numberPad.setVisibility(View.INVISIBLE);

        View dialogView = (View) View.inflate(this, R.layout.dialog_memo, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Memo")
                .setView(dialogView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        switch (which) {
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                    }
                })
                .setNeutralButton("Delete", null);
        AlertDialog dialog = builder.create();

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

                buttons[i][j].setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        dialog.show();
                        return true;
                    }
                });

                int number = board.get(i, j);

                if (Math.random() <= 0.6) {
                    buttons[i][j].set(number);
                }

                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1.0f);

                int left = 5;
                int top = 3;
                int right = 5;
                int bottom = 0;

                if (i == 3 || i == 6) {
                    top = 10;
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
        Button reset = new Button(this);
        reset.setText("RESET");
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BoardGenerator reGame = new BoardGenerator();

                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        int number = reGame.get(i, j);
                        buttons[i][j].set(number);
                        buttons[i][j].textView.setBackgroundResource(R.drawable.button_selector);
                    }
                }
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (Math.random() <= 0.4) {
                            buttons[i][j].set(0);
                        }
                    }
                }
            }
        });

        table.addView(reset);
    }

    public void setConflict() {
        int value = clickedCustomButton.value;
        int row = clickedCustomButton.row;
        int col = clickedCustomButton.col;
        for (int i = 0; i < 9; i++) {
            if (buttons[row][i].value == value) {
                buttons[row][i].textView.setBackgroundResource(R.drawable.conflict);
                clickedCustomButton.textView.setBackgroundResource(R.drawable.conflict);
            }

            if (buttons[i][col].value == value) {
                buttons[i][col].textView.setBackgroundResource(R.drawable.conflict);
                clickedCustomButton.textView.setBackgroundResource(R.drawable.conflict);
            }
        }

        for (int i = clickedCustomButton.boxRow; i < clickedCustomButton.boxRow + 3; i++) {
            for (int j = clickedCustomButton.boxCol; j < clickedCustomButton.boxCol + 3; j++) {
                if (buttons[i][j].value == value) {
                    buttons[i][j].textView.setBackgroundResource(R.drawable.conflict);
                    clickedCustomButton.textView.setBackgroundResource(R.drawable.conflict);
                }
            }
        }
    }

    public void unsetConflict() {
        int value = clickedCustomButton.value;
        int row = clickedCustomButton.row;
        int col = clickedCustomButton.col;
        for (int i = 0; i < 9; i++) {
            if (buttons[row][i].value != value) {
                buttons[row][i].textView.setBackgroundResource(R.drawable.unconflict);
                clickedCustomButton.textView.setBackgroundResource(R.drawable.unconflict);
            }

            if (buttons[i][col].value != value) {
                buttons[i][col].textView.setBackgroundResource(R.drawable.unconflict);
                clickedCustomButton.textView.setBackgroundResource(R.drawable.unconflict);
            }
        }

        for (int i = clickedCustomButton.boxRow; i < clickedCustomButton.boxRow + 3; i++) {
            for (int j = clickedCustomButton.boxCol; j < clickedCustomButton.boxCol + 3; j++) {
                if (buttons[i][j].value != value) {
                    buttons[i][j].textView.setBackgroundResource(R.drawable.unconflict);
                    clickedCustomButton.textView.setBackgroundResource(R.drawable.unconflict);
                }
            }
        }
    }

    public void toast() {
        int value = clickedCustomButton.value;
        String a = String.valueOf(value);
        Toast.makeText(getApplicationContext(), "Input : " + a, Toast.LENGTH_SHORT).show();
    }

    public void onClickNum1(View view) {
        clickedCustomButton.set(1);
        numberPad = (TableLayout) findViewById(R.id.numberPad);
        numberPad.setVisibility(View.INVISIBLE);
        setConflict();
        unsetConflict();
        toast();
    }

    public void onClickNum2(View view) {
        clickedCustomButton.set(2);
        numberPad = (TableLayout) findViewById(R.id.numberPad);
        numberPad.setVisibility(View.INVISIBLE);
        setConflict();
        unsetConflict();
        toast();
    }

    public void onClickNum3(View view) {
        clickedCustomButton.set(3);
        numberPad = (TableLayout) findViewById(R.id.numberPad);
        numberPad.setVisibility(View.INVISIBLE);
        setConflict();
        unsetConflict();
        toast();
    }

    public void onClickNum4(View view) {
        clickedCustomButton.set(4);
        numberPad = (TableLayout) findViewById(R.id.numberPad);
        numberPad.setVisibility(View.INVISIBLE);
        setConflict();
        unsetConflict();
        toast();
    }

    public void onClickNum5(View view) {
        clickedCustomButton.set(5);
        numberPad = (TableLayout) findViewById(R.id.numberPad);
        numberPad.setVisibility(View.INVISIBLE);
        setConflict();
        unsetConflict();
        toast();
    }

    public void onClickNum6(View view) {
        clickedCustomButton.set(6);
        numberPad = (TableLayout) findViewById(R.id.numberPad);
        numberPad.setVisibility(View.INVISIBLE);
        setConflict();
        unsetConflict();
        toast();
    }

    public void onClickNum7(View view) {
        clickedCustomButton.set(7);
        numberPad = (TableLayout) findViewById(R.id.numberPad);
        numberPad.setVisibility(View.INVISIBLE);
        setConflict();
        unsetConflict();
        toast();
    }

    public void onClickNum8(View view) {
        clickedCustomButton.set(8);
        numberPad = (TableLayout) findViewById(R.id.numberPad);
        numberPad.setVisibility(View.INVISIBLE);
        setConflict();
        unsetConflict();
        toast();
    }

    public void onClickNum9(View view) {
        clickedCustomButton.set(9);
        numberPad = (TableLayout) findViewById(R.id.numberPad);
        numberPad.setVisibility(View.INVISIBLE);
        setConflict();
        unsetConflict();
        toast();
    }

    public void onClickCancel(View view) {
        numberPad = (TableLayout) findViewById(R.id.numberPad);
        numberPad.setVisibility(View.INVISIBLE);
    }

    public void onClickDel(View view) {
        clickedCustomButton.set(0);
        numberPad = (TableLayout) findViewById(R.id.numberPad);
        numberPad.setVisibility(View.INVISIBLE);
        Toast.makeText(getApplicationContext(), "DELETE", Toast.LENGTH_SHORT).show();
    }


}