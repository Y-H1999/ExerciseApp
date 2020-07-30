package com.example.bottomnav.makeset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bottomnav.MainActivity;
import com.example.bottomnav.R;
import com.example.bottomnav.helper.ContentsHelper;
import com.example.bottomnav.helper.MCHelper;
import com.example.bottomnav.list.AbsExercise;
import com.example.bottomnav.others.Global;

import java.util.List;
import java.util.Locale;

public class SetMenuTime extends AppCompatActivity {
    private TextView countDown;
    private CountDownTimer countDownTimer;
    private Button start_btn;
    private Button reset_btn;
    private Button end_btn;
    private ListView listView;
    private String contents;
    private MCHelper helper;
    private SQLiteDatabase db;
    private Global global;
    private List<String> list;

    private long millisTime;
    private boolean mTimerRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setmenu_time);

        findViews();

        global.setNumberTimes(0);

        millisTime = global.getIntervalTime();

        readData();

        list = AbsExercise.convertStringToArray(contents);

        final String[] items = list.toArray(new String[list.size()]);

        CustomAdapter adapter = new CustomAdapter(getApplicationContext(), R.layout.itemlist_times, items);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (view.getId()) {
                            case R.id.plus_btn:
                                break;
                            case R.id.minus_btn:
                                break;
                        }
                    }
                }
        );

        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        end_btn.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           Intent i = new Intent(SetMenuTime.this, MainActivity.class);
                                           startActivity(i);
                                           Log.e("TEST", String.valueOf(global.getNumberTimes()));
                                           int kcal = (int) (70 * (5.5 - 1) * global.getNumberTimes() * 1.05);
                                           Log.e("消費カロリー", String.valueOf(kcal));
                                       }
                                   }
        );

        updateCountDownText();
    }

    private void readData() {
        if (helper == null) {
            helper = new MCHelper(getApplicationContext());
        }

        if (db == null) {
            db = helper.getReadableDatabase();
        }

        Cursor c = db.query("mcdb",
                new String[] {"setcontents"},
                null,
                null,
                null,
                null,
                null);

        c.moveToFirst();
        Log.e("myset", String.valueOf(global.getSelectedPosition()));
        for (int i = 0; i < global.getSelectedPosition(); i++) {
            c.moveToNext();
        }

        contents = c.getString(0);

        Log.e("myset", contents);

        c.close();

        Log.e("contents", contents);
    }

    public void findViews() {
        countDown = findViewById(R.id.chronometer);
        start_btn = findViewById(R.id.start_btn);
        reset_btn = findViewById(R.id.reset_btn);
        end_btn = findViewById(R.id.end_btn);
        listView = findViewById(R.id.setmenu_time);
        global = (Global) this.getApplication();
    }

    private void startTimer(){
        countDownTimer = new CountDownTimer(millisTime,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                millisTime = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                mTimerRunning = false;
                start_btn.setText("スタート");
                Toast.makeText(SetMenuTime.this, "インターバルが終了しました。\nトレーニングを再開しましょう!!", Toast.LENGTH_LONG).show();
//                reset_btn.setVisibility(View.INVISIBLE);
                resetTimer();
            }
        }.start();

        mTimerRunning = true;
        start_btn.setText("一時停止");
//        reset_btn.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer(){
        System.out.println("一時停止処理前：" + mTimerRunning);
        countDownTimer.cancel();
        mTimerRunning = false;
        System.out.println("一時停止処理後：" + mTimerRunning);
        start_btn.setText("スタート");
        reset_btn.setVisibility(View.VISIBLE);
    }

    private void resetTimer(){
        millisTime = global.getIntervalTime();
        updateCountDownText();
        mTimerRunning = false;
        countDownTimer.cancel();
        start_btn.setText("スタート");
        start_btn.setVisibility(View.VISIBLE);
//        reset_btn.setVisibility(View.INVISIBLE);
    }

    private void updateCountDownText(){
        int minutes = (int)(millisTime/1000)/60;
        int seconds = (int)(millisTime/1000)%60;
        String timerLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        countDown.setText(timerLeftFormatted);
    }
}

class CustomAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private int resourceId;
    private String[] items;
    private Global global;
    private int[] ints;


    static class ViewHolder {
        ImageButton upButton;
        ImageButton downButton;
        TextView textView;
        TextView textTimes;
        int i;
    }

    CustomAdapter(Context context, int resourceId, String[] items) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resourceId = resourceId;
        this.items = items;
        global = (Global) Global.getAppContext();
        this.ints = new int[this.items.length];
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(resourceId, parent, false);

            holder = new ViewHolder();
            holder.upButton = convertView.findViewById(R.id.plus_btn);
            holder.downButton = convertView.findViewById(R.id.minus_btn);
            holder.textView = convertView.findViewById(R.id.textView);
            holder.textTimes = convertView.findViewById(R.id.times_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(items[position]);
        holder.textTimes.setText(String.valueOf(ints[position]));

        holder.upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) parent).performItemClick(v, position, R.id.plus_btn);
//                holder.i += 1;
//                global.addNumberTimes();
//                Log.e("Test", String.valueOf(holder.i));
//                holder.textTimes.setText(String.valueOf(holder.i));
                ints[position] += 1;
                global.addNumberTimes();
                holder.textTimes.setText(String.valueOf(ints[position]));
            }
        });
        holder.downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView) parent).performItemClick(v, position, R.id.minus_btn);
//                if (holder.i != 0) {
//                    holder.i -= 1;
//                    global.removeNumberTimes();
//                }
//                holder.textTimes.setText(String.valueOf(holder.i));
                if (ints[position] != 0) {
                    ints[position]--;
                    global.removeNumberTimes();
                }
                holder.textTimes.setText(String.valueOf(ints[position]));
            }
        });

        return convertView;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}