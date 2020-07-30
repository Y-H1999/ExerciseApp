package com.example.bottomnav.list;

import androidx.appcompat.app.AppCompatActivity;

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
import com.example.bottomnav.makeset.ChoiceParts;
import com.example.bottomnav.others.Global;

import java.util.ArrayList;

import static com.example.bottomnav.list.AbsExercise.convertArrayToString;
import static com.example.bottomnav.list.AbsExercise.insertData;

public class BicepsExercise extends AppCompatActivity {
    private ListView bicepsList;
    private String[] list;
    private Button backButton;
    private Button okButton;
    private Global global;
    private ContentsHelper helper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercisebiceps);

        global = (Global) this.getApplication();

        bicepsList = findViewById(R.id.exerciseList_biceps);
        list = getResources().getStringArray(R.array.array_biceps);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, list);

        bicepsList.setAdapter(adapter);
        bicepsList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        backButton = findViewById(R.id.biceps_back);
        okButton = findViewById(R.id.biceps_ok);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SparseBooleanArray checked = bicepsList.getCheckedItemPositions();
                ArrayList<String> check = new ArrayList<>();
                String str;

                for(int i = 0; i <= list.length; i++) {
                    if (checked.get(i) == true) {
                        check.add(list[i]);
                    }
                }

                str = convertArrayToString(check);

                if (global.getMenu() == null) {
                    global.setMenu(str);
                } else {
                    global.setMenu(global.getMenu() + "," + str);
                }
                Intent i = new Intent(BicepsExercise.this, ChoiceParts.class);
                startActivity(i);
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SparseBooleanArray checked = bicepsList.getCheckedItemPositions();
                ArrayList<String> check = new ArrayList<>();
                String str;

                for(int i = 0; i <= list.length; i++) {
                    if (checked.get(i) == true) {
                        check.add(list[i]);
                    }
                }

                str = convertArrayToString(check);

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

                Log.e("testes", global.getMenu());

                global.setMenu("");

                Log.e("testes2", global.getMenu());


                Intent i = new Intent(BicepsExercise.this, MainActivity.class);
                startActivity(i);

                global.setMenu(null);
            }
        });
    }
}