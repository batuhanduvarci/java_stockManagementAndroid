package com.example.agent48.termproject.Activity;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Agent48 on 9.04.2018.
 */

public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
    }

    protected abstract int getLayoutResource();
}

