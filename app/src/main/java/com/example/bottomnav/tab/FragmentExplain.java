package com.example.bottomnav.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bottomnav.R;
import com.example.bottomnav.others.Global;

public class FragmentExplain extends Fragment {
    private TextView explain;
    Global global;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_explain,container,false);

        explain = v.findViewById(R.id.explain_txt);
        global = (Global) this.getActivity().getApplication();

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        String[] exercise_abs = getResources().getStringArray(R.array.array_abs);
        String[] explains = getResources().getStringArray(R.array.array_explain);
        String[] codes = getResources().getStringArray(R.array.array_code);

        for (int i = 0; i < exercise_abs.length; i++) {
            if (global.getPlay().equals(exercise_abs[i])) {
                explain.setText(explains[i] + "\n識別コードは " + codes[i] + " です");
                break;
            }
        }
    }

}
