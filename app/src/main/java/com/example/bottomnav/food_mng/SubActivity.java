package com.example.bottomnav.food_mng;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.bottomnav.R;
import com.example.bottomnav.notify.NonScrollListView;
import com.example.bottomnav.notify.SubActivity3;
import com.example.bottomnav.notify.SubActivity3_2;
import com.example.bottomnav.notify.SubActivity3_3;
import com.example.bottomnav.notify.SubActivity3_4;
import com.example.bottomnav.notify.TestOpenHelper2;
import com.example.bottomnav.others.Global;


import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class SubActivity extends AppCompatActivity {

    //    private final static String[] lists = {"体重    ","朝食    ","昼食    ","間食    ","夕食    ","エクササイズ","消費カロリー","摂取カロリー"};
//    private List<String> lists2 = new ArrayList<>();
    private int dayOfMonth1;
    private int dayOfMonth2;
    private int yearb;
    private int monthb;
    private String textdate2;
    private String type = "";
    private TextView textdate;
    private List<String> weightlists = new ArrayList<>();
    private List<String> datelists = new ArrayList<>();
    private List<String> food_list = new ArrayList<>();
    private List<String> cal_list = new ArrayList<>();
    private Global global;
    SQLiteDatabase db2;
    FoodNameHelper helper2;
    private TestOpenHelper2 helper;
    KcalHelper helper3;
    private TextView textView5, t1, t2, t3, t4, t5, t6;
    private String date6 = "";
    private String data = "";
    private double correct_cal = 0;
    private double correct_car = 0;
    private double correct_pro = 0;
    private double correct_fat = 0;
    private float c = 0f;
    private float p =0f;
    private float f =0f;
    TextView aim_cal, aim_cal1;

    private int t = 0;
    String[] dt1 = new String[]{"100"};


    @SuppressLint({"WrongViewCast", "DefaultLocale", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        global = (Global) this.getApplication();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suba);

        t1 = findViewById(R.id.textView2);
        t2 = findViewById(R.id.textView13);
        t3 = findViewById(R.id.textView14);
        t4 = findViewById(R.id.textView25);
        t5 = findViewById(R.id.textView23);
        t6 = findViewById(R.id.textView22);
        aim_cal = (TextView)findViewById(R.id.aim_cal2);
        aim_cal1 = findViewById(R.id.aim_cal1);

        final TextView dateText = (TextView) findViewById(R.id.datetext);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_item1);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navi);

        bottomNavigationView.setSelectedItemId(R.id.food);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.dashbaord:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.food:
                        return true;
                    case R.id.fitness:
                        startActivity(new Intent(getApplicationContext(),Fitness.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                textdate = findViewById(R.id.datetext);
                textdate2 = textdate.getText().toString();

                switch (item.getItemId()) {
                    case R.id.menuBody:
                        Intent intent2 = new Intent(
                                getApplication(), SubActivity2.class);
                        startActivity(intent2);
                        return true;
                    case R.id.menuMeal:
                        return true;

                    case R.id.menuBreakfast:
                        Intent intent3 = new Intent(
                                getApplication(), SubActivity3.class);
                        global.setMealString("朝食");
                        startActivity(intent3);
                        return true;
                    case R.id.menuLunch:
                        Intent intent4 = new Intent(
                                getApplication(), SubActivity3_2.class);
                        global.setMealString("昼食");
                        startActivity(intent4);
                        return true;
                    case R.id.menuSnack:
                        Intent intent5 = new Intent(
                                getApplication(), SubActivity3_3.class);
                        global.setMealString("間食");
                        startActivity(intent5);
                        return true;
                    case R.id.menuDinner:
                        Intent intent6 = new Intent(
                                getApplication(), SubActivity3_4.class);
                        global.setMealString("夕食");
                        startActivity(intent6);
                        return true;
                    case R.id.menuExercise:
                        Intent intent7 = new Intent(
                                getApplication(), SubActivity4.class);
                        startActivity(intent7);
                        return true;
                    case R.id.menuDialog:
                        final Calendar date = Calendar.getInstance(); //Calendarインスタンスを取得

                        DatePickerDialog datePickerDialog = new DatePickerDialog(     //DatePickerDialogインスタンスを取得
                                SubActivity.this,
                                new DatePickerDialog.OnDateSetListener() {

                                    @Override
                                    public void onDateSet(DatePicker view, final int year, final int month, final int dayOfMonth) {
                                        @SuppressLint("DefaultLocale") String date1 = String.format("%d / %02d / %02d", year, month + 1, dayOfMonth);
                                        dateText.setText(date1);  //setした日付を取得して表示
                                        textdate2 = dateText.getText().toString();
                                        t = 0;

                                        Button redayButton = findViewById(R.id.redaybtn);
                                        redayButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                if (t == 0) {
                                                    t = 1;
                                                }
                                                if (t == 1) {
                                                    dayOfMonth2 = dayOfMonth - 1;
                                                    dayOfMonth1 = dayOfMonth2;
                                                    monthb = month + 1;
                                                    yearb = year;
                                                    if (dayOfMonth1 < 1 && monthb == 1) {
                                                        monthb = 12;
                                                        dayOfMonth1 = maxCal(year, monthb);
                                                        yearb--;
                                                        @SuppressLint("DefaultLocale") String date2 = String.format("%d / %02d / %02d", yearb, monthb, dayOfMonth1);
                                                        dateText.setText(date2);
                                                    } else if (dayOfMonth1 < 1 && monthb != 1) {
                                                        monthb = monthb - 1;
                                                        dayOfMonth1 = maxCal(year, monthb);
                                                        @SuppressLint("DefaultLocale") String date2 = String.format("%d / %02d / %02d", year, monthb, dayOfMonth1);
                                                        dateText.setText(date2);
                                                    } else {
                                                        @SuppressLint("DefaultLocale") String date2 = String.format("%d / %02d / %02d", year, month + 1, dayOfMonth1);
                                                        dateText.setText(date2);
                                                    }
                                                    textdate2 = dateText.getText().toString();

                                                    t++;

                                                    global.setTestString(textdate2);
                                                    getKcal();

                                                } else {
                                                    dayOfMonth1 = dayOfMonth1 - 1;
                                                    if (dayOfMonth1 < 1 && monthb == 1) {
                                                        monthb = 12;
                                                        dayOfMonth1 = maxCal(year, monthb);
                                                        yearb--;
                                                        @SuppressLint("DefaultLocale") String date2 = String.format("%d / %02d / %02d", yearb, monthb, dayOfMonth1);
                                                        dateText.setText(date2);
                                                    } else if (dayOfMonth1 < 1 && monthb != 1) {
                                                        monthb = monthb - 1;
                                                        dayOfMonth1 = maxCal(year, monthb);
                                                        @SuppressLint("DefaultLocale") String date2 = String.format("%d / %02d / %02d", yearb, monthb, dayOfMonth1);
                                                        dateText.setText(date2);
                                                    } else {
                                                        @SuppressLint("DefaultLocale") String date2 = String.format("%d / %02d / %02d", yearb, monthb, dayOfMonth1);
                                                        dateText.setText(date2);
                                                    }
                                                    textdate2 = dateText.getText().toString();

                                                    global.setTestString(textdate2);
                                                    getKcal();
                                                }
                                            }
                                        });


                                        Button nextdayButton = findViewById(R.id.nextdaybtn);
                                        nextdayButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                if (t == 0) {
                                                    t = 1;
                                                }
                                                if (t == 1) {
                                                    dayOfMonth2 = dayOfMonth + 1;
                                                    dayOfMonth1 = dayOfMonth2;
                                                    monthb = month + 1;
                                                    yearb = year;
                                                    if (dayOfMonth1 > maxCal(year, monthb) && monthb == 12) {
                                                        dayOfMonth1 = 1;
                                                        monthb = 1;
                                                        yearb++;
                                                        @SuppressLint("DefaultLocale") String date2 = String.format("%d / %02d / %02d", yearb, monthb, dayOfMonth1);
                                                        dateText.setText(date2);
                                                    } else if (dayOfMonth1 > maxCal(year, monthb) && monthb != 12) {
                                                        dayOfMonth1 = 1;
                                                        monthb = monthb + 1;
                                                        @SuppressLint("DefaultLocale") String date2 = String.format("%d / %02d / %02d", year, monthb, dayOfMonth1);
                                                        dateText.setText(date2);
                                                    } else {
                                                        @SuppressLint("DefaultLocale") String date2 = String.format("%d / %02d / %02d", year, month + 1, dayOfMonth1);
                                                        dateText.setText(date2);
                                                    }
                                                    textdate2 = dateText.getText().toString();

                                                    t++;

                                                    global.setTestString(textdate2);
                                                    getKcal();


                                                } else {
                                                    dayOfMonth1 = dayOfMonth1 + 1;
                                                    if (dayOfMonth1 > maxCal(year, monthb) && monthb == 12) {
                                                        dayOfMonth1 = 1;
                                                        monthb = 1;
                                                        yearb++;
                                                        @SuppressLint("DefaultLocale") String date2 = String.format("%d / %02d / %02d", yearb, monthb, dayOfMonth1);
                                                        dateText.setText(date2);
                                                    } else if (dayOfMonth1 > maxCal(year, monthb) && monthb != 12) {
                                                        dayOfMonth1 = 1;
                                                        monthb = monthb + 1;
                                                        @SuppressLint("DefaultLocale") String date2 = String.format("%d / %02d / %02d", yearb, monthb, dayOfMonth1);
                                                        dateText.setText(date2);
                                                    } else {
                                                        @SuppressLint("DefaultLocale") String date2 = String.format("%d / %02d / %02d", yearb, monthb, dayOfMonth1);
                                                        dateText.setText(date2);
                                                    }
                                                    textdate2 = dateText.getText().toString();

                                                    global.setTestString(textdate2);
                                                    getKcal();


                                                }

                                            }

                                        });

                                    }
                                },
                                date.get(Calendar.YEAR),
                                date.get(Calendar.MONTH),
                                date.get(Calendar.DATE)
                        );

                        datePickerDialog.show();   //dialogを表示
                }

                return true;

            }
        });





        Calendar calendar1 = Calendar.getInstance();
        final int year = calendar1.get(Calendar.YEAR);
        final int month = calendar1.get(Calendar.MONTH) + 1;
        final int day = calendar1.get(Calendar.DATE);
        if (dateText.getText().toString().equals("")) {
            dateText.setText(String.format("%d / %02d / %02d", year, month, day));
            global.setTestString(String.format("%d / %02d / %02d", year, month, day));
            getKcal();
        }



        Button nextdayButton = findViewById(R.id.nextdaybtn);




        nextdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (t == 0) {
                    t = 1;
                }
                if (t == 1) {
                    dayOfMonth2 = day + 1;
                    dayOfMonth1 = dayOfMonth2;
                    monthb = month;
                    yearb = year;
                    if (dayOfMonth1 > maxCal(year, month) && month == 12) {

                        dayOfMonth1 = 1;
                        monthb = 1;
                        yearb++;
                        @SuppressLint("DefaultLocale") String date2 = String.format("%d / %02d / %02d", yearb, monthb, dayOfMonth1);
                        dateText.setText(date2);
                    } else if (dayOfMonth1 > maxCal(year, month) && month != 12) {
                        dayOfMonth1 = 1;
                        monthb = monthb + 1;
                        @SuppressLint("DefaultLocale") String date2 = String.format("%d / %02d / %02d", year, monthb, dayOfMonth1);
                        dateText.setText(date2);
                    } else {
                        @SuppressLint("DefaultLocale") String date2 = String.format("%d / %02d / %02d", year, month, dayOfMonth1);
                        dateText.setText(date2);
                    }
                    textdate2 = dateText.getText().toString();
                    t++;
                    global.setTestString(textdate2);
                    getKcal();

                } else {
                    dayOfMonth1 = dayOfMonth1 + 1;
                    if (dayOfMonth1 > maxCal(year, monthb) && monthb == 12) {
                        dayOfMonth1 = 1;
                        monthb = 1;
                        yearb++;
                        @SuppressLint("DefaultLocale") String date2 = String.format("%d / %02d / %02d", yearb, monthb, dayOfMonth1);
                        dateText.setText(date2);
                    } else if (dayOfMonth1 > maxCal(year, monthb) && monthb != 12) {
                        dayOfMonth1 = 1;
                        monthb = monthb + 1;
                        @SuppressLint("DefaultLocale") String date2 = String.format("%d / %02d / %02d", yearb, monthb, dayOfMonth1);
                        dateText.setText(date2);
                    } else {
                        @SuppressLint("DefaultLocale") String date2 = String.format("%d / %02d / %02d", yearb, monthb, dayOfMonth1);
                        dateText.setText(date2);
                    }
                    textdate2 = dateText.getText().toString();
                    global.setTestString(textdate2);
                    getKcal();


                }

            }

        });

        Button redayButton = findViewById(R.id.redaybtn);
        redayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (t == 0) {
                    t = 1;
                }
                if (t == 1) {
                    dayOfMonth2 = day - 1;
                    dayOfMonth1 = dayOfMonth2;
                    monthb = month;
                    yearb = year;
                    if (dayOfMonth1 < 1 && month == 1) {
                        dayOfMonth1 = maxCal(year, month - 1);
                        monthb = 12;
                        yearb--;
                        @SuppressLint("DefaultLocale") String date2 = String.format("%d / %02d / %02d", yearb, monthb, dayOfMonth1);
                        dateText.setText(date2);
                    } else if (dayOfMonth1 < 1 && month != 1) {
                        monthb = monthb - 1;
                        dayOfMonth1 = maxCal(year, monthb);
                        @SuppressLint("DefaultLocale") String date2 = String.format("%d / %02d / %02d", year, monthb, dayOfMonth1);
                        dateText.setText(date2);
                    } else {
                        @SuppressLint("DefaultLocale") String date2 = String.format("%d / %02d / %02d", year, month, dayOfMonth1);
                        dateText.setText(date2);
                    }

                    t++;
                    textdate2 = dateText.getText().toString();
                    global.setTestString(textdate2);
                    getKcal();

                } else {
                    dayOfMonth1 = dayOfMonth1 - 1;
                    if (dayOfMonth1 < 1 && monthb == 1) {
                        monthb = 12;
                        dayOfMonth1 = maxCal(year, monthb);
                        yearb--;
                        @SuppressLint("DefaultLocale") String date2 = String.format("%d / %02d / %02d", yearb, monthb, dayOfMonth1);
                        dateText.setText(date2);
                    } else if (dayOfMonth1 < 1 && monthb != 1) {
                        monthb = monthb - 1;
                        dayOfMonth1 = maxCal(year, monthb);
                        @SuppressLint("DefaultLocale") String date2 = String.format("%d / %02d / %02d", yearb, monthb, dayOfMonth1);
                        dateText.setText(date2);
                    } else {
                        @SuppressLint("DefaultLocale") String date2 = String.format("%d / %02d / %02d", yearb, monthb, dayOfMonth1);
                        dateText.setText(date2);
                    }
                    textdate2 = dateText.getText().toString();

                    global.setTestString(textdate2);
                    getKcal();
                }
            }
        });

    }



    public static int maxCal(int year2, int month2) {

        //取得処理
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year2);
        calendar.set(Calendar.MONTH, month2 - 1);

        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public  void  Mokuhyou(String type, String data) {
        p = 0f;
        c = 0f;
        f = 0f;
        if(data != null) {
            switch (type) {
                case "PFCバランス型":
                    p = (float) (Math.round(10.0 * 0.3 * Double.parseDouble(data)/4.0)/10.0);
                    f = (float) (Math.round(10.0 * 0.2 * Double.parseDouble(data)/9.0)/10.0);
                    c = (float) (Math.round(10.0 * 0.5 * Double.parseDouble(data)/4.0)/10.0);
                    break;
                case "ローカーボ型":
                    p = (float) (Math.round(10.0 * 0.4 * Double.parseDouble(data)/4.0)/10.0);
                    f = (float) (Math.round(10.0 * 0.1 * Double.parseDouble(data)/9.0)/10.0);
                    c = (float) (Math.round(10.0 * 0.5 * Double.parseDouble(data)/4.0)/10.0);
                    break;
                case "ローファット型":
                    p = (float) (Math.round(10.0 * 0.4 * Double.parseDouble(data)/4.0)/10.0);
                    f = (float) (Math.round(10.0 * 0.5 * Double.parseDouble(data)/9.0)/10.0);
                    c = (float) (Math.round(10.0 * 0.1 * Double.parseDouble(data)/4.0)/10.0);
                    break;
                case "ケトジェニック型":
                    p = (float) (Math.round(10.0 * 0.2 * Double.parseDouble(data)/4.0)/10.0);
                    f = (float) (Math.round(10.0 * 0.7 * Double.parseDouble(data)/9.0)/10.0);
                    c = (float) (Math.round(10.0 * 0.1 * Double.parseDouble(data)/4.0)/10.0);
                    break;
            }

        }else{
            Toast.makeText(getApplication(),"目標摂取カロリーを入力してください",Toast.LENGTH_SHORT).show();
        }


    }


    private void getKcal(){
        String ze = "0.0kcal";
        aim_cal.setText(ze);
        date6 = null;
        if (helper3 == null) {
            helper3 = new KcalHelper(SubActivity.this);
        }
        try (SQLiteDatabase db = helper3.getWritableDatabase()) {

            Cursor c = db.rawQuery("select date, name, type from kcaldb", null);

            boolean next = c.moveToFirst();

            while (next) {
                String date = c.getString(0);

                if(date.equals(global.getTestString())) {
                    date6 = c.getString(1); //目標摂取カロリーを取得
                    type = c.getString(2);  // 型名を取得

                    if(date6 != null) {
                        String cal = date6 + "kcal";
                        aim_cal.setText(cal);
                        String setT = "目標摂取カロリー" + "(" + type + ")";
                        aim_cal1.setText(setT);
                    }
                }
                next = c.moveToNext();
            }
            c.close();

        }

        Mokuhyou(type, date6);
        getDailyCal();
        drawGraph();
        String t_1 = " " + c + "g";
        String t_2 = " " + p + "g";
        String t_3 = " " + f + "g";
        String t_4 = " " + correct_car + "g";
        String t_5 = " " + correct_pro + "g";
        String t_6 = " " + correct_fat + "g";
        t1.setText(t_1);
        t2.setText(t_2);
        t3.setText(t_3);
        t4.setText(t_4);
        t5.setText(t_5);
        t6.setText(t_6);
    }

    private void getDailyCal(){
        food_list.clear();
        cal_list.clear();
        correct_car = 0;
        correct_cal = 0;
        correct_fat = 0;
        correct_pro = 0;
        String cal;

        if (helper == null) {
            helper = new TestOpenHelper2(SubActivity.this);
        }
        try (SQLiteDatabase db = helper.getWritableDatabase()) {

            Cursor c = db.rawQuery("select date, food_name, food_cal, food_car, food_pro, food_fat from testdb2", null);

            boolean next = c.moveToFirst();

            while (next) {
                String date = c.getString(0);

                if(date.equals(global.getTestString())) {
                    String f_name = c.getString(1);
                    food_list.add(f_name);
                    cal = c.getString(2);
                    correct_cal = correct_cal + Double.parseDouble(cal);
                    cal_list.add(cal);
                    String car = c.getString(3);
                    correct_car = correct_car + Double.parseDouble(car);
                    String pro = c.getString(4);
                    correct_pro = correct_pro + Double.parseDouble(pro);
                    String fat = c.getString(5);
                    correct_fat = correct_fat + Double.parseDouble(fat);

                }
                next = c.moveToNext();
            }
            c.close();

            textView5 = findViewById(R.id.kal);
            String d = correct_cal + "kcal";
            textView5.setText(d);

        }

            NonScrollListView nonListView = findViewById(R.id.shokuhinn);
            Non_CustomAdapter adapter = new Non_CustomAdapter(getApplicationContext(), R.layout.f_mng_list, (ArrayList<String>) food_list, (ArrayList<String>) cal_list);
            nonListView.setAdapter(adapter);

            adapter.notifyDataSetChanged();

    }

    private void drawGraph(){

        PieChart pieChart = findViewById(R.id.pie_chart);
        PieChart pieChart1 = findViewById(R.id.pie_chart2);
        pieChart.setDescription("");
        pieChart1.setDescription("");
        pieChart.setUsePercentValues(false);
        pieChart1.setUsePercentValues(false);
        pieChart.setExtraOffsets(5,10,5,5);
        pieChart1.setExtraOffsets(5,10,5,5);

        pieChart.setDragDecelerationFrictionCoef(0.99f);
        pieChart1.setDragDecelerationFrictionCoef(0.99f);

        pieChart.setDrawHoleEnabled(true);
        pieChart1.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart1.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(60f);
        pieChart1.setTransparentCircleRadius(60f);

        pieChart.setRotationEnabled(false);
        pieChart1.setRotationEnabled(false);


        pieChart.setCenterText("Kcal:" + correct_cal);


        if(date6 != null){
            pieChart1.setCenterText("Kcal:" + date6);
        }else {
            pieChart1.setCenterText("Kcal:0.0");
        }

        ArrayList<Entry> yValues = new ArrayList<>();

        yValues.add(new Entry((float) correct_car,0));
        yValues.add(new Entry((float) correct_pro,1));
        yValues.add(new Entry((float) correct_fat,2));

        ArrayList<Entry> xValues = new ArrayList<>();
        ArrayList<String> xVals = new ArrayList<>();

        xVals.add("Carbohydrate");
        xVals.add("Protein");
        xVals.add("Fat");

        xValues.add(new Entry(c,0));
        xValues.add(new Entry(p,1));
        xValues.add(new Entry(f,2));


        PieDataSet dataSet = new PieDataSet(yValues,null);
        PieDataSet dataSet1 = new PieDataSet(xValues,null);
        dataSet.setSliceSpace(3f);
        dataSet1.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet1.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        dataSet1.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData data = new PieData(xVals, (PieDataSet) dataSet);
        PieData data1 = new PieData(xVals, (PieDataSet) dataSet1);

        data1.setValueTextSize(10f);
        data.setValueTextSize(10f);

        pieChart.setData(data);
        pieChart1.setData(data1);

        pieChart.invalidate();
        pieChart1.invalidate();
    }

}
