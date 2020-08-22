package com.example.bottomnav.setting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.bottomnav.R;

public class Mail_Change_Activity extends AppCompatActivity {

    private SharedPreferences mail_dataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mail_change_activity);

        mail_dataStore = getSharedPreferences("DataStore", MODE_PRIVATE);
        final EditText editmail = findViewById(R.id.editTextTextEmailAddress);

        Toolbar toolbar = findViewById(R.id.toolbar_mail_form);

        Button button = findViewById(R.id.button7);

        toolbar.setNavigationIcon(android.R.drawable.ic_menu_revert);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), UserInfoActivity.class);
                startActivity(intent);
            }

        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = editmail.getText().toString();
//                Intent mailIntent = new Intent();

                SharedPreferences.Editor editor = mail_dataStore.edit();
                if(!text1.equals("")) {
                    editor.putString("input_mail", text1);
                    editor.apply();
                }

            }
        });

    }

}
