package com.example.nishant.runtimewidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nispok.snackbar.Snackbar;


public class Main extends AppCompatActivity {

    RelativeLayout parentLayout;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        parentLayout = new RelativeLayout(this);
        textView = new TextView(this);

        setContentView(parentLayout);

        parentLayout.addView(textView);

        textView.setText("This view is generating within Runtime. " +
                "Click anywher to open snackbar. " +
                "To get this snackbar add this line in 'build.gradle' dependencies : \n" +
                "compile 'com.nispok:snackbar:2.11.+'");
        textView.setTextSize(25);

        textView.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        Snackbar.with(Main.this).text("This is Snackbar").show(Main.this);
                    }
                }
        );
    }
}