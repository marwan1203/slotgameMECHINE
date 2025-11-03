package com.example.slotgame;



import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView n1, n2, n3, n4, n5, n6, random, numbers;
    private Button start, newgame;
    private boolean isRunning;
    private Random rand = new Random();
    private int count=0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        n1 = findViewById(R.id.num1);
        n2 = findViewById(R.id.num2);
        n3 = findViewById(R.id.num3);
        n4 = findViewById(R.id.num4);
        n5 = findViewById(R.id.num5);
        n6 = findViewById(R.id.num6);
        numbers = findViewById(R.id.points);
        random = findViewById(R.id.randNum);
        newgame = findViewById(R.id.newgame);
        start = findViewById(R.id.startstop);
        n1.setText(String.valueOf(rand.nextInt(40) + 1));
        n2.setText(String.valueOf(rand.nextInt(40) + 1));
        n3.setText(String.valueOf(rand.nextInt(40) + 1));
        n4.setText(String.valueOf(rand.nextInt(40) + 1));
        n5.setText(String.valueOf(rand.nextInt(40) + 1));
        n6.setText(String.valueOf(rand.nextInt(40) + 1));
        random.setText("0");
        numbers.setText("");
        start.setText("start");
        isRunning = false;
        newgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count>5){
                    n1.setText(String.valueOf(rand.nextInt(40) + 1));
                    n2.setText(String.valueOf(rand.nextInt(40) + 1));
                    n3.setText(String.valueOf(rand.nextInt(40) + 1));
                    n4.setText(String.valueOf(rand.nextInt(40) + 1));
                    n5.setText(String.valueOf(rand.nextInt(40) + 1));
                    n6.setText(String.valueOf(rand.nextInt(40) + 1));
                    random.setText("0");
                    numbers.setText("");
                    start.setText("start");
                    isRunning = false;
                    count=0;
                    n1.setBackgroundColor(0);
                    n2.setBackgroundColor(0);
                    n3.setBackgroundColor(0);
                    n4.setBackgroundColor(0);
                    n5.setBackgroundColor(0);
                    n6.setBackgroundColor(0);
                }
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isRunning&&count<6) {
                    count++;
                    isRunning = true;
                    start.setText("stop");
                    start.setBackgroundTintList(ColorStateList.valueOf(Color.RED));


                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (isRunning) {
                                final int num = rand.nextInt(40) + 1;


                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        random.setText(String.valueOf(num));
                                    }
                                });

                                try {
                                    Thread.sleep(100); // change number every 100ms
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).start();

                } else {
                    isRunning = false;
                    start.setText("start");
                    start.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));


                    String drawn = random.getText().toString();

                    if (n1.getText().toString().equals(drawn)){
                        numbers.setText(numbers.getText()+","+n1.getText());
                        n1.setBackgroundColor(0xffff0000);

                    }

                    if (n2.getText().toString().equals(drawn)){
                        numbers.setText(numbers.getText()+","+n2.getText());
                        n2.setBackgroundColor(0xffff0000);
                    }

                    if (n3.getText().toString  ().equals(drawn)){
                        numbers.setText(numbers.getText()+","+n3.getText());
                        n3.setBackgroundColor(0xffff0000);
                    }

                    if (n4.getText().toString().equals(drawn)){
                        numbers.setText(numbers.getText()+","+n4.getText());
                        n4.setBackgroundColor(0xffff0000);
                    }

                    if (n5.getText().toString().equals(drawn)){
                        numbers.setText(numbers.getText()+","+n5.getText());
                        n5.setBackgroundColor(0xffff0000);
                    }

                    if (n6.getText().toString().equals(drawn)){
                        numbers.setText(numbers.getText()+","+n6.getText());
                        n6.setBackgroundColor(0xffff0000);
                    }

                }
            }
        });
    }
}
