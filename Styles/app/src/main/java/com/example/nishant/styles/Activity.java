package com.example.nishant.styles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class Activity extends AppCompatActivity {

    Button clickMeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_);

        clickMeButton = (Button)findViewById(R.id.myButtonId);
    }
}