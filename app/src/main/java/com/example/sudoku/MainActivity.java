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
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    BoardGenerator board = new BoardGenerator();
    CustomButton clickedCustomButton;
    TableLayout numberPad;

    CustomButton[][] buttons = new CustomButton[9][9];
    boolean[] selectedToggleButtons = new boolean[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

                buttons[i][j].setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        clickedCustomButton = (CustomButton) view;

                        View dialogView = (View) View.inflate(table.getContext(), R.layout.dialog_memo, null);
                        AlertDialog.Builder builder = new AlertDialog.Builder(table.getContext())
                                .setTitle("Memo")
                                .setView(dialogView)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int which) {
                                        int k = 0;
                                        TableLayout memoTable = (TableLayout) dialogView.findViewById(R.id.memoPad);

                                        for (int i = 0; i < 3; i++) {
                                            TableRow tableRow = (TableRow) memoTable.getChildAt(i);

                                            for (int j = 0; j < 3; j++, k++) {
                                                ToggleButton toggleButton = (ToggleButton) tableRow.getChildAt(j);

                                                if (toggleButton.isChecked()) {
                                                    selectedToggleButtons[k] = true;
                                                } else {
                                                    selectedToggleButtons[k] = false;
                                                }
                                            }
                                        }

                                        if (clickedCustomButton.generatedCustomButton == true) {
                                            Toast.makeText(getApplicationContext(), "초기에 생성된 버튼이라 메모가 불가능합니다.", Toast.LENGTH_SHORT).show();
                                        }

                                        for (int i = 0; i < 9; i++) {
                                            if (selectedToggleButtons[i] == true && clickedCustomButton.generatedCustomButton == false) {
                                                clickedCustomButton.set(0);
                                                clickedCustomButton.textView.setBackgroundResource(R.drawable.unconflict);
                                                unsetConflict();
                                                clickedCustomButton.memos[i].setVisibility(View.VISIBLE);
                                            }
                                        }
                                        dialogInterface.dismiss();
                                    }
                                })
                                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int which) {
                                        Toast.makeText(getApplicationContext(), "CANCEL", Toast.LENGTH_SHORT).show();
                                        dialogInterface.dismiss();
                                    }
                                })
                                .setNeutralButton("DELETE", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int which) {
                                        for (int i = 0; i < 9; i++) {
                                            selectedToggleButtons[i] = false;
                                            clickedCustomButton.memos[i].setVisibility(View.INVISIBLE);
                                        }
                                        Toast.makeText(getApplicationContext(), "DELETE MEMO", Toast.LENGTH_SHORT).show();
                                        dialogInterface.dismiss();
                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        return true;
                    }
                });

                int number = board.get(i, j);

                if (Math.random() <= 0.6) {
                    buttons[i][j].set(number);
                    buttons[i][j].generatedCustomButton = true;
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
                        buttons[i][j].generatedCustomButton = true;
                        buttons[i][j].textView.setBackgroundResource(R.drawable.button_selector);
                        memoInvisible();
                    }
                }
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (Math.random() <= 0.4) {
                            buttons[i][j].set(0);
                            buttons[i][j].generatedCustomButton = false;
                        }
                    }
                }

                Toast.makeText(getApplicationContext(), "RESET", Toast.LENGTH_SHORT).show();
            }
        });

        table.addView(reset);
    }

    public void setConflict() {
        int row = clickedCustomButton.row;
        int col = clickedCustomButton.col;
        int value = buttons[row][col].value;

        for (int i = 0; i < 9; i++) {
            if (buttons[row][i].value == value && clickedCustomButton != buttons[row][i]) {
                buttons[row][i].textView.setBackgroundResource(R.drawable.conflict);
                clickedCustomButton.textView.setBackgroundResource(R.drawable.conflict);
            }

            if (buttons[i][col].value == value && clickedCustomButton != buttons[i][col]) {
                buttons[i][col].textView.setBackgroundResource(R.drawable.conflict);
                clickedCustomButton.textView.setBackgroundResource(R.drawable.conflict);
            }
        }

        for (int i = clickedCustomButton.customBoxRow; i < clickedCustomButton.customBoxRow + 3; i++) {
            for (int j = clickedCustomButton.customBoxCol; j < clickedCustomButton.customBoxCol + 3; j++) {
                if (buttons[i][j].value == value && clickedCustomButton != buttons[i][j]) {
                    buttons[i][j].textView.setBackgroundResource(R.drawable.conflict);
                    clickedCustomButton.textView.setBackgroundResource(R.drawable.conflict);
                }
            }
        }
    }

    public void unsetConflict() {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                int num = buttons[y][x].value;
                boolean conflictButton = false;

                if (buttons[y][x].value == 0) continue;

                for (int i = 0; i < 9; i++) {
                    if (i == x) continue;

                    if (buttons[y][i].value == num) {
                        conflictButton = true;
                    }
                }

                for (int i = 0; i < 9; i++) {
                    if (i == y) continue;

                    if (buttons[i][x].value == num) {
                        conflictButton = true;
                    }
                }

                int boxRow = (x / 3) * 3;
                int boxCol = (y / 3) * 3;

                for (int j = boxCol; j < boxCol + 3; j++) {
                    for (int i = boxRow; i < boxRow + 3; i++) {
                        if (i == x && j == y) continue;

                        if (buttons[j][i].value == num) {
                            conflictButton = true;
                        }
                    }
                }

                if (conflictButton) {
                    buttons[y][x].textView.setBackgroundResource(R.drawable.conflict);
                } else {
                    buttons[y][x].textView.setBackgroundResource(R.drawable.unconflict);
                }
            }
        }
    }

    public void toast() {
        int value = clickedCustomButton.value;
        String a = String.valueOf(value);
        Toast.makeText(getApplicationContext(), "Input : " + a, Toast.LENGTH_SHORT).show();
    }

    public void memoInvisible() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 9; k++) {
                    buttons[i][j].memos[k].setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    public void memosInvisible() {
        for (int i = 0; i < 9; i++) {
            selectedToggleButtons[i] = false;
            clickedCustomButton.memos[i].setVisibility(View.INVISIBLE);
        }
    }

    public void onClickNum1(View view) {
        if (!clickedCustomButton.generatedCustomButton) {
            clickedCustomButton.set(1);
            setConflict();
            unsetConflict();
            memosInvisible();
            toast();
        } else {
            Toast.makeText(getApplicationContext(), "초기에 생성된 버튼이라 변경 불가능합니다.", Toast.LENGTH_SHORT).show();
        }
        numberPad = (TableLayout) findViewById(R.id.numberPad);
        numberPad.setVisibility(View.INVISIBLE);
    }

    public void onClickNum2(View view) {
        if (!clickedCustomButton.generatedCustomButton) {
            clickedCustomButton.set(2);
            setConflict();
            unsetConflict();
            memosInvisible();
            toast();
        } else {
            Toast.makeText(getApplicationContext(), "초기에 생성된 버튼이라 변경 불가능합니다.", Toast.LENGTH_SHORT).show();
        }
        numberPad = (TableLayout) findViewById(R.id.numberPad);
        numberPad.setVisibility(View.INVISIBLE);
    }

    public void onClickNum3(View view) {
        if (!clickedCustomButton.generatedCustomButton) {
            clickedCustomButton.set(3);
            setConflict();
            unsetConflict();
            memosInvisible();
            toast();
        } else {
            Toast.makeText(getApplicationContext(), "초기에 생성된 버튼이라 변경 불가능합니다.", Toast.LENGTH_SHORT).show();
        }
        numberPad = (TableLayout) findViewById(R.id.numberPad);
        numberPad.setVisibility(View.INVISIBLE);
    }

    public void onClickNum4(View view) {
        if (!clickedCustomButton.generatedCustomButton) {
            clickedCustomButton.set(4);
            setConflict();
            unsetConflict();
            memosInvisible();
            toast();
        } else {
            Toast.makeText(getApplicationContext(), "초기에 생성된 버튼이라 변경 불가능합니다.", Toast.LENGTH_SHORT).show();
        }
        numberPad = (TableLayout) findViewById(R.id.numberPad);
        numberPad.setVisibility(View.INVISIBLE);
    }

    public void onClickNum5(View view) {
        if (!clickedCustomButton.generatedCustomButton) {
            clickedCustomButton.set(5);
            setConflict();
            unsetConflict();
            memosInvisible();
            toast();
        } else {
            Toast.makeText(getApplicationContext(), "초기에 생성된 버튼이라 변경 불가능합니다.", Toast.LENGTH_SHORT).show();
        }
        numberPad = (TableLayout) findViewById(R.id.numberPad);
        numberPad.setVisibility(View.INVISIBLE);
    }

    public void onClickNum6(View view) {
        if (!clickedCustomButton.generatedCustomButton) {
            clickedCustomButton.set(6);
            setConflict();
            unsetConflict();
            memosInvisible();
            toast();
        } else {
            Toast.makeText(getApplicationContext(), "초기에 생성된 버튼이라 변경 불가능합니다.", Toast.LENGTH_SHORT).show();
        }
        numberPad = (TableLayout) findViewById(R.id.numberPad);
        numberPad.setVisibility(View.INVISIBLE);
    }

    public void onClickNum7(View view) {
        if (!clickedCustomButton.generatedCustomButton) {
            clickedCustomButton.set(7);
            setConflict();
            unsetConflict();
            memosInvisible();
            toast();
        } else {
            Toast.makeText(getApplicationContext(), "초기에 생성된 버튼이라 변경 불가능합니다.", Toast.LENGTH_SHORT).show();
        }
        numberPad = (TableLayout) findViewById(R.id.numberPad);
        numberPad.setVisibility(View.INVISIBLE);
    }

    public void onClickNum8(View view) {
        if (!clickedCustomButton.generatedCustomButton) {
            clickedCustomButton.set(8);
            setConflict();
            unsetConflict();
            memosInvisible();
            toast();
        } else {
            Toast.makeText(getApplicationContext(), "초기에 생성된 버튼이라 변경 불가능합니다.", Toast.LENGTH_SHORT).show();
        }
        numberPad = (TableLayout) findViewById(R.id.numberPad);
        numberPad.setVisibility(View.INVISIBLE);
    }

    public void onClickNum9(View view) {
        if (!clickedCustomButton.generatedCustomButton) {
            clickedCustomButton.set(9);
            setConflict();
            unsetConflict();
            memosInvisible();
            toast();
        } else {
            Toast.makeText(getApplicationContext(), "초기에 생성된 버튼이라 변경 불가능합니다.", Toast.LENGTH_SHORT).show();
        }
        numberPad = (TableLayout) findViewById(R.id.numberPad);
        numberPad.setVisibility(View.INVISIBLE);
    }

    public void onClickCancel(View view) {
        numberPad = (TableLayout) findViewById(R.id.numberPad);
        numberPad.setVisibility(View.INVISIBLE);
    }

    public void onClickDel(View view) {
        if (!clickedCustomButton.generatedCustomButton) {
            clickedCustomButton.textView.setBackgroundResource(R.drawable.unconflict);
            clickedCustomButton.set(0);
            unsetConflict();

        } else {
            Toast.makeText(getApplicationContext(), "초기에 생성된 버튼이라 삭제 불가능합니다.", Toast.LENGTH_SHORT).show();
        }
        numberPad = (TableLayout) findViewById(R.id.numberPad);
        numberPad.setVisibility(View.INVISIBLE);

        memosInvisible();
        Toast.makeText(getApplicationContext(), "DELETE", Toast.LENGTH_SHORT).show();
    }

}