package com.example.bottomnav.notify;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.bottomnav.R;

public class GraphActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        fragmentManager = getSupportFragmentManager();

        Toast.makeText(this, "期間を選択してください．", Toast.LENGTH_LONG).show();

        Toolbar toolbar = findViewById(R.id.toolbarh);

        toolbar.setNavigationIcon(android.R.drawable.ic_menu_revert);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), SubActivity.class);
                startActivity(intent);
            }

        });

    }

    public void onClickUserInfo(View view) {
        Fragment fragment = new One_Week_Fragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.contents,fragment );
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void onClickColor(View view) {
        Fragment fragment = new One_Month_Fragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.contents,fragment );
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void onClickDrink(View view) {
        Fragment fragment = new Three_Month_Fragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.contents,fragment );
        transaction.addToBackStack(null);
        transaction.commit();

    }

    public void onClickFood(View view) {
        Fragment fragment = new Six_Month_Fragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.contents,fragment );
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            // 戻るボタンの処理
            Intent intent2 = new Intent(getApplication(), SubActivity.class);
            startActivity(intent2);
        }
        return false;
    }
}
