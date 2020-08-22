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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.bottomnav.R;

public class AddMyfoodActivity extends AppCompatActivity {

    private EditText editTextKey, editTextValue, editTextValue2, editDetail, editTextValue3, editTextValue4, editTextValue5;
    private TestOpenHelper helper;
    private SQLiteDatabase db;
    private String detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmyfood);

        //DB処理
        editTextKey = findViewById(R.id.setname);
        editTextValue = findViewById(R.id.setg);
        editTextValue2 = findViewById(R.id.setc);
        editTextValue3 = findViewById(R.id.carbon_edit);
        editTextValue4 = findViewById(R.id.edit_pro);
        editTextValue5 = findViewById(R.id.fat_edit);
        editDetail = findViewById(R.id.detail);

        Toolbar toolbar = findViewById(R.id.newmyfoodtoolbar);

        toolbar.inflateMenu(R.menu.menu_add);

        toolbar.setNavigationIcon(android.R.drawable.ic_menu_revert);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MenuRegisterActivity.class);
                startActivity(intent);
            }

        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            String value3;
            String value4;
            String value5;

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(editTextKey.getText().length() != 0 && editTextValue.getText().length() != 0 && editTextValue2.getText().length() != 0){
                    if (item.getItemId() == R.id.menuAddBtn) {
                        Intent intent = new Intent(
                                getApplication(), MenuRegisterActivity.class);
                        if (helper == null) {
                            helper = new TestOpenHelper(getApplicationContext());
                        }

                        if (db == null) {
                            db = helper.getReadableDatabase();
                        }
                        Log.d("debug", "**********Cursor");

                        Cursor cursor = db.query(
                                "testdb",
                                new String[]{"food_name", "food_gram"},
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
                            sbuilder.append(cursor.getInt(1));
                            sbuilder.append("\n");
                            cursor.moveToNext();
                        }

                        cursor.close();

                        Log.d("debug", "**********" + sbuilder.toString());
                        intent.putExtra("String Value", sbuilder.toString());

                        String key = editTextKey.getText().toString();
                        String value = editTextValue.getText().toString();
                        String value2 = editTextValue2.getText().toString();
                        if(!editTextValue3.getText().toString().equals("")) {
                            value3 = editTextValue3.getText().toString();
                        }else{
                            value3 = "0";
                        }
                        if(!editTextValue4.getText().toString().equals("")) {
                            value4 = editTextValue4.getText().toString();
                        }else{
                            value4 = "0";
                        }
                        if(!editTextValue5.getText().toString().equals("")) {
                            value5 = editTextValue5.getText().toString();
                        }else{
                            value5 = "0";
                        }

                        if(editDetail.getText().toString().length() == 0){
                            detail = "";
                        }else{
                            detail = editDetail.getText().toString();
                        }
                        insertData(db, key, Double.parseDouble(value), Double.parseDouble(value2), detail, Double.parseDouble(value3), Double.parseDouble(value4), Double.parseDouble(value5));

                        startActivity(intent);
                    }
                }else{
                    Toast.makeText(getApplication(), "未入力の項目があります。", Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });
    }

    private void insertData(SQLiteDatabase db, String com, double gram,double cal, String detail1, double car, double pro, double fat){

        ContentValues values = new ContentValues();
        values.put("food_name", com);
        values.put("food_gram", gram);
        values.put("food_cal", cal);
        values.put("detail", detail1);
        values.put("food_car", car);
        values.put("food_pro", pro);
        values.put("food_fat", fat);

        db.insert("testdb", null, values);
    }

}
