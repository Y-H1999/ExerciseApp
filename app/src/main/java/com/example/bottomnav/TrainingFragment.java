package com.example.bottomnav;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bottomnav.helper.MCHelper;
import com.example.bottomnav.helper.MenuHelper;
import com.example.bottomnav.makeset.MenuList;
import com.example.bottomnav.makeset.MySetMenu;
import com.example.bottomnav.makeset.SetMenuName;
import com.example.bottomnav.others.Global;
import com.example.bottomnav.setmenu.SetMenuActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrainingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrainingFragment extends Fragment {
    private ArrayAdapter<String> levelAdapter;
    private ArrayAdapter<String> partsAdapter;
    private Button levelButton;
    private Button partsButton;
    private AlertDialog alertDialog;
    private int selectedIndex = 0;
    private TextView levelText;
    private TextView partsText;
    private ImageButton imageButton1;
    private Button setmenuButton;
    private Button setmenuButton2;
    private ArrayList<String> setNames;
    private ListView setList;
    private MenuHelper helper;
    private SQLiteDatabase db;
    private Global global;
    private int locate;
    private MCHelper mcHelper;
    private SQLiteDatabase mcdb;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TrainingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TrainingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrainingFragment newInstance(String param1, String param2) {
        TrainingFragment fragment = new TrainingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_training, container, false);
        partsButton = (Button) v.findViewById(R.id.parts_button);
        levelButton = (Button) v.findViewById(R.id.level_button);
        levelText = (TextView) v.findViewById(R.id.selected_level_text);
        partsText = (TextView) v.findViewById(R.id.selected_parts_text);
        imageButton1 = (ImageButton) v.findViewById(R.id.abs_btn);
        setmenuButton = (Button) v.findViewById(R.id.setmenu_button);
        setmenuButton2 = (Button) v.findViewById(R.id.makeset_button);
        setList = (ListView) v.findViewById(R.id.setMenuList);
        global = (Global) this.getActivity().getApplication();
        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        setNames = new ArrayList<>();
        final ArrayList<String> idList = new ArrayList<>();

        readMCData();

        Log.e("TEST", String.valueOf(setNames));

        final ArrayAdapter adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1, setNames);

//        setList.setAdapter(adapter);

        adapter.notifyDataSetChanged();
//        setList.setOnItemClickListener(onItemClickListener);
        /*setList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("セットメニューを削除");
                builder.setMessage("削除しますか？");

                locate = (int) parent.getItemIdAtPosition(position);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.e("削除", String.valueOf(locate));
                                if (mcHelper == null) {
                                    mcHelper = new MCHelper(getContext());
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
                                    mcHelper = new MCHelper(getContext());
                                }
                                if (mcdb == null) {
                                    mcdb = mcHelper.getWritableDatabase();
                                }

                                *//*Cursor c = db.query("menudb",
                                        new String[] {"setmenu"},
                                        null,
                                        null,
                                        null,
                                        null,
                                        null);*//*

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

                                setList.setAdapter(adapter);
                                adapter.notifyDataSetChanged();

                                global.addDeleteCount();
                            }
                        }
                );

                AlertDialog dialog = builder.create();
                dialog.show();

                return true;
            }
        });
*/
        setmenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MenuList.class);
                startActivity(intent);
            }
        });

        setmenuButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SetMenuName.class);
                startActivity(intent);
            }
        });

        levelAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_single_choice);
        levelAdapter.add("初心者");
        levelAdapter.add("初級");
        levelAdapter.add("中級");
        levelAdapter.add("上級");
//        adapter.add("りゅう");

        // Spinner風のボタン
        levelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // AlertDialogで選択肢を表示
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        getActivity());
                builder.setTitle("レベルを選ぼう！");
                builder.setSingleChoiceItems(levelAdapter, selectedIndex,
                        onLevelDialogClickListener);
                alertDialog = builder.create();
                alertDialog.show();
            }
        });

        partsAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_single_choice);
        partsAdapter.add("背中");
        partsAdapter.add("全身");
        partsAdapter.add("お腹");
        partsAdapter.add("お尻");
        partsAdapter.add("有酸素運動");
        partsAdapter.add("ふくらはぎ");
        partsAdapter.add("腕");
        partsAdapter.add("太もも");

        partsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("部位を選ぼう！");
                builder.setSingleChoiceItems(partsAdapter, selectedIndex,
                        onPartsDialogClickListener);
                alertDialog = builder.create();
                alertDialog.show();
            }
        });

        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AbsExActivity.class);
                startActivity(intent);
            }
        });

    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            global.setSelectedPosition((int) parent.getItemIdAtPosition(position));
            String str = String.valueOf(global.getSelectedPosition());
            Log.e("Position", str);
            Intent intent = new Intent(
                    getContext(),
                    MySetMenu.class
            );
            startActivity(intent);
        }
    };

    private DialogInterface.OnClickListener onLevelDialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            // AlertDialogで選択された内容を保持
            selectedIndex = which;
            levelButton.setText(levelAdapter.getItem(which));
            levelText.setText("レベル：" + levelAdapter.getItem(which));
            alertDialog.dismiss();
        }
    };

    private DialogInterface.OnClickListener onPartsDialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            // AlertDialogで選択された内容を保持
            selectedIndex = which;
            partsButton.setText(partsAdapter.getItem(which));
            partsText.setText("パーツ：" + partsAdapter.getItem(which));
            alertDialog.dismiss();
        }
    };

    public void readMCData() {
        if (mcHelper == null) {
            mcHelper = new MCHelper(getContext());
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