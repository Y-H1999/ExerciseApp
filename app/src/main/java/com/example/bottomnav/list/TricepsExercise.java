package com.example.bottomnav.list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.bottomnav.MainActivity;
import com.example.bottomnav.R;
import com.example.bottomnav.makeset.ChoiceParts;

public class TricepsExercise extends AppCompatActivity {
    private ListView tricepsList;
    private String[] list;
    private Button backButton;
    private Button okButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercisetriceps);

        tricepsList = findViewById(R.id.exerciseList_triceps);
        list = getResources().getStringArray(R.array.array_triceps);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, list);

        tricepsList.setAdapter(adapter);
        tricepsList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        backButton = findViewById(R.id.triceps_back);
        okButton = findViewById(R.id.triceps_ok);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TricepsExercise.this, ChoiceParts.class);
                startActivity(i);
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TricepsExercise.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}