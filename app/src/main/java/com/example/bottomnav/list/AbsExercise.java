package com.example.bottomnav.list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.bottomnav.MainActivity;
import com.example.bottomnav.R;
import com.example.bottomnav.helper.ContentsHelper;
import com.example.bottomnav.helper.MCHelper;
import com.example.bottomnav.makeset.ChoiceParts;
import com.example.bottomnav.others.Global;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AbsExercise extends AppCompatActivity {
    private ListView absList;
    private String[] list;
    private Button backButton;
    private Button okButton;
    private ContentsHelper helper;
    private SQLiteDatabase db;
    private Global global;
    private MCHelper mcHelper;
    private SQLiteDatabase mcdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exerciseabs);

        global = (Global) this.getApplication();

        absList = findViewById(R.id.exerciseList_abs);
        list = getResources().getStringArray(R.array.array_abs);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, list);

        absList.setAdapter(adapter);
        absList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        backButton = findViewById(R.id.abs_back);
        okButton = findViewById(R.id.abs_ok);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SparseBooleanArray checked = absList.getCheckedItemPositions();
                ArrayList<String> check = new ArrayList<>();
                String str;

                for(int i = 0; i <= list.length; i++) {
                    if (checked.get(i) == true) {
                        check.add(list[i]);
                    }
                }

                if (check.size() > 1) {
                    str = convertArrayToString(check);
                } else {
                    str = null;
                }

                if (global.getMenu() == null) {
                    global.setMenu(str);
                } else {
                    global.setMenu(global.getMenu() + str);
                }


                List<String> arr = convertStringToArray(str);

                Log.e("Test", str);

                Log.e("Test", String.valueOf(arr));

//                if (helper == null) {
//                    helper = new ContentsHelper(getApplicationContext());
//                }
//
//                if (db == null) {
//                    db = helper.getWritableDatabase();
//                }
//
//                insertData(db, str);

                Intent i = new Intent(AbsExercise.this, ChoiceParts.class);
                startActivity(i);
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SparseBooleanArray checked = absList.getCheckedItemPositions();
                ArrayList<String> check = new ArrayList<>();
                String str = "";

                for(int i = 0; i <= list.length; i++) {
                    if (checked.get(i) == true) {
                        check.add(list[i]);
                    }
                }

                if (check.size() > 1) {
                    str = convertArrayToString(check);
                    Log.e("Test", str);
                } else {
                    str = null;
                }

//                List<String> arr = convertStringToArray(str);

//                Log.e("Test", String.valueOf(arr));

                if (global.getMenu() == null) {
                    global.setMenu(str);
                } else {
                    global.setMenu(global.getMenu() + "," + str);
                }

                if (helper == null) {
                    helper = new ContentsHelper(getApplicationContext());
                }

                if (db == null) {
                    db = helper.getWritableDatabase();
                }

                insertData(db, global.getMenu());

                if (mcHelper == null) {
                    mcHelper = new MCHelper(getApplicationContext());
                }

                if (mcdb == null) {
                    mcdb = mcHelper.getWritableDatabase();
                }

                insertData(mcdb, global.getConveyMenuName(), global.getMenu());

                Intent i = new Intent(AbsExercise.this, MainActivity.class);
                startActivity(i);
                global.setMenu(null);
            }
        });
    }

    public static String strSeparator = ",";

    public static String convertArrayToString(ArrayList<String> array) {
        StringBuilder sb = new StringBuilder();
        for (String str : array) {
            sb.append(str).append(strSeparator);
        }
        sb.setLength(sb.length() - strSeparator.length());
        return sb.toString();
    }

    public static List<String> convertStringToArray(String str) {
        return Arrays.asList(str.split(strSeparator));
    }

    public static void insertData(SQLiteDatabase db, String contents) {
        db.beginTransaction();

        try {
            ContentValues values = new ContentValues();
            values.put("setcontents", contents);
            db.insert("contentsdb", null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    public static void insertData(SQLiteDatabase db, String name, String contents) {
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put("setmenu", name);
            values.put("setcontents", contents);
            db.insert("mcdb", null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

}