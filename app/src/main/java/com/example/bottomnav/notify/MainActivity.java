package com.example.bottomnav.notify;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bottomnav.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main1);  //レイアウトファイルをコンテントビューとしてセット

        Button sendButton = findViewById(R.id.send_btn);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplication(), SubActivity.class);  //インテント作成
                startActivity(intent);
            }
        });

        findViewById(R.id.setbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), com.example.bottomnav.setting.MainActivity.class);  //インテント作成
                startActivity(intent);
            }
        });

        findViewById(R.id.button9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), com.example.bottomnav.food_mng.HomeActivity.class);  //インテント作成
                startActivity(intent);
            }
        });

    }


}
