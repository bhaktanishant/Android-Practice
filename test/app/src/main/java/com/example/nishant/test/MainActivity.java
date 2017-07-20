package com.example.nishant.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    public static final String REQUEST_RESULT = "REQUEST_RESULT";
    public static final String KEY_COUNTER = "COUNTER";
    private static int counter = 0;
    private TextView textView1, textView2;
    private static String finalText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView) findViewById(R.id.textView2);
        textView1.setText(Integer.toString(counter));
        textView2 = (TextView) findViewById(R.id.textView1);
        textView2.setText(finalText);
    }

    public void startCounter(View view){
        counter++;
        textView1.setText(Integer.toString(counter));
    }

    public void onClick(View view){
        Intent intent = new Intent(this, secondActivity.class);
        EditText editText = (EditText) findViewById(R.id.text);
        String text = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, text);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int comingFrom, int resultCode, Intent data){
        super.onActivityResult(comingFrom, resultCode, data);
        if (resultCode == RESULT_OK){
            finalText = Integer.toString(comingFrom) + Integer.toString(data.getIntExtra(REQUEST_RESULT, 0)) + resultCode;
            textView2.setText(finalText);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_COUNTER, counter);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        counter = savedInstanceState.getInt(KEY_COUNTER);
    }
}