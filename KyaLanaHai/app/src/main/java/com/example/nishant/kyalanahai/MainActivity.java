package com.example.nishant.kyalanahai;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    public static String KEY_COUNTER = "ItemList";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.itemList);
        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        textView.setText(settings.getString(KEY_COUNTER, ""));
    }

    public void addItem(View view){
        EditText itemNameTextEdit = (EditText) findViewById(R.id.enterName);
        EditText quantityTextEdit = (EditText) findViewById(R.id.quantity);
        String itemName = itemNameTextEdit.getText().toString();
        String quantity = quantityTextEdit.getText().toString();
        if (!itemName.isEmpty()){
            textView.append(itemName + " -- " + quantity + "\n");
        }else {
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context, "Naam Likho Pahle", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void clearList(View view){
        textView.setText("");
    }

    @Override
    protected void onPause(){
        super.onPause();
        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(KEY_COUNTER, textView.getText().toString());
        editor.commit();
    }
}