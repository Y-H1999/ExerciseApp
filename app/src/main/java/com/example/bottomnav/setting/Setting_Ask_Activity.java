package com.example.bottomnav.setting;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.bottomnav.BuildConfig;
import com.example.bottomnav.R;

public class Setting_Ask_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_askform);

        Toolbar toolbar = findViewById(R.id.toolbar_ask_form);

        toolbar.setNavigationIcon(android.R.drawable.ic_menu_revert);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), SettingActivity.class);
                startActivity(intent);
            }

        });

        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mailerIntent = new Intent();

                EditText ed = findViewById(R.id.editTextTextPersonName2);

                mailerIntent.setAction(Intent.ACTION_SEND);
                mailerIntent.setType("message/rfc822");
                mailerIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"hagi5656yuu@icloud.com"});
                mailerIntent.putExtra(Intent.EXTRA_SUBJECT,"myApp：お問い合わせ");
                mailerIntent.putExtra(Intent.EXTRA_TEXT,
                        "\n"
                                + ed.getText()
                                + "\n\n"
                                + "Device : " + Build.BRAND+" "+ Build.MODEL
                                + "\nOS version : " + Build.VERSION.RELEASE
                                + "\nApp :myApp"
                                + "\nVersion : " + BuildConfig.VERSION_NAME);


                startActivity(mailerIntent);
            }
        });

    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            // 戻るボタンの処理
            Intent intent2 = new Intent(getApplication(), SettingActivity.class);
            startActivity(intent2);
        }
        return false;
    }

}