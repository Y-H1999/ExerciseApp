package com.example.bottomnav.notify;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.bottomnav.R;
import com.example.bottomnav.others.Global;

import java.util.ArrayList;
import java.util.List;

public class SubActivity2 extends AppCompatActivity {

    private EditText editTextKey;
    private WeightOpenHelper helper;
    private SQLiteDatabase db;
    private List<String> datelists2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);

        Global global = (Global) this.getApplication();

        final TextView t1 = findViewById(R.id.textView6);
        t1.setText(global.getTestString());
        editTextKey = findViewById(R.id.editweight);

        Toolbar toolbar = findViewById(R.id.myitemtoolbar);

        toolbar.setNavigationIcon(android.R.drawable.ic_menu_revert);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent re_intent = new Intent(
                        getApplication(), SubActivity.class);
                startActivity(re_intent);
            }

        });

        toolbar.inflateMenu(R.menu.menu_insert);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menuAddBtn) {
                    Intent intent = new Intent(
                            getApplication(), SubActivity.class);
                    if (editTextKey.getText().length() != 0) {

                        if (helper == null) {
                            helper = new WeightOpenHelper(getApplicationContext());
                        }
                        try (SQLiteDatabase db = helper.getWritableDatabase()) {
                            Cursor c = db.rawQuery("select _weightid, weight, date from weightdb", null);
                            boolean next = c.moveToFirst();

                            while (next) {
                                String date1 = c.getString(2);// dateを取得
                                datelists2.add(date1);
                                next = c.moveToNext();
                            }

                            c.close();

                        }

                        if (db == null) {
                            db = helper.getReadableDatabase();
                        }
                        Log.d("debug", "**********Cursor");

                        Cursor cursor = db.query(
                                "weightdb",
                                new String[]{"weight", "date"},
                                null,
                                null,
                                null,
                                null,
                                null
                        );

                        cursor.moveToFirst();

                        StringBuilder sbuilder = new StringBuilder();

                        for (int i = 0; i < cursor.getCount(); i++) {
                            sbuilder.append(cursor.getString(0));
                            sbuilder.append(": ");
                            sbuilder.append(cursor.getString(1));
                            sbuilder.append("\n");
                            cursor.moveToNext();
                        }

                        cursor.close();

                        Log.d("debug", "**********" + sbuilder.toString());
                        intent.putExtra("String Value", sbuilder.toString());

                        String key = editTextKey.getText().toString();
                        String value = t1.getText().toString();

                        if (datelists2.contains(value)) {
                            ContentValues cv = new ContentValues();
                            cv.put("weight", key);
                            cv.put("date", value);
                            db.update("weightdb", cv, "date = ?", new String[]{value});
                        } else {
                            insertData(db, key, value);
                        }
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplication(), "体重を入力してください。", Toast.LENGTH_LONG).show();
                    }
                }
                return true;
            }
        });

    }

    private void insertData(SQLiteDatabase db, String weight, String date){

        ContentValues values = new ContentValues();
        values.put("weight", weight);
        values.put("date", date);

        db.insert("weightdb", null, values);
    }

}
