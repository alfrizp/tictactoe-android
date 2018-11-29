package com.example.mac.tictactoe.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mac.tictactoe.R;
import com.example.mac.tictactoe.controller.retrofit.RetrofitClientInstance;
import com.example.mac.tictactoe.controller.retrofit.User;
import com.example.mac.tictactoe.controller.retrofit.UserService;
import com.example.mac.tictactoe.model.Board;
import com.example.mac.tictactoe.model.Cell;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicTacToeActivity extends AppCompatActivity {
    public Board board;
    public TextView playerXLabel, playerOLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        String player1 = getIntent().getExtras().getString("player1");
        String player2 = getIntent().getExtras().getString("player2");
        this.board = new Board(player1, player2);

        playerXLabel = findViewById(R.id.player_x);
        playerOLabel = findViewById(R.id.player_o);
        playerXLabel.setText(player1 + " - X");
        playerOLabel.setText(player2 + " - O");
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

        if (board.isGameOver()) {
            disableButtons();
            Intent intent = new Intent(this, WinnerActivity.class);
            intent.putExtra("winner", board.getWinner());
            startActivity(intent);
        }
    }

    public void onClickResetBtn(View view) {
        GridLayout gridLayout = findViewById(R.id.grid_layout);
        int childCount = gridLayout.getChildCount();

        for (int i = 0; i < childCount; i++) {
            Button button = (Button) gridLayout.getChildAt(i);
            button.setText("");
            button.setEnabled(true);
        }

        board.resetBoard();
    }

    private void disableButtons() {
        GridLayout gridLayout = findViewById(R.id.grid_layout);
        int childCount = gridLayout.getChildCount();

        for (int i = 0; i < childCount; i++) {
            ((Button) gridLayout.getChildAt(i)).setEnabled(false);
        }
    }
}
