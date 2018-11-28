package com.example.mac.tictactoe.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import com.example.mac.tictactoe.R;
import com.example.mac.tictactoe.model.Board;
import com.example.mac.tictactoe.model.Cell;

public class MainActivity extends AppCompatActivity {
    public Board board = new Board();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    public void onClickBtn(View view) {
        Button button = (Button) view;

        String buttonTag = (String) button.getTag();

        int row, col;
        row = Integer.parseInt(buttonTag.substring(0,1));
        col = Integer.parseInt(buttonTag.substring(1,2));

        board.fillCell(row, col);
        Cell selectedCell = board.getCell(row, col);

        button.setText(selectedCell.getValue());

        if (board.isGameOver) {
            Intent intent = new Intent(this, WinnerActivity.class);
            intent.putExtra("winner", board.winner);
            startActivity(intent);
        }
    }

    public void onClickResetBtn(View view) {
        GridLayout gridLayout = findViewById(R.id.grid_layout);
        int childCount = gridLayout.getChildCount();

        for (int i = 0; i < childCount; i++) {
            ((Button) gridLayout.getChildAt(i)).setText("");
        }

        board.resetBoard();
    }
}
