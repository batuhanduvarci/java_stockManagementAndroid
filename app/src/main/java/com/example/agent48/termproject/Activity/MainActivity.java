package com.example.agent48.termproject.Activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.agent48.termproject.Menu.MainMenu;
import com.example.agent48.termproject.R;

/**
 * Created by Agent48 on 9.04.2018.
 */

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this,MainMenu.class));
                overridePendingTransition(R.animator.right_in,R.animator.left_out);
            }
        },2000);

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.main_activity_layout;
    }
}

