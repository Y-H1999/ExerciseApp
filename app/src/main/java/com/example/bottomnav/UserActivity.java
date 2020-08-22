package com.example.bottomnav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.bottomnav.helper.UserHelper;

public class UserActivity extends AppCompatActivity {
    EditText nicknameEdit;

    RadioGroup mwRadioGroup;
    RadioButton maleButton;
    RadioButton femaleButton;

    EditText yearEdit;
    EditText monthEdit;
    EditText dayEdit;

    EditText tallEdit;

    Button registerBtn;

    UserHelper userHelper;
    SQLiteDatabase userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        nicknameEdit = findViewById(R.id.nickname_edit);

        mwRadioGroup = findViewById(R.id.mw_group);
        maleButton = findViewById(R.id.male_btn);
        femaleButton = findViewById(R.id.female_btn);

        yearEdit = findViewById(R.id.year_edit);
        monthEdit = findViewById(R.id.month_edit);
        dayEdit = findViewById(R.id.day_edit);

        tallEdit = findViewById(R.id.tall_edit);

        registerBtn = findViewById(R.id.register_btn);

        if (readUserData()) {
            startActivity(new Intent(UserActivity.this, MainActivity.class));
        }

        registerBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String nickname = nicknameEdit.getText().toString();

                        int checkedId = mwRadioGroup.getCheckedRadioButtonId();
                        String mw = null;
                        if (-1 != checkedId) {
                            Toast.makeText(UserActivity.this,
                                    ((RadioButton)findViewById(checkedId)).getText()
                                            + "が選択されています",
                                    Toast.LENGTH_SHORT).show();
                            mw = (String) ((RadioButton) findViewById(checkedId)).getText();
                        } else {
                            Toast.makeText(UserActivity.this,
                                    "何も選択されていません",
                                    Toast.LENGTH_SHORT).show();
                        }

                        String year = yearEdit.getText().toString();
                        String month = monthEdit.getText().toString();
                        String day = dayEdit.getText().toString();
                        String birth = year + "年" + month + "月" + day + "日";

                        String tall = tallEdit.getText().toString();

                        insertData(userDB, nickname, mw, birth, tall);

                        startActivity(new Intent(UserActivity.this, MainActivity.class));
                    }
                }
        );
    }

    public void insertData(SQLiteDatabase db, String nickname, String mw, String birth, String tall) {
        if (userHelper == null) {
            userHelper = new UserHelper(getApplicationContext());
        }
        if (userDB == null) {
            userDB = userHelper.getWritableDatabase();
        }

        ContentValues values = new ContentValues();
        values.put("nickname", nickname);
        values.put("mw", mw);
        values.put("birth", birth);
        values.put("tall", tall);

        db.insert("userdb", null, values);
    }

    public boolean readUserData() {
        if (userHelper == null) {
            userHelper = new UserHelper(getApplicationContext());
        }
        if (userDB == null) {
            userDB = userHelper.getReadableDatabase();
        }

        Cursor c = userDB.query("userdb", new String[] {"nickname", "mw", "birth", "tall"}, null, null, null,
                null, null);
        boolean b = c.moveToFirst();

        c.close();

        return b;
    }
}