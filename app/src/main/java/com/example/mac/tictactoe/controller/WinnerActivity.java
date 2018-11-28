package com.example.mac.tictactoe.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mac.tictactoe.R;

public class WinnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        TextView textView = findViewById(R.id.text_view_winner);
        String winner = getIntent().getExtras().getString("winner");
        textView.setText(winner);
    }
}
