package com.example.nishant.runtimemenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import static android.view.View.generateViewId;

public class MainActivity extends AppCompatActivity {

    private boolean showMenuItem = false;
    private final int MENU_DOWNLOAD_ID = generateViewId();
    private final int MENU_ABOUT_ID = generateViewId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(0, MENU_DOWNLOAD_ID, 0, R.string.menu_download);
        menu.add(0, MENU_ABOUT_ID, 0, R.string.menu_About);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        MenuItem menuItem = menu.findItem(MENU_DOWNLOAD_ID);
        menuItem.setVisible(showMenuItem);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        int i = menuItem.getItemId();
        if (i == MENU_DOWNLOAD_ID) {
            Toast.makeText(MainActivity.this, "Clicked Download", Toast.LENGTH_SHORT).show();

        } else if (i == MENU_ABOUT_ID) {
            Toast.makeText(MainActivity.this, "Clicked About Me", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(intent);
        }else {
            super.onOptionsItemSelected(menuItem);
        }
        return true;
    }

    public void showDownload(View view){
        showMenuItem = !showMenuItem;
        invalidateOptionsMenu();
    }
}