package com.example.nishant.contextualbatchmode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class N extends AppCompatActivity {

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        Button button = (Button)findViewById(R.id.button_id);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(((EditText) findViewById(R.id.edit_text_id)).getText().toString()).isEmpty()) {
                    adapter.add(((EditText) findViewById(R.id.edit_text_id)).getText().toString());
                    ((ListView) findViewById(R.id.list_view)).setAdapter(adapter);
                    ((EditText) findViewById(R.id.edit_text_id)).setText(null);
                }
            }
        });

        ((ListView) findViewById(R.id.list_view)).setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        ((ListView) findViewById(R.id.list_view)).setMultiChoiceModeListener(multiChoiceModeListener);

        ((ListView) findViewById(R.id.list_view)).setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ((ListView)parent).setItemChecked(position, true);
                    }
                }
        );
    }

    AbsListView.MultiChoiceModeListener multiChoiceModeListener = new AbsListView.MultiChoiceModeListener() {
        @Override
        public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater menuInflater = new MenuInflater(N.this);
            menuInflater.inflate(R.menu.contextual_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()){
                case R.id.contextual_done:
                    Toast.makeText(N.this, "Done", Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;
                case R.id.contextual_delete:
                    Toast.makeText(N.this, "Delete", Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.main_menu_about_me:
                Toast.makeText(this, "__/\\__Nishant Bhakta __/\\__", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
