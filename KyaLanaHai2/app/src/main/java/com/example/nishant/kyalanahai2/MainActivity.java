package com.example.nishant.kyalanahai2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private static List<String> itemStateList = new ArrayList<>();
    private ListView listView;
    private static List<String> listArray = new ArrayList<>();
    SharedPreferences settings;
    private SaveListInSharedPreferences saveListInSharedPreferences = new SaveListInSharedPreferences();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settings = getSharedPreferences("Saved_value", MODE_PRIVATE);
        String savedData = settings.getString("saveString", "");
        String savedStateData = settings.getString("stateSaveString", "");
        if (!savedData.isEmpty()){
            listArray = saveListInSharedPreferences.getList(savedData);
            itemStateList = saveListInSharedPreferences.getList(savedStateData);
        }

        listView = (ListView)findViewById(android.R.id.list);
        Button button = (Button)findViewById(R.id.addButton);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditText itemNameView = (EditText)findViewById(R.id.itemName);
                String itemName = itemNameView.getText().toString();
                if (!itemName.isEmpty()){
                    listArray.add(itemName);
                    itemStateList.add("default");
                }
                if (!listArray.isEmpty()) {
                    createAndSetAdapter();
                }
                itemNameView.setText(null);
            }
        });

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                        final PopupMenu popupMenu = new PopupMenu(getApplicationContext(), view);
                        popupMenu.inflate(R.menu.popup_menu);
                        popupMenu.setOnMenuItemClickListener(
                                new PopupMenu.OnMenuItemClickListener() {
                                    @Override
                                    public boolean onMenuItemClick(MenuItem menuItem) {
                                        switch (menuItem.getItemId()){
                                            case R.id.popup_menu_mil_gaya:
                                                view.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.mil_gaya));
                                                itemStateList.remove(position);
                                                itemStateList.add(position, "mil_gaya");
                                                return true;
                                            case R.id.popup_menu_nahi_mila:
                                                view.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.nahi_mila));
                                                itemStateList.remove(position);
                                                itemStateList.add(position, "nahi_mila");
                                                return true;
                                            case R.id.popup_menu_delete_saamaan:
                                                listArray.remove(position);
                                                itemStateList.remove(position);
                                                createAndSetAdapter();
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

    public void createAndSetAdapter(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_view, listArray);
        listView.setAdapter(adapter);
        Log.d(listArray.toString(), itemStateList.toString());
        for(int i = 0; i < listArray.size(); i++){
            switch (itemStateList.get(i)){
                case "mil_gaya":
                    Log.d("   show view ", ((TextView)listView.getAdapter().getView(i, null, listView)).getText().toString());
                    listView.getAdapter().getView(i, null, listView).setBackgroundColor(Color.parseColor("#AAFF7F"));
                    break;
                case "nahi_mila":
                    Log.d("    case :", " nahi_mila");
                    listView.getAdapter().getView(i, null, listView).setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.nahi_mila));
                    break;
                default:
                    Log.d("    case :", " default");
                    break;
            }
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        if (listArray != null){
            String listAsString = saveListInSharedPreferences.putList(listArray);
            String stateListAsString = saveListInSharedPreferences.putList(itemStateList);
            SharedPreferences.Editor editor = settings.edit();
            editor.clear();
            editor.putString("saveString", listAsString);
            editor.putString("stateSaveString", stateListAsString);
            editor.commit();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        if (!listArray.isEmpty() && listArray != null) {
            createAndSetAdapter();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        if (menuItem.getItemId() == R.id.main_menu_creator){
            Intent intent = new Intent(this, AboutMeActivity.class);
            startActivity(intent);
            return true;
        }else if(menuItem.getItemId() == R.id.clearButton){
            listArray.clear();
            itemStateList.clear();
            listView.setAdapter(null);
            return true;
        }else {
            return false;
        }
    }
}