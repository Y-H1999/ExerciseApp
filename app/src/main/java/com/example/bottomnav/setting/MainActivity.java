package com.example.bottomnav.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bottomnav.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Buttonクリックで設定画面へ
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
            }
        });
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        // レイアウトルートの背景をテーマ設定の値によって変更
//        RelativeLayout root = findViewById(R.id.root);
//        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        switch (defaultSharedPreferences.getString("preference_theme", getString(R.string.default_value_preference_theme))) {
//            case "light":
//                root.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                break;
//            case "dark":
//                root.setBackgroundColor(Color.parseColor("#000000"));
//                break;
//        }
//
//    }



}