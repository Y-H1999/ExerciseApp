package com.example.bottomnav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AbsExActivity extends AppCompatActivity
implements AdapterView.OnItemClickListener {

    private String[] menus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abs_ex);

        menus = getResources().getStringArray(R.array.array_abs);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(
                        AbsExActivity.this,
                        R.layout.listitem,
                        R.id.abs_text,
                        menus
                );

        ListView listView = (ListView) findViewById(R.id.abs_listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(
                this.getApplicationContext(), AbsDetailActivity.class
        );
        startActivity(intent);
    }
}