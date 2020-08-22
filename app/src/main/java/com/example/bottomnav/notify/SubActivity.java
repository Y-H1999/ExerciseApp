package com.example.bottomnav.notify;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.bottomnav.R;
import com.example.bottomnav.others.Global;

import java.util.ArrayList;
import java.util.List;

public class SubActivity extends AppCompatActivity {

    private WeightOpenHelper helper;
    private TestOpenHelper2 helper2;
    private int dayOfMonth1, dayOfMonth2, yearb, monthb;
    private String textdate2, dateformat, usersex;
    private TextView textdate, weightText, dateText, calText, bmiText, bmrText;
    private List<String> weightlists = new ArrayList<>();
    private List<String> datelists = new ArrayList<>();
    private int t = 0;
    private Global global;
    private List<String> bmilists = new ArrayList<>();
    private List<String> bmrlists = new ArrayList<>();
    private List<String> bmrlists2 = new ArrayList<>();
    private List<String> non_food_list = new ArrayList<>();
    private List<String> list2 = new ArrayList<>();
    private List<String> list1 = new ArrayList<>();
    private List<Double> non_cal_list = new ArrayList<>();


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        global = (Global) this.getApplication();
        dateText = findViewById(R.id.datetext);
        weightText = findViewById(R.id.num_Text);
        bmiText = findViewById(R.id.bmi_num_Text);
        bmrText = findViewById(R.id.bmr_num_Text);
        calText = findViewById(R.id.gain_cal_num__Text);
        double userheight = 1.8;
        double userage = 20.0;
        usersex = "男性";
        dateformat = "%d / %02d / %02d";

        //ツールバー設定
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.inflateMenu(R.menu.menu_item);

        toolbar.setNavigationIcon(android.R.drawable.ic_menu_revert);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRe = new Intent(
                        getApplication(), MainActivity.class);
                startActivity(intentRe);
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
                        final Calendar date = Calendar.getInstance();
                        DatePickerDialog datePickerDialog = new DatePickerDialog(
                                SubActivity.this,
                                new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker view, final int year, final int month, final int dayOfMonth) {
                                        String date1 = String.format(dateformat, year, month + 1, dayOfMonth);
                                        dateText.setText(date1);
                                        textdate2 = dateText.getText().toString();
                                        global.setTestString(textdate2);
                                        t = 0;
                                        setall();
                                        setWeight(textdate2);
                                        getcal();

                                        Button redayButton = findViewById(R.id.redaybtn);
                                        redayButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                reBtn(dayOfMonth, month + 1, year);
                                                setall();
                                            }
                                        });

                                        Button nextdayButton = findViewById(R.id.nextdaybtn);
                                        nextdayButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                neBtn(dayOfMonth, month + 1, year);
                                                setall();
                                            }
                                        });
                                    }
                                },
                                date.get(Calendar.YEAR),
                                date.get(Calendar.MONTH),
                                date.get(Calendar.DATE)
                        );
                        datePickerDialog.show();
                }

                return true;

            }
        });

        //DB処理
        if (helper == null) {
            helper = new WeightOpenHelper(SubActivity.this);
        }
        try (SQLiteDatabase db = helper.getWritableDatabase()) {

            Cursor c = db.rawQuery("select _weightid, weight, date from weightdb", null);

            boolean next = c.moveToFirst();


            // 取得した全ての行を取得
            while (next) {
                // 取得したカラムの順番(0から始まる)と型を指定してデータを取得する
                String rowdata = c.getString(1);// weightを取得
                String date1 = c.getString(2);// dateを取得
                weightlists.add(rowdata + " kg");  //weightlistsにdbから取得したデータを追加
                double userweight = Double.parseDouble(rowdata);
                double userbmid = (Math.round((userweight / (userheight * userheight))*10.0))/10.0;
                String userbmi = Double.toString(userbmid);
                bmilists.add(userbmi);
                if(usersex.equals("男性")) {
                    double userbmrd = (Math.round((88.362 + (13.397 * userweight) + (4.799 * userheight *100.0) - (5.677 * userage))*10.0))/10.0;
                    String userbmr = Double.toString(userbmrd);
                    bmrlists.add(userbmr + " kcal");
                }else{
                    double userbmrd2 = (Math.round((444.593 + (9.247 * userweight) + (3.098 * userheight *100.0) - (4.330 * userage))*10.0))/10.0;
                    String userbmr2 = Double.toString(userbmrd2);
                    bmrlists2.add(userbmr2 + " kcal");
                }
                datelists.add(date1);
                next = c.moveToNext();
            }
            c.close();

        }

        Button nextdayButton = findViewById(R.id.nextdaybtn);
        Button redayButton = findViewById(R.id.redaybtn);
        Button graphButton = findViewById(R.id.button4);


        graphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentg = new Intent(
                        getApplication(), GraphActivity.class);
                startActivity(intentg);
            }
        });


        Calendar calendar1 = Calendar.getInstance();
        final int year = calendar1.get(Calendar.YEAR);
        final int month = calendar1.get(Calendar.MONTH) + 1;
        final int day = calendar1.get(Calendar.DATE);
        final int syear;
        final int smonth;
        final int sday;

        dateText.setText(global.getTestString());
        if (!dateText.getText().toString().equals("")) {
            weightText = findViewById(R.id.num_Text);
            setWeight(global.getTestString());
            getcal();
            String string_year = global.getTestString().substring(0, 4);
            String string_month;
            if (global.getTestString().substring(7).equals("0")) {
                string_month = global.getTestString().substring(7);
            } else {
                string_month = global.getTestString().substring(7, 9);
            }
            String string_day;
            if (global.getTestString().substring(12).equals("0")) {
                string_day = global.getTestString().substring(12);
            } else {
                string_day = global.getTestString().substring(12, 14);
            }

            syear = Integer.parseInt(string_year);
            smonth = Integer.parseInt(string_month);
            sday = Integer.parseInt(string_day);

            nextdayButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    neBtn(sday, smonth, syear);
                    setall();
                }
            });

            redayButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reBtn(sday, smonth, syear);
                    setall();
                }
            });
        }


        if (dateText.getText().toString().equals("")) {
            dateText.setText(String.format(dateformat, year, month, day));
            global.setTestString(String.format(dateformat, year, month, day));
            weightText = findViewById(R.id.num_Text);
            setWeight(String.format(dateformat, year, month, day));
            getcal();

            nextdayButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    neBtn(day, month, year);
                    setall();

                }
            });

            redayButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reBtn(day, month, year);
                    setall();
                }
            });
        }

        setall();


    }


    //関数定義
    public static int maxCal(int year2, int month2) {

        //取得処理
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year2);
        calendar.set(Calendar.MONTH, month2 - 1);

        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public void setWeight(String Date){
        if(datelists.contains(Date)){
            weightText.setText(weightlists.get(datelists.indexOf(Date)));
            bmiText.setText(bmilists.get(datelists.indexOf(Date)));
            if(usersex.equals("男性")) {
                bmrText.setText(bmrlists.get(datelists.indexOf(Date)));
            }else{
                bmrText.setText(bmrlists2.get(datelists.indexOf(Date)));
            }
        }else{
            weightText.setText("");
            bmiText.setText("");
            bmrText.setText("");
        }
    }

    public void neBtn(int dofm ,int mo, int yea){
        if(t == 0){
            t = 1;
        }
        if (t == 1) {
            dayOfMonth2 = dofm + 1;
            dayOfMonth1 = dayOfMonth2;
            monthb = mo;
            yearb = yea;
            if (dayOfMonth1 > maxCal(yearb, monthb) && monthb == 12) {
                dayOfMonth1 = 1;
                monthb = 1;
                yearb++;
                String date2 = String.format(dateformat, yearb, monthb, dayOfMonth1);
                dateText.setText(date2);
            } else if (dayOfMonth1 > maxCal(yearb, monthb) && monthb != 12) {
                dayOfMonth1 = 1;
                monthb = monthb + 1;
                String date2 = String.format(dateformat, yearb, monthb, dayOfMonth1);
                dateText.setText(date2);
            } else {
                String date2 = String.format(dateformat, yearb, monthb, dayOfMonth1);
                dateText.setText(date2);
            }
            textdate2 = dateText.getText().toString();
            global.setTestString(textdate2);
            setWeight(textdate2);
            getcal();
            t++;
        }else {
            dayOfMonth1 = dayOfMonth1 + 1;
            if(dayOfMonth1 > maxCal(yea , monthb ) && monthb == 12) {
                dayOfMonth1 = 1;
                monthb = 1;
                yearb++;
                String date2 = String.format(dateformat, yearb, monthb, dayOfMonth1);
                dateText.setText(date2);
            }else if(dayOfMonth1 > maxCal(yea, monthb) && monthb !=12) {
                dayOfMonth1 = 1;
                monthb = monthb + 1;
                String date2 = String.format(dateformat, yearb, monthb, dayOfMonth1);
                dateText.setText(date2);
            }else{
                String date2 = String.format(dateformat, yearb, monthb, dayOfMonth1);
                dateText.setText(date2);
            }
            textdate2 = dateText.getText().toString();
            global.setTestString(textdate2);
            setWeight(textdate2);
            getcal();

        }

    }

    public void reBtn(int dofm, int mo, int yea){
        if(t == 0){
            t = 1;
        }
        if (t == 1) {
            dayOfMonth2 = dofm - 1;
            dayOfMonth1 = dayOfMonth2;
            monthb = mo ;
            yearb = yea;
            if(dayOfMonth1 < 1 && monthb == 1) {
                monthb = 12;
                dayOfMonth1 = maxCal(yea,monthb);
                yearb--;
                String date2 = String.format(dateformat, yearb, monthb, dayOfMonth1);
                dateText.setText(date2);
            }else if(dayOfMonth1 < 1){
                monthb = monthb - 1;
                dayOfMonth1 = maxCal(yea,monthb);
                String date2 = String.format(dateformat, yearb, monthb , dayOfMonth1);
                dateText.setText(date2);
            }else {
                String date2 = String.format(dateformat, yearb, monthb, dayOfMonth1);
                dateText.setText(date2);
            }
            textdate2 = dateText.getText().toString();
            global.setTestString(textdate2);
            setWeight(textdate2);
            getcal();
            t++;

        } else {
            dayOfMonth1 = dayOfMonth1 - 1;
            if(dayOfMonth1 < 1 && monthb == 1) {
                monthb = 12;
                dayOfMonth1 = maxCal(yea, monthb);
                yearb--;
                String date2 = String.format(dateformat, yearb, monthb, dayOfMonth1);
                dateText.setText(date2);
            }else if(dayOfMonth1 < 1) {
                monthb = monthb - 1;
                dayOfMonth1 = maxCal(yea, monthb);
                String date2 = String.format(dateformat, yearb, monthb, dayOfMonth1);
                dateText.setText(date2);
            }else {
                String date2 = String.format(dateformat, yearb, monthb, dayOfMonth1);
                dateText.setText(date2);
            }
            textdate2 = dateText.getText().toString();
            global.setTestString(textdate2);
            setWeight(textdate2);
            getcal();
        }
    }

    public void setFood(String select_meal, int id){

        non_food_list.clear();
        non_cal_list.clear();
        list1.clear();
        list2.clear();

        if(helper2 == null){
            helper2 = new TestOpenHelper2(SubActivity.this);
        }
        try (SQLiteDatabase db = helper2.getWritableDatabase()) {
            // rawQueryというSELECT専用メソッドを使用してデータを取得する
            Cursor c = db.rawQuery("select date, meal_name, food_name, food_cal, food_gram, detail from testdb2", null);
            // Cursorの先頭行があるかどうか確認
            boolean next = c.moveToFirst();

            // 取得した全ての行を取得
            while (next) {
                // 取得したカラムの順番(0から始まる)と型を指定してデータを取得する
                String non_date = c.getString(0);
                String non_meal = c.getString(1);
                String rowdata1 = c.getString(4);
                String rowdata2 = c.getString(5);

                if(non_date.equals(global.getTestString()) && non_meal.equals(select_meal)) {
                    String non_food = c.getString(2);
                    double non_cal = c.getDouble(3);
                    non_food_list.add(non_food);
                    non_cal_list.add(non_cal);
                    list1.add(rowdata1);
                    list2.add(rowdata2);
                }
                // 次の行が存在するか確認
                next = c.moveToNext();
            }

            c.close();

        }

        NonScrollListView nonListView =  findViewById(id);
        CustomAdapter4 adapter4 = new CustomAdapter4(getApplicationContext(), R.layout.non_item, (ArrayList<String>) non_food_list, (ArrayList<Double>) non_cal_list, (ArrayList<String>) list1, (ArrayList<String>) list2);
        nonListView.setAdapter(adapter4);

        adapter4.notifyDataSetChanged();
    }

    public void setall(){
        setFood("朝食", R.id.lv_nonscroll_list);
        setFood("昼食", R.id.lv_nonscroll_list2);
        setFood("間食", R.id.lv_nonscroll_list3);
        setFood("夕食", R.id.lv_nonscroll_list4);
    }

    public void getcal() {
        if (helper2 == null) {
            helper2 = new TestOpenHelper2(SubActivity.this);
        }
        try (SQLiteDatabase db = helper2.getWritableDatabase()) {
            // rawQueryというSELECT専用メソッドを使用してデータを取得する
            Cursor c = db.rawQuery("select date, meal_name, food_name, food_cal from testdb2", null);
            // Cursorの先頭行があるかどうか確認
            boolean next = c.moveToFirst();
            double total_cal = 0.0;
            double non_cal1;
            // 取得した全ての行を取得
            while (next) {
                // 取得したカラムの順番(0から始まる)と型を指定してデータを取得する
                String non2_date = c.getString(0);

                if (non2_date.equals(global.getTestString())) {
                    non_cal1 = c.getDouble(3);
                    total_cal = total_cal + non_cal1;

                }
                // 次の行が存在するか確認
                next = c.moveToNext();
            }

            c.close();
            String tocal = (total_cal) + " kcal";
            calText.setText(tocal);
        }
    }
}
