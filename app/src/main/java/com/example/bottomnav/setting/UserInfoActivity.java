package com.example.bottomnav.setting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.bottomnav.R;


public class UserInfoActivity extends AppCompatActivity {

    private SharedPreferences dataStore;
    private EditText editText1,editText2,editText3,editText4;
    private RadioGroup radioGroup;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_user_info);

        Toolbar toolbar = findViewById(R.id.toolbarh);

        toolbar.setNavigationIcon(android.R.drawable.ic_menu_revert);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), SettingActivity.class);
                startActivity(intent);
            }

        });


        // "DataStore"という名前でインスタンスを生成
        dataStore = getSharedPreferences("DataStore", MODE_PRIVATE);

        Button button = findViewById(R.id.button2);

        editText1 = findViewById(R.id.editTextTextPersonName);
        editText2 = findViewById(R.id.editTextTextPassword);
        editText3 = findViewById(R.id.editTextDate);
        editText4 = findViewById(R.id.editTextNumberDecimal);

        Button buttonWrite = findViewById(R.id.button4);

        radioGroup = (RadioGroup) findViewById(R.id.RadioGroup);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserInfoActivity.this, Mail_Change_Activity.class));
            }
        });

        buttonWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // エディットテキストのテキストを取得
                String text1 = editText1.getText().toString();
                String text2 = editText2.getText().toString();
                String text3 = editText3.getText().toString();
                String text4 = editText4.getText().toString();
                int checkedId2 = radioGroup.getCheckedRadioButtonId();
                if (checkedId2 != -1) {
                    // 選択されているラジオボタンの取得
                    RadioButton radioButton = (RadioButton) findViewById(checkedId2);// (Fragmentの場合は「getActivity().findViewById」にする)
                    // ラジオボタンのテキストを取得
                    text = radioButton.getText().toString();
                }
                // 入力文字列を"input"に書き込む
                Editor editor = dataStore.edit();
                editor.putString("input", text1);
                editor.putString("input2", text2);
                editor.putString("input3", text3);
                editor.putString("input4", text4);
                editor.putString("input5", text);
                //editor.commit();
                editor.apply();

                Intent intent = new Intent(getApplication(), SettingActivity.class);  //インテント作成
                startActivity(intent);
            }
        });


        // "input"から読み出す、何もないときは"Nothing"を返す
        String str1 = dataStore.getString("input", "Nothing");
        String str2 = dataStore.getString("input2", "Nothing");
        String str3 = dataStore.getString("input3", "Nothing");
        String str4 = dataStore.getString("input4", "Nothing");
        String str5 = dataStore.getString("input5", "Nothing");
        if(!str1.equals("Nothing")) {
            editText1.setText(str1);
        }
        if(!str2.equals("Nothing")) {
            editText2.setText(str2);
        }
        if(!str3.equals("Nothing")) {
            editText3.setText(str3);
        }
        if(!str4.equals("Nothing")) {
            editText4.setText(str4);
        }
        if(!str5.equals("Nothing") && str5.equals("男性")) {
            radioGroup.check(R.id.radioMan);
        }else if(!str5.equals("Nothing") && str5.equals("女性")){
            radioGroup.check(R.id.radioWoman);
        }
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
