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

public class SubActivity5 extends AppCompatActivity {
    SQLiteDatabase db;
    NutoritionHelper helper;
    Global global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub5);

        final EditText editText = findViewById(R.id.editText9);
        final EditText editText1 = findViewById(R.id.editText10);
        final EditText editText2 = findViewById(R.id.editText11);

        Button sendButton = findViewById(R.id.send_button2);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), SubActivity.class);
                intent.putExtra("keyword2",editText.getText().toString());
                intent.putExtra("keyword3",editText1.getText().toString());
                intent.putExtra("keyword4",editText2.getText().toString());
                //DB処理
                if (helper == null) {
                    helper = new NutoritionHelper(SubActivity5.this);
                }
                try (SQLiteDatabase db = helper.getWritableDatabase()) {
                    // rawQueryというSELECT専用メソッドを使用してデータを取得する
                    if (!editText.getText().toString().equals("")){
                        insertData(db,global.getTestString(),editText.getText().toString());
                        insertData(db,global.getTestString(),editText1.getText().toString());
                        insertData(db,global.getTestString(),editText2.getText().toString());
                    }else {
                        Toast.makeText(SubActivity5.this,"数値を入力して下さい",Toast.LENGTH_SHORT).show();
                    }
                startActivity(intent);
            }}
        });
    }

    private void insertData(SQLiteDatabase db, String date, String f_name){

        ContentValues values = new ContentValues();
        values.put("date", date);
        values.put("name", f_name);
        db.insert("fooddb", null, values);
    }
}
