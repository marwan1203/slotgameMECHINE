package com.example.slotgame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreActivity extends AppCompatActivity {
    private TextView played,wins;
    private Button backButton;
Intent back =new Intent(ScoreActivity.this,MainActivity.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        played=findViewById(R.id.gamesnum);
        wins=findViewById(R.id.textView3);
        played.setText("Number of games played:"+String.valueOf(MainActivity.gamePlayed));
        wins.setText(""+"Number of wins:"+String.valueOf(MainActivity.Pubcounttrue));
        backButton.setOnClickListener(view -> {
            startActivity(back);
        });

        };
    }
