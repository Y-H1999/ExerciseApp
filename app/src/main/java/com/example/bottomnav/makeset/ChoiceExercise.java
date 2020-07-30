package com.example.bottomnav.makeset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.bottomnav.R;

import java.util.ArrayList;

public class ChoiceExercise extends AppCompatActivity {

    private static ArrayList<String> RETURN = null;
    private ListView listView1;
    private ListView listView2;
    private ListView listView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_exercise);

        final String[] abs = getResources().getStringArray(R.array.array_abs);
        final String[] biceps = getResources().getStringArray(R.array.array_biceps);
//        final String[] triceps = getResources().getStringArray(R.array.array_triceps);
        listView1 = (ListView) findViewById(R.id.exerciseList_abs);
        listView2 = (ListView) findViewById(R.id.exerciseList_biceps);
        listView3 = (ListView) findViewById(R.id.exerciseList_triceps);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, abs);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, biceps);
//        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, triceps);

        listView1.setAdapter(adapter1);
        listView1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView2.setAdapter(adapter2);
        listView2.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
//        listView3.setAdapter(adapter3);
//        listView3.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        final Button okb = (Button) findViewById(R.id.ok_btn);

        okb.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SparseBooleanArray checked = listView1.getCheckedItemPositions();
                        Intent intent = getIntent();
                        ArrayList<String> strings = new ArrayList<String>();
                        for (int i = 0; i < checked.size(); i++) {
                            if (checked.get(i) == true) {
                                strings.add(abs[i]);
                            }
                        }
                        Log.i("TEST", String.valueOf(checked));
                        intent.putStringArrayListExtra(String.valueOf(RETURN), strings);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }
        );


    }
}