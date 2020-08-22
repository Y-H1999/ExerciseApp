package com.example.bottomnav.setting;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;

import com.example.bottomnav.R;

import java.util.Objects;

public class SettingActivity extends AppCompatActivity implements PreferenceFragmentCompat.OnPreferenceStartScreenCallback  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);

        // Toolbarの設定
        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // savedInstanceStateがnullでない場合は前回のFragmentが自動で復元されるのでnullの場合のみ処理
        if (savedInstanceState == null) {
            // トップ画面のFragmentを表示
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, SettingsFragmentB.newInstance("preference_root"))
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // ActionBarの矢印がクリックされたとき、Backボタンと同等の処理をする
            // 前のFragmentに戻るのではなくActivity自体を終了させたい場合は代わりに finish();
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // PreferenceScreenがクリックされた時に呼び出されます
    @Override
    public boolean onPreferenceStartScreen(PreferenceFragmentCompat caller, PreferenceScreen pref) {
        // Fragmentの切り替えと、addToBackStackで戻るボタンを押した時に前のFragmentに戻るようにする
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, SettingsFragmentB.newInstance(pref.getKey()))
                .addToBackStack(null)
                .commit();
        return true;
    }

}
