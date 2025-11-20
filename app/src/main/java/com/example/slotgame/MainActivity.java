package com.example.slotgame;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    public static int Pubcounttrue = 0;
    public static int gamePlayed = 0;

    private EditText nameInput;
    private TextView n1, n2, n3, n4, n5, n6, random, numbers;
    private Button start, newgame, scorePage, go, exit;

    private boolean isRunning = false;
    private int count = 0;
    private int counttrue = 0;

    private final Random rand = new Random();
    private final Handler handler = new Handler();

    private Intent scoreIntent;
    private Runnable numberGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreIntent = new Intent(MainActivity.this, ScoreActivity.class);

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
        go = findViewById(R.id.sendName);
        exit = findViewById(R.id.exit);

        resetGameUI();

        exit.setOnClickListener(view -> createExitDialog());

        go.setOnClickListener(view -> {
            String name = nameInput.getText().toString().trim();
            if (!name.isEmpty()) {
                scoreIntent.putExtra("NAME", name);
                nameInput.setEnabled(false);
            }
        });

        scorePage.setOnClickListener(v -> startActivity(scoreIntent));

        newgame.setOnClickListener(v -> {
            gamePlayed++;
            resetGameUI();
        });

        start.setOnClickListener(v -> {
            if (!isRunning && count < 6) {
                startRandomGenerator();
            } else {
                stopRandomGenerator();
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
    }

public void createExitDialog(){
    Dialog d=new Dialog(this);
    d.setContentView(R.layout.my_dialog);
    Button yes=d.findViewById(R.id.yes);
    Button no=d.findViewById(R.id.no);

}

    private void startRandomGenerator() {
        isRunning = true;
        count++;
        start.setText("Stop");
        start.setBackgroundColor(Color.RED);
        handler.post(numberGenerator);
    }

    private void stopRandomGenerator() {
        if (!isRunning) return;

        isRunning = false;
        handler.removeCallbacks(numberGenerator);

        start.setText("Start");
        start.setBackgroundColor(Color.parseColor("#008000"));

        String drawnValue = random.getText().toString();
        checkMatch(n1, drawnValue);
        checkMatch(n2, drawnValue);
        checkMatch(n3, drawnValue);
        checkMatch(n4, drawnValue);
        checkMatch(n5, drawnValue);
        checkMatch(n6, drawnValue);

        numbers.setText(counttrue + " of 6");

        if (count >= 6) {
            start.setEnabled(false);
            newgame.setEnabled(true);
        }
    }

    private void checkMatch(TextView tv, String value) {
        if (tv.getText().toString().equals(value) && tv.getCurrentTextColor() != Color.RED) {
            counttrue++;
            Pubcounttrue = counttrue;
            tv.setTextColor(Color.RED);
        }
    }

    private void generateNewNumbers() {
        Set<Integer> uniqueNumbers = new HashSet<>();
        while (uniqueNumbers.size() < 6) {
            uniqueNumbers.add(rand.nextInt(40) + 1);
        }

        Integer[] numArray = uniqueNumbers.toArray(new Integer[0]);
        n1.setText(String.valueOf(numArray[0]));
        n2.setText(String.valueOf(numArray[1]));
        n3.setText(String.valueOf(numArray[2]));
        n4.setText(String.valueOf(numArray[3]));
        n5.setText(String.valueOf(numArray[4]));
        n6.setText(String.valueOf(numArray[5]));
    }

    private void resetGameUI() {
        count = 0;
        counttrue = 0;

        generateNewNumbers();

        random.setText("0");
        numbers.setText("0 of 6");

        start.setText("Start");
        start.setEnabled(true);
        start.setBackgroundColor(Color.parseColor("#008000"));

        newgame.setEnabled(false);

        n1.setTextColor(Color.TRANSPARENT);
        n2.setTextColor(Color.TRANSPARENT);
        n3.setTextColor(Color.TRANSPARENT);
        n4.setTextColor(Color.TRANSPARENT);
        n5.setTextColor(Color.TRANSPARENT);
        n6.setTextColor(Color.TRANSPARENT);
    }
}
