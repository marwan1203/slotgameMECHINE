package com.example.slotgame;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private AlertDialog.Builder dialog;
    private EditText nameInput;
    private TextView n1, n2, n3, n4, n5, n6, random, points,logindits;
    private Button start, newgame, scorePage,exit;

    private boolean isRunning;
    private Random rand = new Random();
    private int count = 0;

    public static int gamePlayed = 0,Pubcounttrue=0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new AlertDialog.Builder(this);

        // Number TextViews
        n1 = findViewById(R.id.num1);
        n2 = findViewById(R.id.num2);
        n3 = findViewById(R.id.num3);
        n4 = findViewById(R.id.num4);
        n5 = findViewById(R.id.num5);
        n6 = findViewById(R.id.num6);
        nameInput = findViewById(R.id.name);
        points = findViewById(R.id.points);
        random = findViewById(R.id.randNum);
        start = findViewById(R.id.startstop);
        newgame = findViewById(R.id.newgame);
        exit = findViewById(R.id.exit);
        scorePage=findViewById(R.id.ScorePage);
        login();
        initNumbers();
        start.setOnClickListener(v -> handleStartStop());
        newgame.setOnClickListener(v -> resetGame());
        scorePage.setOnClickListener(v -> openScorePage());
        exit.setOnClickListener(v -> showExitDialog());

    }


    private void initNumbers() {
        n1.setText(String.valueOf(rand.nextInt(40) + 1));
        n2.setText(String.valueOf(rand.nextInt(40) + 1));
        n3.setText(String.valueOf(rand.nextInt(40) + 1));
        n4.setText(String.valueOf(rand.nextInt(40) + 1));
        n5.setText(String.valueOf(rand.nextInt(40) + 1));
        n6.setText(String.valueOf(rand.nextInt(40) + 1));
        random.setText("0");
        points.setText("0 out of 6");
        start.setText("start");
        start.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));

        clearHighlights();
        isRunning = false;
        count = 0;
    }


    private void handleStartStop() {

        if (!isRunning && count < 6) {

            isRunning = true;
            count++;
            start.setText("stop");
            start.setBackgroundTintList(ColorStateList.valueOf(Color.RED));

            new Thread(() -> {
                while (isRunning) {
                    int num = rand.nextInt(40) + 1;

                    runOnUiThread(() -> random.setText(String.valueOf(num)));

                    try {
                        Thread.sleep(400);
                    } catch (Exception ignored) { }
                }
            }).start();

        } else {

            isRunning = false;
            start.setText("start");
            start.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));

            checkMatch();
        }
    }


    private void checkMatch() {
        String drawn = random.getText().toString();
        boolean matchedOnce = false;

        TextView[] arr = {n1, n2, n3, n4, n5, n6};

        for (TextView t : arr) {
            if (t.getText().toString().equals(drawn)) {
                t.setBackgroundColor(Color.RED);
                points.setText(count + " out of 6");
            }

        }

        if (matchedOnce) {
            count++;
            Pubcounttrue++;
        }
    }


    private void resetGame() {
        if (count >= 6) {
            initNumbers();
            gamePlayed++;
            count=0;
        }
    }


    private void clearHighlights() {
        n1.setBackgroundColor(Color.TRANSPARENT);
        n2.setBackgroundColor(Color.TRANSPARENT);
        n3.setBackgroundColor(Color.TRANSPARENT);
        n4.setBackgroundColor(Color.TRANSPARENT);
        n5.setBackgroundColor(Color.TRANSPARENT);
        n6.setBackgroundColor(Color.TRANSPARENT);
    }


    private void openScorePage() {
        Intent score = new Intent(MainActivity.this, ScoreActivity.class);
        score.putExtra("NAME", nameInput.getText().toString());
        startActivity(score);
    }

public void login() {
    Dialog d = new Dialog(this);
    d.setContentView(R.layout.welcome_dialog);
    Button enter = d.findViewById(R.id.enter);
    TextView age = d.findViewById(R.id.age);
    nameInput = d.findViewById(R.id.name);
    d.show();
    enter.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (!(nameInput.getText().toString().isEmpty()) || !(age.getText().toString().isEmpty())) {
                d.dismiss();
                logindits.setText(nameInput+","+age);
            }

        }
    });
}
    private void showExitDialog() {
        dialog.setTitle("EXIT?");
        dialog.setMessage("Are you sure?");
        dialog.setIcon(R.drawable.alert);
        dialog.setCancelable(false);

        dialog.setPositiveButton("Yes", (dialog, which) -> {
            finishAffinity();
            dialog.dismiss();
        });

        dialog.setNegativeButton("No", (dialog, which) -> dialog.dismiss());

        dialog.show();
    }
}
