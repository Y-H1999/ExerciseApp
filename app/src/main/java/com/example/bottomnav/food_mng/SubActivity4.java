package com.example.bottomnav.food_mng;


import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bottomnav.R;
import com.example.bottomnav.others.Global;


public class SubActivity4 extends AppCompatActivity {

    SQLiteDatabase db;
    FoodNameHelper helper;
    Global global;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        global = (Global) this.getApplication();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub4_1);

        final EditText editText = findViewById(R.id.editText);

        Button sendButton = findViewById(R.id.send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), SubActivity.class);
                intent.putExtra("keyword",editText.getText().toString());


                //DB処理
                if (helper == null) {
                    helper = new FoodNameHelper(SubActivity4.this);
                }

                try (SQLiteDatabase db = helper.getWritableDatabase()) {
                    // rawQueryというSELECT専用メソッドを使用してデータを取得する
                    if (!editText.getText().toString().equals("")){
                        insertData(db,global.getTestString(),editText.getText().toString());
                    }else {
                        Toast.makeText(SubActivity4.this,"Foodを入力して下さい",Toast.LENGTH_SHORT).show();
                        }

                }

                startActivity(intent);

            }
        });
    }
    private void insertData(SQLiteDatabase db,String date, String f_name){

        ContentValues values = new ContentValues();
        values.put("date", date);
        values.put("name", f_name);
        db.insert("fooddb", null, values);
    }
}
