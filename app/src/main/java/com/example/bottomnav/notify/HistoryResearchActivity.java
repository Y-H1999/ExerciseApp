package com.example.bottomnav.notify;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.bottomnav.R;
import com.example.bottomnav.others.Global;

import java.util.ArrayList;
import java.util.List;

public class HistoryResearchActivity extends AppCompatActivity {

    private TestOpenHelper2 helper2 = null;
    private List<String> relist0 = new ArrayList<>();
    private List<Double> relist = new ArrayList<>();
    private List<Double> relist5 = new ArrayList<>();
    private List<Double> relist6 = new ArrayList<>();
    private List<Double> relist7 = new ArrayList<>();
    private List<String> relist1 = new ArrayList<>();
    private List<String> cal_list  = new ArrayList<>();
    private List<String> detail_list  = new ArrayList<>();
    private SQLiteDatabase db2;
    private NonScrollListView listView;
    private Global global;
    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_research);

        final EditText search_items = findViewById(R.id.search_edit1);
        listView = findViewById(R.id.search_list);
        final Button searchbtn = findViewById(R.id.search_btn1);
        global = (Global)this.getApplication();

        Toolbar toolbar = findViewById(R.id.toolbarh);

        toolbar.setNavigationIcon(android.R.drawable.ic_menu_revert);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //DB処理
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relist0.clear();
                relist.clear();
                cal_list.clear();
                relist1.clear();
                relist5.clear();
                relist6.clear();
                relist7.clear();

                String search_item = search_items.getText().toString();
                if(helper2 == null){
                    helper2 = new TestOpenHelper2(HistoryResearchActivity.this);
                }
                try (SQLiteDatabase db = helper2.getWritableDatabase()){
                    // rawQueryというSELECT専用メソッドを使用してデータを取得する
                    Cursor c = db.rawQuery("select _id2, food_name, food_cal, food_gram, detail, food_car, food_pro, food_fat from testdb2", null);
                    // Cursorの先頭行があるかどうか確認
                    boolean next = c.moveToFirst();

                    // 取得した全ての行を取得
                    while (next) {
                        // 取得したカラムの順番(0から始まる)と型を指定してデータを取得する
                        String rowdata0 = c.getString(1);// food_nameを取得
                        Double rowdata2 = c.getDouble(2);// food_calを取得
                        String rowdata3 = c.getString(3);// food_gramを取得
                        String rowdata4 = c.getString(4);
                        Double rowdata5 = c.getDouble(5);
                        Double rowdata6 = c.getDouble(6);
                        Double rowdata7 = c.getDouble(7);

                        s= "false";
                        int num = 0;

                        if(!search_item.equals("")) {
                            if (rowdata0.matches(".*" + search_item + ".*")) {
                                if (relist0.size() != 0) {
                                    while (num != relist0.size()) {
                                        if (relist0.get(num).equals(rowdata0) && relist.get(num).equals(rowdata2) && relist1.get(num).equals(rowdata3) && detail_list.get(num).equals(rowdata4)) {
                                            s = "true";
                                            break;
                                        }
                                        num = num + 1;
                                    }
                                }
                                if(!s.equals("true")) {
                                    relist0.add(rowdata0);
                                    relist.add(rowdata2);
                                    relist1.add(rowdata3);
                                    cal_list.add(rowdata2 + "kcal");
                                    detail_list.add(rowdata4);
                                    relist5.add(rowdata5);
                                    relist6.add(rowdata6);
                                    relist7.add(rowdata7);

                                }
                            }
                        }

                        // 次の行が存在するか確認
                        next = c.moveToNext();
                    }

                    c.close();

                }

                CustomAdapter3 adapter = new CustomAdapter3(getApplicationContext(), R.layout.searchedlist, (ArrayList<String>) relist0, (ArrayList<String>) cal_list, (ArrayList<String>)relist1, (ArrayList<String>)detail_list);
                listView.setAdapter(adapter);

                adapter.notifyDataSetChanged();
            }

        });

        setlistbtn();

    }

    private void insertData(SQLiteDatabase db,String date, String f_name, String m_name, Double f_cal, String f_gra, String detail, double f_car, double f_pro, double f_fat){

        ContentValues values = new ContentValues();
        values.put("date", date);
        values.put("food_name", f_name);
        values.put("meal_name", m_name);
        values.put("food_cal", f_cal);
        values.put("food_gram", f_gra);
        values.put("detail", detail);
        values.put("food_car", f_car);
        values.put("food_pro", f_pro);
        values.put("food_fat", f_fat);
        db.insert("testdb2", null, values);
    }

    public void setlistbtn(){
        listView = findViewById(R.id.search_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(helper2 == null){
                    helper2 = new TestOpenHelper2(HistoryResearchActivity.this);
                }//編集ボタン等の動作
                db2 = helper2.getWritableDatabase();
                if (view.getId() == R.id.button2) {
                    String date = global.getTestString();
                    String meal = global.getMealString();
                    insertData(db2, date, relist0.get(position), meal, relist.get(position), relist1.get(position), detail_list.get(position), relist5.get(position), relist6.get(position), relist7.get(position));
                    switch (global.getMealString()){
                        case "朝食":
                            Intent intent = new Intent(
                                    getApplication(), SubActivity3.class);
                            startActivity(intent);
                            break;
                        case "昼食":
                            Intent intent1 = new Intent(
                                    getApplication(), SubActivity3_2.class);
                            startActivity(intent1);
                            break;
                        case "間食":
                            Intent intent2 = new Intent(
                                    getApplication(), SubActivity3_3.class);
                            startActivity(intent2);
                            break;
                        case "夕食":
                            Intent intent3 = new Intent(
                                    getApplication(), SubActivity3_4.class);
                            startActivity(intent3);
                            break;
                    }
                }
            }
        });

    }

}
