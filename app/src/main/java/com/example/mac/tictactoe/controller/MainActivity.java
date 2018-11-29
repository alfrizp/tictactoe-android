package com.example.mac.tictactoe.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mac.tictactoe.R;
import com.example.mac.tictactoe.controller.retrofit.RetrofitClientInstance;
import com.example.mac.tictactoe.controller.retrofit.User;
import com.example.mac.tictactoe.controller.retrofit.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "DEBUG -- ";
    private List<String> nameList = new ArrayList<>();
    private Spinner player1Spinner, player2Spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        UserService service = RetrofitClientInstance.getRetrofitInstance().create(UserService.class);
        Call<List<User>> call = service.getAllUser();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                initPlayer(response.body());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Try again later", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initPlayer(List<User> users) {
        for (User user : users) {
            nameList.add(user.getName());
        }

        player1Spinner = findViewById(R.id.player1_spinner);
        player2Spinner = findViewById(R.id.player2_spinner);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, nameList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        player1Spinner.setAdapter(dataAdapter);
        player2Spinner.setAdapter(dataAdapter);
    }

    public void startGameButtonOnClick(View view) {
        String player1Name, player2Name;
        player1Name = player1Spinner.getSelectedItem().toString();
        player2Name = player2Spinner.getSelectedItem().toString();

        if (player1Name.equals(player2Name)) {
            Toast.makeText(MainActivity.this, "Player tidak boleh sama", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, TicTacToeActivity.class);
            intent.putExtra("player1", player1Name);
            intent.putExtra("player2", player2Name);
            startActivity(intent);
        }
    }
}
