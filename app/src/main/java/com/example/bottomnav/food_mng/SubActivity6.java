package com.example.bottomnav.food_mng;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bottomnav.R;
import com.example.bottomnav.others.Global;


public class SubActivity6 extends AppCompatActivity {
    private Global global;
    private KcalHelper helper;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        global = (Global) this.getApplication();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub6);

        final EditText editText = findViewById(R.id.editText);
        TextView tv1 = findViewById(R.id.type);
        Intent intentfm5 = getIntent();
        type = intentfm5.getStringExtra("keyword0");
        tv1.setText(type);

        Button sendButton = findViewById(R.id.send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { Intent intent = new Intent(getApplication(), SubActivity.class);
                intent.putExtra("keyword5",editText.getText().toString());

                //DB処理
                    if (helper == null) {
                        helper = new KcalHelper(SubActivity6.this);
                    }

                    try (SQLiteDatabase db = helper.getWritableDatabase()) {
                        // rawQueryというSELECT専用メソッドを使用してデータを取得する
                        if (!editText.getText().toString().equals("")){
                            insertData(db,global.getTestString(),editText.getText().toString(), type);
                        }else {
                            Toast.makeText(SubActivity6.this,"Foodを入力して下さい",Toast.LENGTH_SHORT).show();
                        }

                    }

                    startActivity(intent);

                }
            });
        }


        private void insertData(SQLiteDatabase db,String date, String f_name, String type){

            ContentValues values = new ContentValues();
            values.put("date", date);
            values.put("name", f_name);
            values.put("type", type);
            db.insert("kcaldb", null, values);
        }
    }
