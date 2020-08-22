package com.example.bottomnav.notify;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.bottomnav.R;
import com.example.bottomnav.others.Global;

import java.util.ArrayList;
import java.util.List;

public class MenuRegisterActivity extends AppCompatActivity {

    private List<String> list = new ArrayList<>();
    private List<String> idlist = new ArrayList<>();
    private List<Double> list3 = new ArrayList<>();
    private List<Double> list5 = new ArrayList<>();
    private List<Double> list6 = new ArrayList<>();
    private List<Double> list7 = new ArrayList<>();
    private List<String> list2 = new ArrayList<>();
    private List<String> list4 = new ArrayList<>();
    private List<String> list3_3 = new ArrayList<>();
    private TestOpenHelper helper;
    private TestOpenHelper2 helper2;
    private SQLiteDatabase db;
    private SQLiteDatabase db2;
    private Global global;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_register_activity);

        global = (Global)this.getApplication();

        Toolbar toolbar = findViewById(R.id.toolbarre);

        toolbar.setNavigationIcon(android.R.drawable.ic_menu_revert);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), SubActivity3.class);  //インテント作成
                startActivity(intent);
            }

        });

        Button go_next_button = findViewById(R.id.goaddbtn);
        go_next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        getApplication(), AddMyfoodActivity.class);
                startActivity(intent);
            }

        });

        loadList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(helper == null){
                    helper = new TestOpenHelper(MenuRegisterActivity.this);
                }//編集ボタン等の動作
                db = helper.getWritableDatabase();
                if(helper2 == null){
                    helper2 = new TestOpenHelper2(MenuRegisterActivity.this);
                }//編集ボタン等の動作
                db2 = helper2.getWritableDatabase();
                switch (view.getId()) {
                    case R.id.button2:
                        String date = global.getTestString();
                        String meal = global.getMealString();
                        Intent intent = new Intent(
                                getApplication(), SubActivity3.class);
                        insertData(db2, date, list.get(position), meal, list3.get(position), list2.get(position), list4.get(position),list5.get(position), list6.get(position), list7.get(position));
                        startActivity(intent);
                        break;
                    case R.id.button3:
                        db.delete("testdb", "_id = ?" , new String[] {idlist.get(position)});
                        loadList();
                        break;
                }
            }
        });
    }


    private void insertData(SQLiteDatabase db,String date, String f_name, String m_name, Double f_cal, String f_gra, String f_tail, double car, double pro, double fat){

        ContentValues values = new ContentValues();
        values.put("date", date);
        values.put("food_name", f_name);
        values.put("meal_name", m_name);
        values.put("food_cal", f_cal);
        values.put("food_gram", f_gra);
        values.put("detail", f_tail);
        values.put("food_car", car);
        values.put("food_pro", pro);
        values.put("food_fat", fat);
        db.insert("testdb2", null, values);
    }

    private void loadList(){

        idlist.clear();
        list.clear();
        list3.clear();
        list3_3.clear();
        list4.clear();
        list5.clear();
        list6.clear();
        list7.clear();


        if(helper == null){
            helper = new TestOpenHelper(MenuRegisterActivity.this);
        }

        try (SQLiteDatabase db = helper.getWritableDatabase()){
            // rawQueryというSELECT専用メソッドを使用してデータを取得する
            Cursor c = db.rawQuery("select _id, food_name,food_gram, food_cal, detail, food_car, food_pro, food_fat from testdb", null);
            // Cursorの先頭行があるかどうか確認
            boolean next = c.moveToFirst();

            // 取得した全ての行を取得
            while (next) {
                // 取得したカラムの順番(0から始まる)と型を指定してデータを取得する
                String __id = c.getString(0);
                String rowdata = c.getString(1);// nameを取得
                double rowdata3 = c.getDouble(3);
                double rowdata2 = c.getDouble(2);
                String rowdata4 = c.getString(4);
                double rowdata5 = c.getDouble(5);
                double rowdata6 = c.getDouble(6);
                double rowdata7 = c.getDouble(7);

                idlist.add(__id);
                list.add(rowdata);  //listにdbから取得したデータを追加
                list2.add("１人前" + rowdata2 + "g");
                list3.add(rowdata3);
                list3_3.add(rowdata3 + "kcal");
                list4.add(rowdata4);
                list5.add(rowdata5);
                list6.add(rowdata6);
                list7.add(rowdata7);

                // 次の行が存在するか確認
                next = c.moveToNext();
            }

            c.close();

        }

        listView = findViewById(R.id.listitems);
        CustomAdapter adapter = new CustomAdapter(getApplicationContext(), R.layout.row_item, (ArrayList<String>) list, (ArrayList<String>) list3_3, (ArrayList<String>) list4, (ArrayList<String>) list2);
        listView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }


}
