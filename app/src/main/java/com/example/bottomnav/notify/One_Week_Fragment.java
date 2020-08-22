package com.example.bottomnav.notify;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.example.bottomnav.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

public class One_Week_Fragment extends Fragment {

    private WeightOpenHelper helper;
    private ArrayList<LineDataSet> dataSets = new ArrayList<>();
    // X軸の値
    private ArrayList<String> xValues = new ArrayList<>();
    //Y軸の値
    private ArrayList<Float> yValues = new ArrayList<>();
    // value
    ArrayList<Entry> value = new ArrayList<>();
    ArrayList<String> date = new ArrayList<>();
    ArrayList<String> date2 = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one_week, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LineChart lineChart = view.findViewById(R.id.line_chart);

        cal();
        set_value(6);
        float max_value = Collections.max(yValues);
        float min_value = Collections.min(yValues);

        int i;
        for(i = 0; i < xValues.size(); ++i) {
            value.add(new Entry(yValues.get(i), i));
        }

        lineChart.notifyDataSetChanged();
        lineChart.invalidate();

        lineChart.setDescription("");
        lineChart.setScaleEnabled(false);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(8f);

        lineChart.getAxisRight().setEnabled(false);
        YAxis leftAxis = lineChart.getAxisLeft();
        // Y軸最大最小設定
        leftAxis.setStartAtZero(false);
        leftAxis.setAxisMaxValue((float) (max_value + 3.0));
        leftAxis.setAxisMinValue((float) (min_value - 3.0));
        leftAxis.setDrawGridLines(false);
        LineDataSet valueDataSet = new LineDataSet(value, "体重");
        valueDataSet.setDrawValues(false);
        valueDataSet.setDrawCircles(false);
        valueDataSet.setLineWidth(2f);
        dataSets.add(valueDataSet);

        lineChart.setData(new LineData(xValues, dataSets));

    }

    public void cal(){
        // Date型のフォーマットの設定
        DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyy / MM / dd");
        DateTimeFormatter dtFormat2 = DateTimeFormatter.ofPattern("MM/dd");
        // 加算される現在時間の取得(Calender型)
        LocalDateTime d = LocalDateTime.now();
        String str1 = d.format(dtFormat);
        String str2 = d.format(dtFormat2);
        date.add(str1);
        date2.add(str2);
        for(int t = 1; t <= 6; ++t) {

            // 加算される現在時間の取得(String型)
            str1 = d.minusDays(t).format(dtFormat);
            str2 = d.minusDays(t).format(dtFormat2);
            date.add(str1);
            date2.add(str2);
        }
    }

    public void set_value(int s){
        if (helper == null) {
            helper = new WeightOpenHelper(getActivity());
        }
        try (SQLiteDatabase db = helper.getWritableDatabase()) {
            // rawQueryというSELECT専用メソッドを使用してデータを取得する
            Cursor c1 = db.rawQuery("select _weightid, weight, date from weightdb", null);
            Cursor c2 = db.rawQuery("select _weightid, weight, date from weightdb", null);
            // Cursorの先頭行があるかどうか確認
            boolean next1 = c1.moveToFirst();
            boolean next2 = c2.moveToFirst();


            while (s != -1) {
                String bool = "true";

                while(next1) {
                    if (date.get(s).equals(c1.getString(2))) {
                        xValues.add(date2.get(s));
                        yValues.add(Float.parseFloat(c1.getString(1)));
                        bool = "false";
                    }
                    next1 = c1.moveToNext();
                }
                next1 = c1.moveToFirst();

                if(bool.equals("true")) {
                    while (next2) {
                        if (!date.get(s).equals(c2.getString(2))) {
                            if (c2.isLast()) {
                                xValues.add(date2.get(s));
                                if (yValues.size() == 0 || yValues.get(yValues.size() - 1) == 70f) {
                                    yValues.add(70f);
                                } else {
                                    yValues.add(yValues.get(yValues.size() - 1));
                                }
                            }
                        }
                        next2 = c2.moveToNext();
                    }
                    next2 = c2.moveToFirst();
                }
                --s;
            }

            c1.close();
            c2.close();

            if(yValues.size() == 0){
                yValues.add(50f);
            }
        }
    }

}