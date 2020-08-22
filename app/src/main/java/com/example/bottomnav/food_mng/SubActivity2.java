package com.example.bottomnav.food_mng;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.bottomnav.R;

import java.util.ArrayList;


public class SubActivity2 extends AppCompatActivity {
    private ArrayList<String> data = new ArrayList<String>();

    String[] a = new String[]{"100"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2a);

        data.add("PFCバランス型");
        data.add("ローファット型");
        data.add("ローカーボ型");
        data.add("ケトジェニック型");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);

        ListView listView = (ListView)findViewById(R.id.mysetlistview2);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               if (position == 0) {
                   Intent intent1 = new Intent(getApplication(), SubActivity6.class);
                   intent1.putExtra("keyword0",data.get(0));
                   startActivity(intent1);
               } else if (position == 1) {
                   Intent intent2 = new Intent(getApplication(), SubActivity6.class);
                   intent2.putExtra("keyword0",data.get(1));
                   startActivity(intent2);
               } else if (position == 2){
                   Intent intent3 = new Intent(getApplication(), SubActivity6.class);
                   intent3.putExtra("keyword0",data.get(2));
                   startActivity(intent3);
           }else{
                   Intent intent4 = new Intent(getApplication(), SubActivity6.class);
                   intent4.putExtra("keyword0",data.get(3));
                   startActivity(intent4);
        }
            }
        });

        Button sendButton = findViewById(R.id.button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), SubActivity5.class);
                startActivity(intent);
            }
        });
    }

    private void insertData(SQLiteDatabase db,String date, String f_name){

        ContentValues values = new ContentValues();
        values.put("date", date);
        values.put("food_name", f_name);
        db.insert("fooddb", null, values);
    }
}