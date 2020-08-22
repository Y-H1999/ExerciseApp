package com.example.bottomnav.notify;

import android.content.ContentValues;
import android.content.Intent;
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

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ImgActivity1 extends AppCompatActivity {

    private List<String> relist0 = new ArrayList<>();
    private List<Double> relist = new ArrayList<>();
    private List<Double> relist5 = new ArrayList<>();
    private List<Double> relist6 = new ArrayList<>();
    private List<Double> relist7 = new ArrayList<>();
    private List<String> cal_list = new ArrayList<>();
    private List<String> g_list = new ArrayList<>();
    private List<String> detail_list = new ArrayList<>();
    private String search_item;
    private Global global;
    private SQLiteDatabase db2;
    private TestOpenHelper2 helper2;
    private NonScrollListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imgb1);

        final EditText search_items = findViewById(R.id.search_edit1);
        listView = findViewById(R.id.search_list);
        final Button searchbtn = findViewById(R.id.search_btn1);
        global = (Global)this.getApplication();

        Toolbar toolbar = findViewById(R.id.myitemtoolbar);

        toolbar.setNavigationIcon(android.R.drawable.ic_menu_revert);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relist0.clear();
                relist.clear();
                cal_list.clear();
                g_list.clear();
                detail_list.clear();
                relist5.clear();
                relist6.clear();
                relist7.clear();

                search_item = search_items.getText().toString();

                try {
                    JSONObject json = new JSONObject("{\"foodCode\":\"9999020000044\",\"detail\":{\"foodName\":\"カレーライス（ポーク）\",\"picturePath\":\"\",\"janCode\":\"\",\"standardAmountUnit\":\"人前\",\"contentsAmountUnit\":\"グラム\",\"largeCategoryName\":\"ご飯類\",\"largeCategoryCode\":\"41\",\"foodCategoryName\":\"カレーライス\",\"foodCategoryCode\":\"4101\",\"storeName\":\"\",\"storeCode\":\"\",\"description\":\"1人前<br />\",\"nutrition\":[{\"nutritionName\":\"エネルギー\",\"nutritionDisplayName\":\"エネルギー\",\"nutritionCode\":\"0101\",\"nutritionAmountUnit\":\"kcal\",\"nutritionAmount\":827},{\"nutritionName\":\"タンパク質\",\"nutritionDisplayName\":\"たんぱく質\",\"nutritionCode\":\"0301\",\"nutritionAmountUnit\":\"g\",\"nutritionAmount\":17.6},{\"nutritionName\":\"脂質\",\"nutritionDisplayName\":\"脂質\",\"nutritionCode\":\"0401\",\"nutritionAmountUnit\":\"g\",\"nutritionAmount\":30.4},{\"nutritionName\":\"炭水化物\",\"nutritionDisplayName\":\"炭水化物\",\"nutritionCode\":\"0501\",\"nutritionAmountUnit\":\"g\",\"nutritionAmount\":114.9},{\"nutritionName\":\"糖質(g)\",\"nutritionDisplayName\":\"糖質(g)\",\"nutritionCode\":\"2030\",\"nutritionAmountUnit\":\"g\",\"nutritionAmount\":105.5},{\"nutritionName\":\"総量\",\"nutritionDisplayName\":\"食物繊維(総量)\",\"nutritionCode\":\"1103\",\"nutritionAmountUnit\":\"g\",\"nutritionAmount\":9.4},{\"nutritionName\":\"ナトリウム\",\"nutritionDisplayName\":\"ナトリウム\",\"nutritionCode\":\"0701\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":844},{\"nutritionName\":\"塩分相当\",\"nutritionDisplayName\":\"食塩相当量\",\"nutritionCode\":\"1201\",\"nutritionAmountUnit\":\"g\",\"nutritionAmount\":2.1},{\"nutritionName\":\"カリウム\",\"nutritionDisplayName\":\"カリウム\",\"nutritionCode\":\"0702\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":738},{\"nutritionName\":\"カルシウム\",\"nutritionDisplayName\":\"カルシウム\",\"nutritionCode\":\"0703\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":63},{\"nutritionName\":\"マグネシウム\",\"nutritionDisplayName\":\"マグネシウム\",\"nutritionCode\":\"0704\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":54},{\"nutritionName\":\"リン\",\"nutritionDisplayName\":\"リン\",\"nutritionCode\":\"0705\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":251},{\"nutritionName\":\"鉄\",\"nutritionDisplayName\":\"鉄\",\"nutritionCode\":\"0706\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":2.4},{\"nutritionName\":\"亜鉛\",\"nutritionDisplayName\":\"亜鉛\",\"nutritionCode\":\"0707\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":2.9},{\"nutritionName\":\"銅\",\"nutritionDisplayName\":\"銅\",\"nutritionCode\":\"0708\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":0.42},{\"nutritionName\":\"マンガン\",\"nutritionDisplayName\":\"マンガン\",\"nutritionCode\":\"0709\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":1.46},{\"nutritionName\":\"ヨウ素\",\"nutritionDisplayName\":\"ヨウ素\",\"nutritionCode\":\"0710\",\"nutritionAmountUnit\":\"μg\",\"nutritionAmount\":2},{\"nutritionName\":\"セレン\",\"nutritionDisplayName\":\"セレン\",\"nutritionCode\":\"0711\",\"nutritionAmountUnit\":\"μg\",\"nutritionAmount\":13},{\"nutritionName\":\"クロム\",\"nutritionDisplayName\":\"クロム\",\"nutritionCode\":\"0712\",\"nutritionAmountUnit\":\"μg\",\"nutritionAmount\":2},{\"nutritionName\":\"モリブデン\",\"nutritionDisplayName\":\"モリブデン\",\"nutritionCode\":\"0713\",\"nutritionAmountUnit\":\"μg\",\"nutritionAmount\":82},{\"nutritionName\":\"レチノール当量\",\"nutritionDisplayName\":\"ビタミンA(レチノール活性当量)\",\"nutritionCode\":\"0803\",\"nutritionAmountUnit\":\"μg\",\"nutritionAmount\":342},{\"nutritionName\":\"β-カロテン(μg)\",\"nutritionDisplayName\":\"β-カロテン(μg)\",\"nutritionCode\":\"2260\",\"nutritionAmountUnit\":\"μg\",\"nutritionAmount\":2633},{\"nutritionName\":\"ビタミンＤ\",\"nutritionDisplayName\":\"ビタミンＤ\",\"nutritionCode\":\"0804\",\"nutritionAmountUnit\":\"μg\",\"nutritionAmount\":0.3},{\"nutritionName\":\"α-トコフェロール\",\"nutritionDisplayName\":\"ビタミンE(α-トコフェロール)\",\"nutritionCode\":\"0821\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":1},{\"nutritionName\":\"ビタミンＫ\",\"nutritionDisplayName\":\"ビタミンＫ\",\"nutritionCode\":\"0806\",\"nutritionAmountUnit\":\"μg\",\"nutritionAmount\":19},{\"nutritionName\":\"ビタミンＢ１\",\"nutritionDisplayName\":\"ビタミンＢ１\",\"nutritionCode\":\"0807\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":0.43},{\"nutritionName\":\"ビタミンＢ２\",\"nutritionDisplayName\":\"ビタミンＢ２\",\"nutritionCode\":\"0808\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":0.22},{\"nutritionName\":\"ナイアシン(ナイアシン当量)\",\"nutritionDisplayName\":\"ナイアシン(ナイアシン当量)\",\"nutritionCode\":\"0822\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":9},{\"nutritionName\":\"ビタミンＢ６\",\"nutritionDisplayName\":\"ビタミンＢ６\",\"nutritionCode\":\"0810\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":0.52},{\"nutritionName\":\"ビタミンＢ１２\",\"nutritionDisplayName\":\"ビタミンＢ１２\",\"nutritionCode\":\"0811\",\"nutritionAmountUnit\":\"μg\",\"nutritionAmount\":0.4},{\"nutritionName\":\"葉酸\",\"nutritionDisplayName\":\"葉酸\",\"nutritionCode\":\"0812\",\"nutritionAmountUnit\":\"μg\",\"nutritionAmount\":47},{\"nutritionName\":\"パントテン酸\",\"nutritionDisplayName\":\"パントテン酸\",\"nutritionCode\":\"0813\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":1.97},{\"nutritionName\":\"ビオチン\",\"nutritionDisplayName\":\"ビオチン\",\"nutritionCode\":\"0815\",\"nutritionAmountUnit\":\"μg\",\"nutritionAmount\":7},{\"nutritionName\":\"ビタミンＣ\",\"nutritionDisplayName\":\"ビタミンＣ\",\"nutritionCode\":\"0814\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":15},{\"nutritionName\":\"水分\",\"nutritionDisplayName\":\"水分\",\"nutritionCode\":\"0201\",\"nutritionAmountUnit\":\"g\",\"nutritionAmount\":480.9},{\"nutritionName\":\"灰分\",\"nutritionDisplayName\":\"灰分\",\"nutritionCode\":\"0601\",\"nutritionAmountUnit\":\"g\",\"nutritionAmount\":4},{\"nutritionName\":\"飽和脂肪酸\",\"nutritionDisplayName\":\"飽和脂肪酸\",\"nutritionCode\":\"0901\",\"nutritionAmountUnit\":\"g\",\"nutritionAmount\":14.37},{\"nutritionName\":\"コレステロール\",\"nutritionDisplayName\":\"コレステロール\",\"nutritionCode\":\"1001\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":65},{\"nutritionName\":\"ショ糖(g）\",\"nutritionDisplayName\":\"ショ糖(g）\",\"nutritionCode\":\"2010\",\"nutritionAmountUnit\":\"g\",\"nutritionAmount\":1.9},{\"nutritionName\":\"レチノール\",\"nutritionDisplayName\":\"レチノール\",\"nutritionCode\":\"0801\",\"nutritionAmountUnit\":\"μg\",\"nutritionAmount\":72},{\"nutritionName\":\"α-カロテン\",\"nutritionDisplayName\":\"α-カロテン\",\"nutritionCode\":\"0816\",\"nutritionAmountUnit\":\"μg\",\"nutritionAmount\":1217},{\"nutritionName\":\"β-クリプトキサンチン\",\"nutritionDisplayName\":\"β-クリプトキサンチン\",\"nutritionCode\":\"0817\",\"nutritionAmountUnit\":\"μg\",\"nutritionAmount\":1},{\"nutritionName\":\"β-カロテン当量\",\"nutritionDisplayName\":\"β-カロテン当量\",\"nutritionCode\":\"0802\",\"nutritionAmountUnit\":\"μg\",\"nutritionAmount\":3242},{\"nutritionName\":\"β-トコフェロール\",\"nutritionDisplayName\":\"β-トコフェロール\",\"nutritionCode\":\"0818\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":0},{\"nutritionName\":\"γ-トコフェロール\",\"nutritionDisplayName\":\"γ-トコフェロール\",\"nutritionCode\":\"0819\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":0.2},{\"nutritionName\":\"δ-トコフェロール\",\"nutritionDisplayName\":\"δ-トコフェロール\",\"nutritionCode\":\"0820\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":0},{\"nutritionName\":\"ビタミンＥ\",\"nutritionDisplayName\":\"α-トコフェロール当量\",\"nutritionCode\":\"0805\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":1.1},{\"nutritionName\":\"ナイアシン\",\"nutritionDisplayName\":\"ナイアシン\",\"nutritionCode\":\"0809\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":5.8},{\"nutritionName\":\"たんぱく質（アミノ酸縮合物）\",\"nutritionDisplayName\":\"たんぱく質（アミノ酸縮合物）\",\"nutritionCode\":\"0302\",\"nutritionAmountUnit\":\"g\",\"nutritionAmount\":13.9},{\"nutritionName\":\"アミノ酸(mg)\",\"nutritionDisplayName\":\"アミノ酸(mg)\",\"nutritionCode\":\"2140\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":16400},{\"nutritionName\":\"分岐鎖アミノ酸\",\"nutritionDisplayName\":\"分岐鎖アミノ酸\",\"nutritionCode\":\"2145\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":2606},{\"nutritionName\":\"イソロイシン(mg)\",\"nutritionDisplayName\":\"イソロイシン(mg)\",\"nutritionCode\":\"2170\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":622},{\"nutritionName\":\"ロイシン(mg)\",\"nutritionDisplayName\":\"ロイシン(mg)\",\"nutritionCode\":\"2160\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":1184},{\"nutritionName\":\"トリプトファン\",\"nutritionDisplayName\":\"トリプトファン\",\"nutritionCode\":\"2161\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":192},{\"nutritionName\":\"バリン(mg)\",\"nutritionDisplayName\":\"バリン(mg)\",\"nutritionCode\":\"2150\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":800},{\"nutritionName\":\"アルギニン(mg)\",\"nutritionDisplayName\":\"アルギニン(mg)\",\"nutritionCode\":\"2190\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":1273},{\"nutritionName\":\"アラニン(mg) \",\"nutritionDisplayName\":\"アラニン(mg) \",\"nutritionCode\":\"2180\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":958},{\"nutritionName\":\"グリシン(mg)\",\"nutritionDisplayName\":\"グリシン(mg)\",\"nutritionCode\":\"2210\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":1046},{\"nutritionName\":\"プロリン(mg)\",\"nutritionDisplayName\":\"プロリン(mg)\",\"nutritionCode\":\"2200\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":830},{\"nutritionName\":\"トリアシルグリセロール当量\",\"nutritionDisplayName\":\"トリアシルグリセロール当量\",\"nutritionCode\":\"0402\",\"nutritionAmountUnit\":\"g\",\"nutritionAmount\":28.8},{\"nutritionName\":\"一価不飽和脂肪酸\",\"nutritionDisplayName\":\"一価不飽和脂肪酸\",\"nutritionCode\":\"0902\",\"nutritionAmountUnit\":\"g\",\"nutritionAmount\":10.67},{\"nutritionName\":\"多価不飽和脂肪酸\",\"nutritionDisplayName\":\"多価不飽和脂肪酸\",\"nutritionCode\":\"0903\",\"nutritionAmountUnit\":\"g\",\"nutritionAmount\":2.55},{\"nutritionName\":\"n-3系脂肪酸\",\"nutritionDisplayName\":\"n-3系脂肪酸\",\"nutritionCode\":\"0904\",\"nutritionAmountUnit\":\"g\",\"nutritionAmount\":0.16},{\"nutritionName\":\"n-6系脂肪酸\",\"nutritionDisplayName\":\"n-6系脂肪酸\",\"nutritionCode\":\"0905\",\"nutritionAmountUnit\":\"g\",\"nutritionAmount\":2.38},{\"nutritionName\":\"中鎖脂肪酸\",\"nutritionDisplayName\":\"中鎖脂肪酸\",\"nutritionCode\":\"0909\",\"nutritionAmountUnit\":\"g\",\"nutritionAmount\":0.72},{\"nutritionName\":\"オクタン酸\",\"nutritionDisplayName\":\"オクタン酸\",\"nutritionCode\":\"2282\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":125},{\"nutritionName\":\"デカン酸\",\"nutritionDisplayName\":\"デカン酸\",\"nutritionCode\":\"2283\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":300},{\"nutritionName\":\"ラウリン酸\",\"nutritionDisplayName\":\"ラウリン酸\",\"nutritionCode\":\"2284\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":396},{\"nutritionName\":\"α‐リノレン酸\",\"nutritionDisplayName\":\"α‐リノレン酸\",\"nutritionCode\":\"2285\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":148},{\"nutritionName\":\"DHA(mg)\",\"nutritionDisplayName\":\"DHA(mg)\",\"nutritionCode\":\"2280\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":0},{\"nutritionName\":\"EPA(mg)\",\"nutritionDisplayName\":\"EPA(mg)\",\"nutritionCode\":\"2281\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":0},{\"nutritionName\":\"水溶性\",\"nutritionDisplayName\":\"食物繊維(水溶性)\",\"nutritionCode\":\"1101\",\"nutritionAmountUnit\":\"g\",\"nutritionAmount\":4.1},{\"nutritionName\":\"不溶性\",\"nutritionDisplayName\":\"食物繊維(不溶性)\",\"nutritionCode\":\"1102\",\"nutritionAmountUnit\":\"g\",\"nutritionAmount\":5.2},{\"nutritionName\":\"利用可能炭水化物（単糖当量）\",\"nutritionDisplayName\":\"利用可能炭水化物（単糖当量）\",\"nutritionCode\":\"2020\",\"nutritionAmountUnit\":\"g\",\"nutritionAmount\":110.1},{\"nutritionName\":\"でん粉\",\"nutritionDisplayName\":\"でん粉\",\"nutritionCode\":\"2031\",\"nutritionAmountUnit\":\"g\",\"nutritionAmount\":89.1},{\"nutritionName\":\"ぶどう糖\",\"nutritionDisplayName\":\"ぶどう糖\",\"nutritionCode\":\"2050\",\"nutritionAmountUnit\":\"g\",\"nutritionAmount\":5.1},{\"nutritionName\":\"果糖\",\"nutritionDisplayName\":\"果糖\",\"nutritionCode\":\"2051\",\"nutritionAmountUnit\":\"g\",\"nutritionAmount\":4.5},{\"nutritionName\":\"ガラクトース\",\"nutritionDisplayName\":\"ガラクトース\",\"nutritionCode\":\"2052\",\"nutritionAmountUnit\":\"g\",\"nutritionAmount\":0},{\"nutritionName\":\"麦芽糖\",\"nutritionDisplayName\":\"麦芽糖\",\"nutritionCode\":\"2011\",\"nutritionAmountUnit\":\"g\",\"nutritionAmount\":0.1},{\"nutritionName\":\"乳糖\",\"nutritionDisplayName\":\"乳糖\",\"nutritionCode\":\"2012\",\"nutritionAmountUnit\":\"g\",\"nutritionAmount\":0.1},{\"nutritionName\":\"有機酸\",\"nutritionDisplayName\":\"有機酸\",\"nutritionCode\":\"2221\",\"nutritionAmountUnit\":\"g\",\"nutritionAmount\":0.6},{\"nutritionName\":\"酢酸\",\"nutritionDisplayName\":\"酢酸\",\"nutritionCode\":\"2222\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":72},{\"nutritionName\":\"リンゴ酸(mg)\",\"nutritionDisplayName\":\"リンゴ酸(mg)\",\"nutritionCode\":\"2070\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":184},{\"nutritionName\":\"クエン酸(mg)\",\"nutritionDisplayName\":\"クエン酸(mg)\",\"nutritionCode\":\"2220\",\"nutritionAmountUnit\":\"mg\",\"nutritionAmount\":237},{\"nutritionName\":\"硝酸イオン\",\"nutritionDisplayName\":\"硝酸イオン\",\"nutritionCode\":\"2390\",\"nutritionAmountUnit\":\"g\",\"nutritionAmount\":0}],\"serving\":[{\"dishGroupCode\":\"01\",\"servingAmount\":2.32,\"servingCount\":2},{\"dishGroupCode\":\"02\",\"servingAmount\":2.14,\"servingCount\":2},{\"dishGroupCode\":\"03\",\"servingAmount\":1.20,\"servingCount\":1},{\"dishGroupCode\":\"05\",\"servingAmount\":0.01,\"servingCount\":0}],\"foodLevel\":\"料理\",\"standardAmount\":1,\"contentsAmount\":647.6,\"calorie\":827},\"status\":0}");
                    //JSONArray jarray = json.getJSONObject("detail").getJSONArray("nutrition");  json内のnutritionリストを取得
                    for (int i = 0; i < json.length()/3; ++ i) {
                        String id2 = json.getJSONObject("detail").getString("foodName");
                        double name2 = Double.parseDouble(json.getJSONObject("detail").getString("calorie"));
                        double f_gram = Double.parseDouble(json.getJSONObject("detail").getString("contentsAmount"));
                        double f_car = Double.parseDouble(json.getJSONObject("detail").getJSONArray("nutrition").getJSONObject(3).getString("nutritionAmount"));
                        double f_pro = Double.parseDouble(json.getJSONObject("detail").getJSONArray("nutrition").getJSONObject(1).getString("nutritionAmount"));
                        double f_fat = Double.parseDouble(json.getJSONObject("detail").getJSONArray("nutrition").getJSONObject(2).getString("nutritionAmount"));

                        if(!search_item.equals("")) {
                            if (id2.matches(".*" + search_item + ".*")) {
                                relist0.add(id2);
                                relist.add(name2);
                                cal_list.add(name2 + "kcal");
                                g_list.add("１人前" + f_gram + "g");
                                detail_list.add("");
                                relist5.add(f_car);
                                relist6.add(f_pro);
                                relist7.add(f_fat);
                            }
                        }
                    }
                }
                catch (org.json.JSONException ignored) {
                }

                CustomAdapter3 adapter = new CustomAdapter3(getApplicationContext(), R.layout.searchedlist, (ArrayList<String>) relist0, (ArrayList<String>) cal_list,(ArrayList<String>) g_list , (ArrayList<String>)detail_list);
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
                    helper2 = new TestOpenHelper2(ImgActivity1.this);
                }//編集ボタン等の動作
                db2 = helper2.getWritableDatabase();
                if (view.getId() == R.id.button2) {
                    String date = global.getTestString();
                    String meal = global.getMealString();
                    insertData(db2, date, relist0.get(position), meal, relist.get(position), g_list.get(position), detail_list.get(position), relist5.get(position), relist6.get(position), relist7.get(position));
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