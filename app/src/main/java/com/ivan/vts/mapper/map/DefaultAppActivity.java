package com.ivan.vts.mapper.map;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ivan.vts.mapper.R;

/**
 * Created by Chiefster on 4/5/2017.
 */

public abstract class DefaultAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences = getSharedPreferences("theme", MODE_PRIVATE);
        int prefereceNumber = preferences.getInt("primaryTheme", 0);
        if (prefereceNumber == 0)
            setTheme(R.style.DarkTheme);
        else
            setTheme(R.style.LigthTheme);

        super.onCreate(savedInstanceState);
    }
}
