package com.example.slotgame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreActivity extends AppCompatActivity {

    private TextView played, wins, name;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        played = findViewById(R.id.gamesnum);
        wins = findViewById(R.id.textView3);
        backButton = findViewById(R.id.back);
        name = findViewById(R.id.name);

        name.setText("Hello " + getIntent().getStringExtra("NAME") + ",");
        played.setText("Number of games played: " + MainActivity.gamePlayed);
        wins.setText("Number of wins: " + MainActivity.Pubcounttrue);

        backButton.setOnClickListener(view -> {
            Intent back = new Intent(ScoreActivity.this, MainActivity.class);
            startActivity(back);
        });
    }
}
