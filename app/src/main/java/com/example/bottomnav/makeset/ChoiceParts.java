package com.example.bottomnav.makeset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.bottomnav.R;
import com.example.bottomnav.list.AbsExercise;
import com.example.bottomnav.list.BicepsExercise;
import com.example.bottomnav.list.TricepsExercise;
import com.example.bottomnav.others.Global;

import java.util.List;

public class ChoiceParts extends AppCompatActivity {
    private Button abs;
    private Button biceps;
    private Button triceps;
    private Button forearm;
    private Button spine;
    private Button chest;
    private Button shoulder;
    private Button front_femur;
    private Button back_femur;
    private Button lower_leg;
    private Button other;
    private ListView menuList;
    private Global global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_parts);

        findViews();

        String str = global.getMenu();

        if (str != null) {
            List<String> list = AbsExercise.convertStringToArray(str);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
            menuList.setAdapter(adapter);
        }



        abs.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(ChoiceParts.this, AbsExercise.class);
                        startActivity(i);
                    }
                }
        );
        biceps.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(ChoiceParts.this, BicepsExercise.class);
                        startActivity(i);
                    }
                }
        );
        triceps.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(ChoiceParts.this, TricepsExercise.class);
                        startActivity(i);
                    }
                }
        );
        forearm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent i = new Intent(ChoiceParts.this, ForearmExercise.class);
                    }
                }
        );
        spine.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent i = new Intent(ChoiceParts.this, SpineExercise.class);
                    }
                }
        );
        chest.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent i = new Intent(ChoiceParts.this, ChestExercise.class);
                    }
                }
        );
        shoulder.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent i = new Intent(ChoiceParts.this, ShoulderExercise.class);
                    }
                }
        );
        front_femur.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent i = new Intent(ChoiceParts.this, FrontFemurExercise.class);
                    }
                }
        );
        back_femur.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent i = new Intent(ChoiceParts.this, BackFemurExercise.class);
                    }
                }
        );
        lower_leg.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent i = new Intent(ChoiceParts.this, LowerLegExercise.class);
                    }
                }
        );
        other.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent i = new Intent(ChoiceParts.this, OtherExercise.class);
                    }
                }
        );

    }

    public void findViews() {
        abs = findViewById(R.id.abs_btn);
        biceps = findViewById(R.id.biceps_btn);
        triceps = findViewById(R.id.triceps_btn);
        forearm = findViewById(R.id.forearm_btn);
        spine = findViewById(R.id.spine_btn);
        chest = findViewById(R.id.chest_btn);
        shoulder = findViewById(R.id.shoulder_btn);
        front_femur = findViewById(R.id.front_femur_btn);
        back_femur = findViewById(R.id.back_femur_btn);
        lower_leg = findViewById(R.id.lower_leg_btn);
        other = findViewById(R.id.other_btn);
        menuList = findViewById(R.id.show_list);
        global = (Global) this.getApplication();
    }
}