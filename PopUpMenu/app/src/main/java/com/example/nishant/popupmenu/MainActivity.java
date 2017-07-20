package com.example.nishant.popupmenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((ImageButton)findViewById(R.id.image_button)).setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                        popupMenu.inflate(R.menu.popup_menu);
                        popupMenu.setOnMenuItemClickListener(
                                new PopupMenu.OnMenuItemClickListener(){
                                    @Override
                                    public boolean onMenuItemClick(MenuItem menuItem){
                                        switch (menuItem.getItemId()){
                                            case R.id.popup_next:
                                                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                                                startActivity(intent);
                                                return true;
                                            case R.id.popup_previous:
                                                Toast.makeText(MainActivity.this, "There is no going back", Toast.LENGTH_SHORT).show();
                                                return true;
                                            default:
                                                return false;
                                        }
                                    }
                                }
                        );
                        popupMenu.show();
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.menu_about_me:
                Toast.makeText(getApplicationContext(), "Nishant Bhakta", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }
}
