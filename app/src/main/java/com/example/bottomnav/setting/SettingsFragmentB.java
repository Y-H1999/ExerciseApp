package com.example.bottomnav.setting;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreferenceCompat;


import com.example.bottomnav.R;

import java.util.Objects;

public class SettingsFragmentB extends PreferenceFragmentCompat {

    private boolean switch_pass;
    private SwitchPreferenceCompat themePreference;


    public static SettingsFragmentB newInstance(String rootKey) {
        SettingsFragmentB fragment = new SettingsFragmentB();
        Bundle bundle = new Bundle();
        bundle.putString(PreferenceFragmentCompat.ARG_PREFERENCE_ROOT, rootKey);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        onCreateAppearancePreferences();

        showDialog("preference_stop",  "退会しますか？");
        showDialog("preference_logout",  "ログアウトしますか？");

        switch (rootKey) {
//            case "preference_appearance":
//                onCreateAppearancePreferences();
//                break;
//            case "preference_others":
//                break;
            case "preference_alert":
                break;
            case "preference_user_info":
                break;
            case "preference_pass":
                break;
            case "preference_res":
                break;
            case "preference_stop":
                break;
            case "preference_rule":
                break;
            case "preference_alert2":
                break;
            case "preference_adv":
                break;
            case "preference_logout":
                break;
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        // ActionBarのタイトルに現在表示中のPreferenceScreenのタイトルをセット
        assert getArguments() != null;
        String rootKey = getArguments().getString(PreferenceFragmentCompat.ARG_PREFERENCE_ROOT);
        assert rootKey != null;
        requireActivity().setTitle(Objects.requireNonNull(findPreference(rootKey)).getTitle());
    }

    private void onCreateAppearancePreferences() {
        themePreference = findPreference("preference_pass");
        assert themePreference != null;
        try {
            themePreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object value) {
                    SharedPreferences defaultSharedPreferencesAlert2 = PreferenceManager.getDefaultSharedPreferences(requireActivity());
                    switch_pass = defaultSharedPreferencesAlert2.getBoolean("preference_pass", false);
                    Intent nextActivity = new Intent(
                            getContext(),
                            Setting_Pass_Activity.class);

                    if (!switch_pass) {
                        themePreference.setIntent(nextActivity);
                    }
                    return true;
                }
            });
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    private void showDialog(String key, final String title){

        PreferenceScreen Ps = findPreference(key);
        assert Ps != null;
        try {
            Ps.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    new AlertDialog.Builder(requireActivity())
                            .setTitle(title)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // OK button pressed
                                }
                            })
                            .setNegativeButton("Cancel", null)
                            .show();
                    return true;
                }
            });
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

}
