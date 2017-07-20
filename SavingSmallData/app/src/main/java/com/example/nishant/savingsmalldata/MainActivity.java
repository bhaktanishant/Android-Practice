package com.example.nishant.savingsmalldata;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public final String KEY_COUNTER = "Counter";
    private static int counter = 0;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        int defaultCounter = 0;
        counter = settings.getInt(KEY_COUNTER, defaultCounter);
        textView = (TextView) findViewById(R.id.textView1);
        addText();
    }

    public void startCounter(View view){
        counter++;
        addText();
    }

    private void addText(){
        textView.setText(Integer.toString(counter));
        textView.setTextSize(80);
    }

    @Override
    protected void onPause(){
        super.onPause();
        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(KEY_COUNTER, counter);
        editor.commit();
    }
}
