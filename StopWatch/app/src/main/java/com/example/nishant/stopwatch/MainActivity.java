package com.example.nishant.stopwatch;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean running;
    private boolean wasRunning;
    private int second;
    private TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        running = false;
        second = 0;

        Button start_button = (Button)findViewById(R.id.start);
        Button pause_button = (Button)findViewById(R.id.pause);
        Button reset_button = (Button)findViewById(R.id.reset);
        display = (TextView)findViewById(R.id.textView2);

        if (savedInstanceState != null){
            second = savedInstanceState.getInt("second");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }

        startTimer();

        start_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                running = true;
            }
        });

        pause_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;
            }
        });

        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;
                second = 0;
                display.setText(null);
                display.setHint("00:00:00");
            }
        });
    }

    protected void startTimer(){

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = second/3600;
                int minute = (second%3600)/60;
                int second_hand = second%60;
                String time = String.format("%02d:%02d:%02d", hours, minute, second_hand);

                if (!time.equals("00:00:00")){
                    display.setText(time);
                }

                if (running){
                    second++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    @Override
    protected void onPause(){
        super.onPause();
        wasRunning = running;
        running = false;
    }

    @Override
    protected void onResume(){
        super.onResume();
        if (wasRunning){
            running = true;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState){
        saveInstanceState.putInt("second", second);
        saveInstanceState.putBoolean("running", running);
        saveInstanceState.putBoolean("wasRunning", wasRunning);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        if (R.id.main_menu == menuItem.getItemId()){
            Toast.makeText(this, "Nishant Bhakta", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}