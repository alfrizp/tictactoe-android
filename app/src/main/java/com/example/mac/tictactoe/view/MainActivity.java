package com.example.mac.tictactoe.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.mac.tictactoe.R;
import com.example.mac.tictactoe.model.Board;
import com.example.mac.tictactoe.model.Cell;
import com.example.mac.tictactoe.model.Player;

public class MainActivity extends AppCompatActivity {
    public Board board;
    public Cell cell;
    public Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }
}
