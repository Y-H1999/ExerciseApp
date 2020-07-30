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
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.bottomnav.R;
import com.example.bottomnav.helper.ContentsHelper;
import com.example.bottomnav.helper.MCHelper;
import com.example.bottomnav.helper.MenuHelper;
import com.example.bottomnav.list.AbsExercise;
import com.example.bottomnav.others.Global;

import java.util.List;

public class MySetMenu extends AppCompatActivity {
    private Global global;
    private MenuHelper menuHelper;
    private MCHelper mcHelper;
    SQLiteDatabase db;
    SQLiteDatabase menudb;
    ListView setList2;
    String contents;
    TextView tv;
    private Button start_btn;
    private NumberPicker minutePicker;
    private NumberPicker secondPicker;
    private int minute;
    private int second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myset_menu);

        minutePicker = findViewById(R.id.minute);
        secondPicker = findViewById(R.id.second);
        tv = findViewById(R.id.setmenu_text1);
        start_btn = findViewById(R.id.button_start);
        global = (Global) this.getApplication();

        minutePicker.setMaxValue(59);
        secondPicker.setMaxValue(59);
        minutePicker.setMinValue(0);
        minutePicker.setMinValue(0);

        readMCData();

        List<String> list = AbsExercise.convertStringToArray(contents);

        setList2 = findViewById(R.id.setList2);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, list);

        setList2.setAdapter(adapter);
        setList2.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        global.setPlay((String) setList2.getItemAtPosition(position));
                        Intent i = new Intent(MySetMenu.this, ExerciseSetting.class);
                        startActivity(i);
                    }
                }
        );

        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                minute = minutePicker.getValue();
                second = secondPicker.getValue();

                global.setIntervalTime(totalTime(minute, second));


                Intent i = new Intent(MySetMenu.this, SetMenuTime.class);
                startActivity(i);

                Log.e("Interval", String.valueOf(global.getIntervalTime()));
                Log.e("Interval", String.valueOf(totalTime(minute, second)));
                Log.e("Interval", String.valueOf(minute));
            }
        });
    }


    /*private void readData() {
        if (mcHelper == null) {
            mcHelper = new MCHelper(getApplicationContext());
        }

        if (db == null) {
            db = mcHelper.getReadableDatabase();
        }

        Cursor c = db.query("mcdb",
                new String[] {"setcontents"},
                null,
                null,
                null,
                null,
                null);

        c.moveToFirst();
        Log.e("myset", String.valueOf(global.getSelectedPosition()));
        for (int i = 0; i < global.getSelectedPosition(); i++) {
            c.moveToNext();
        }

        contents = c.getString(1);

        Log.e("myset", contents);

        c.close();

        Log.e("contents", contents);
    }*/

    public void readMCData() {
        if (mcHelper == null) {
            mcHelper = new MCHelper(getApplicationContext());
        }

        if (db == null) {
            db = mcHelper.getReadableDatabase();
        }

        Cursor c = db.query("mcdb",
                new String[] {"setmenu", "setcontents"},
                null,
                null,
                null,
                null,
                null);

        c.moveToFirst();
        for (int i = 0; i < global.getSelectedPosition(); i++) {
            c.moveToNext();
        }

        String str = c.getString(0);
        contents =  c.getString(1);

        tv.setText(str);

        c.close();

    }


    public int totalTime(int m, int s) {
        return ((m * 60) + s) * 1000;}
}