package com.example.bottomnav.tab;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.bottomnav.R;
import com.example.bottomnav.makeset.ExerciseSetting;
import com.example.bottomnav.others.Global;

public class FragmentTraining extends Fragment {
    int count = 1;
    Global global;
    AlertDialog dialog;
    private Button add_btn;
    private Button start_btn;
    private EditText weightText;
    private EditText timesText;
    private EditText intervalText;
    private ListView listView;
    private TextView content;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mytraining,container,false);

        listView = v.findViewById(R.id.setcontents_list);
        weightText = v.findViewById(R.id.weight_edit);
        timesText = v.findViewById(R.id.times_edit);
        intervalText = v.findViewById(R.id.interval_edit);
        add_btn = v.findViewById(R.id.setting_add_btn);
        start_btn = v.findViewById(R.id.timer_start_btn);
        content = v.findViewById(R.id.exercise_name);


        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        global = (Global) this.getActivity().getApplication();

        content.setText(global.getPlay());

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String weight = weightText.getText().toString();
                String times = timesText.getText().toString();

                if (weight.length() != 0 && times.length() != 0) {
                    weight = weightText.getText().toString();
                    times = timesText.getText().toString();
                    String string = count + "セット目　　" + weight + "kg   ×   " + times + "回";

                    adapter.add(string);
                    listView.setAdapter(adapter);

                    count++;
                } else {
                    Toast.makeText(getContext(), "重量および回数を入力してください", Toast.LENGTH_SHORT).show();
                }
            }
        });

        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("タイマーダイアログ");
                builder.setMessage(intervalText.getText().toString() + "秒");
                builder.setPositiveButton("OK", null);
                dialog = builder.create();
                dialog.show();
            }
        });
    }
}
