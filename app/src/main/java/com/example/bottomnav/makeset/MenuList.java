package com.example.bottomnav.makeset;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bottomnav.R;
import com.example.bottomnav.helper.MCHelper;
import com.example.bottomnav.list.AbsExercise;
import com.example.bottomnav.others.Global;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL;

public class MenuList extends AppCompatActivity {
    private ListView listView;
    private ArrayList<String> setNames;
    private MCHelper mcHelper;
    private SQLiteDatabase mcdb;
    private Global global;
    private ArrayList<String> idList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);

        findView();

        setNames = new ArrayList<>();

        readMCData();

        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, setNames);

        listView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        listView.setOnItemClickListener(onItemClickListener);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MenuList.this);
                builder.setTitle("セットメニューを削除");
                builder.setMessage("削除しますか？");

                final int locate = (int) parent.getItemIdAtPosition(position);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.e("削除", String.valueOf(locate));
                                if (mcHelper == null) {
                                    mcHelper = new MCHelper(getApplicationContext());
                                }

                                if (mcdb == null) {
                                    mcdb = mcHelper.getWritableDatabase();
                                }

                                mcdb.delete("mcdb", "_id = ?", new String[]{String.valueOf(locate + global.getDeleteCount())});

//                                helper.selectedDelete(String.valueOf(setList.get(position)));

                                mcdb.close();
                                mcdb = null;

                                setNames.clear();

                                if (mcHelper == null) {
                                    mcHelper = new MCHelper(getApplicationContext());
                                }
                                if (mcdb == null) {
                                    mcdb = mcHelper.getWritableDatabase();
                                }

                                /*Cursor c = db.query("menudb",
                                        new String[] {"setmenu"},
                                        null,
                                        null,
                                        null,
                                        null,
                                        null);*/

                                Cursor c = mcdb.rawQuery("select _id, setmenu from mcdb", null);

//                                if (c.moveToFirst()) {
//                                    do {
//                                        setNames.add(c.getString(0));
//                                    } while (c.moveToNext());
//                                }
//                                Log.e("Cursor", String.valueOf(c.getCount()));

                                boolean next = c.moveToFirst();

                                while (next) {
                                    String _id = c.getString(0);
                                    String rawData = c.getString(1);

                                    idList.add(_id);
                                    setNames.add(rawData);

                                    next = c.moveToNext();

                                    Log.e("_id", _id);
                                }

                                c.close();

                                mcdb.close();
                                mcdb = null;

                                listView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();

                                global.addDeleteCount();
                            }
                        }
                );

                builder.setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

                return true;
            }
        });
    }

    public void findView() {
        listView = findViewById(R.id.setMenuList);
        global = (Global) this.getApplication();
    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            global.setSelectedPosition((int) parent.getItemIdAtPosition(position));
            String str = String.valueOf(global.getSelectedPosition());
            Log.e("Position", str);
            Intent intent = new Intent(
                    MenuList.this,
                    MySetMenu.class
            );
            startActivity(intent);
        }
    };

    public void readMCData() {
        if (mcHelper == null) {
            mcHelper = new MCHelper(getApplicationContext());
        }

        if (mcdb == null) {
            mcdb = mcHelper.getReadableDatabase();
        }

        Cursor c = mcdb.query("mcdb",
                new String[] {"setmenu"},
                null,
                null,
                null,
                null,
                null);

        c.moveToFirst();
        for (int i = 0; i < c.getCount(); i++) {
            setNames.add(c.getString(0));
            c.moveToNext();
        }
        c.close();
    }
}