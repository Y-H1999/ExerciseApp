package com.example.bottomnav.setting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.bottomnav.R;


public class Setting_Pass_Activity extends AppCompatActivity {

    private SharedPreferences dataStore2;
    private EditText editText1,editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_pass);

        Toolbar toolbar = findViewById(R.id.toolbar_pass);

        toolbar.setNavigationIcon(android.R.drawable.ic_menu_revert);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), SettingActivity.class);
                startActivity(intent);
            }

        });


        // "DataStore"という名前でインスタンスを生成
        dataStore2 = getSharedPreferences("DataStore2", MODE_PRIVATE);

        editText1 = findViewById(R.id.pass1);
        editText2 = findViewById(R.id.pass2);

        Button buttonWrite = findViewById(R.id.button6);


        buttonWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // エディットテキストのテキストを取得
                String text1 = editText1.getText().toString();
                String text2 = editText2.getText().toString();

                if(!text1.equals("") && !text2.equals("") && text1.equals(text2)) {
                    Editor editor = dataStore2.edit();
                    editor.putString("pass1", text1);
                    editor.putString("pass2", text2);
                    editor.apply();

                    Intent intent = new Intent(getApplication(), SettingActivity.class);  //インテント作成
                    startActivity(intent);
                }else if(!text1.equals(text2)){
                    Toast.makeText(getApplication(), "パスコードが一致しません。", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplication(), "パスコードを入力してください。", Toast.LENGTH_LONG).show();
                }

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
