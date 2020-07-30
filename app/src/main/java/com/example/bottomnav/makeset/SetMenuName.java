package com.example.bottomnav.makeset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.bottomnav.R;
import com.example.bottomnav.helper.MenuHelper;
import com.example.bottomnav.others.Global;

public class SetMenuName extends AppCompatActivity {
    private MenuHelper helper;
    private SQLiteDatabase db;
    private EditText edit;
    private Global global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setmenu_name);

        global = (Global) this.getApplication();

        edit = findViewById(R.id.setname_text);
        Button addButton = findViewById(R.id.add_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (helper == null) {
                    helper = new MenuHelper(getApplicationContext());
                }

                if (db == null) {
                    db = helper.getWritableDatabase();
                }

                String menuName = edit.getText().toString();

                global.setConveyMenuName(menuName);

                InputMethodManager inputMethodManager =
                        (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);

                insertData(db, menuName);

                Intent intent = new Intent(getApplicationContext(), ChoiceParts.class);
                startActivity(intent);

                global.setMenu(null);

            }
        });
    }

    private void insertData(SQLiteDatabase db, String name) {
        ContentValues values = new ContentValues();
        values.put("setmenu", name);

        db.insert("menudb", null, values);
    }
}