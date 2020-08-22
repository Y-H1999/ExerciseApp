package com.example.bottomnav.food_mng;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bottomnav.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class OptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navi);

        bottomNavigationView.setSelectedItemId(R.id.dashbaord);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.dashbaord:
                        return true;
                    case R.id.food:
                        startActivity(new Intent(getApplicationContext(), SubActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.fitness:
                        startActivity(new Intent(getApplicationContext(), Fitness.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });
    }
}