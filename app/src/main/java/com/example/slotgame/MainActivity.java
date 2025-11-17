package com.example.slotgame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
private String name;
    public static int Pubcounttrue = 0, gamePlayed = 0;
    private EditText nameInput;
    private TextView n1, n2, n3, n4, n5, n6, random, numbers;
    private Button start, newgame, scorePage,go;

    private boolean isRunning = false;
    private int count = 0;
    private int counttrue = 0;

    private final Random rand = new Random();
    private final Handler handler = new Handler();

    private Intent score;
    private Runnable numberGenerator;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score = new Intent(MainActivity.this, ScoreActivity.class);

        n1 = findViewById(R.id.num1);
        n2 = findViewById(R.id.num2);
        n3 = findViewById(R.id.num3);
        n4 = findViewById(R.id.num4);
        n5 = findViewById(R.id.num5);
        n6 = findViewById(R.id.num6);
        nameInput = findViewById(R.id.name);
        numbers = findViewById(R.id.points);
        random = findViewById(R.id.randNum);
        newgame = findViewById(R.id.newgame);
        start = findViewById(R.id.startstop);
        scorePage = findViewById(R.id.ScorePage);
        go=findViewById(R.id.sendName);
        generateNewNumbers();
        random.setText("0");
        numbers.setText("");
        start.setText("start");


        go.setOnClickListener(view -> {
            String name = nameInput.getText().toString();
            if (!name.isEmpty()) {
                score.putExtra("NAME",name);
            }
        });
        scorePage.setOnClickListener(v -> startActivity(score));

        newgame.setOnClickListener(v -> {
            if (count >= 6) {
                gamePlayed++;
                resetGame();
            }
        });


        numberGenerator = new Runnable() {
            @Override
            public void run() {
                if (isRunning) {
                    int num = rand.nextInt(40) + 1;
                    random.setText(String.valueOf(num));
                    handler.postDelayed(this, 100);
                }
            }
        };

        start.setOnClickListener(v -> {
            if (!isRunning && count < 6) {
                startRandomGenerator();
            } else {
                stopRandomGenerator();
            }
        });
    }


    private void startRandomGenerator() {
        isRunning = true;
        count++;
        start.setText("stop");
        start.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        handler.post(numberGenerator);
    }

    private void stopRandomGenerator() {
        isRunning = false;
        start.setText("start");
        start.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));

        String drawn = random.getText().toString();
        checkMatch(n1, drawn);
        checkMatch(n2, drawn);
        checkMatch(n3, drawn);
        checkMatch(n4, drawn);
        checkMatch(n5, drawn);
        checkMatch(n6, drawn);
    }

    private void checkMatch(TextView tv, String value) {
        if (tv.getText().toString().equals(value)) {
            Pubcounttrue++;
            counttrue++;
            numbers.setText(counttrue + " out of 6");
            tv.setBackgroundColor(Color.RED);
        }
    }

    private void generateNewNumbers() {
        n1.setText(String.valueOf(rand.nextInt(40) + 1));
        n2.setText(String.valueOf(rand.nextInt(40) + 1));
        n3.setText(String.valueOf(rand.nextInt(40) + 1));
        n4.setText(String.valueOf(rand.nextInt(40) + 1));
        n5.setText(String.valueOf(rand.nextInt(40) + 1));
        n6.setText(String.valueOf(rand.nextInt(40) + 1));
    }

    private void resetGame() {
        count = 0;
        counttrue = 0;
        numbers.setText("0 of 6");


        generateNewNumbers();
        random.setText("0");
        start.setText("start");
        isRunning = false;

        n1.setBackgroundColor(Color.TRANSPARENT);
        n2.setBackgroundColor(Color.TRANSPARENT);
        n3.setBackgroundColor(Color.TRANSPARENT);
        n4.setBackgroundColor(Color.TRANSPARENT);
        n5.setBackgroundColor(Color.TRANSPARENT);
        n6.setBackgroundColor(Color.TRANSPARENT);
    }
}
