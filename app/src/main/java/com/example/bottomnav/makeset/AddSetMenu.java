package com.example.bottomnav.makeset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bottomnav.R;
import com.example.bottomnav.helper.MenuHelper;

import java.util.ArrayList;

public class AddSetMenu extends AppCompatActivity {
    private ListView setList;
    private MenuHelper helper;
    private SQLiteDatabase db;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> setNames;
    private TextView titleText;
    private static final String PASS_STR = "passString";
    private static final String PASS_STR2 = "passString2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_setmenu);

        titleText = (TextView) findViewById(R.id.setName_text);
        titleText.setText("セットメニュー一覧");


        setNames = new ArrayList<>();

        readData();

        Log.e("TEST", String.valueOf(setNames));

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, setNames);

        setList = findViewById(R.id.set_listView);
        setList.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        setList.setOnItemClickListener(onItemClickListener);

    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(
                    getApplicationContext(),
                    MySetMenu.class
            );
            intent.putExtra(PASS_STR, (String) parent.getItemAtPosition(position));
            intent.putExtra(PASS_STR2, "セットメニュー一覧");
            startActivity(intent);
        }
    };

    private void readData() {
        if (helper == null) {
            helper = new MenuHelper(getApplicationContext());
        }

        if (db == null) {
            db = helper.getReadableDatabase();
        }

        Cursor c = db.query("menudb",
                new String[] {"setmenu"},
                null,
                null,
                null,
                null,
                null);

        c.moveToFirst();
        for (int i = 0; i < c.getCount(); i++) {
            setNames.add(c.getString(0));
            c.moveToNext();
        }

        c.close();

    }
}