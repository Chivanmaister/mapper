package com.ivan.vts.mapper.map;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ivan.vts.mapper.R;
import com.ivan.vts.mapper.extended.Constants;

/**
 * Created by Chiefster on 4/5/2017.
 */

public abstract class DefaultAppActivity extends AppCompatActivity {

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferences = getSharedPreferences(Constants.APP_NAME, MODE_PRIVATE);
        int theme = preferences.getInt("primaryTheme", 0);
//        int language = preferences.getInt("primaryLanguage", 0);

        if (theme == 0)
            setTheme(R.style.LigthTheme);
//        if (theme == 2)
        else
            setTheme(R.style.DarkTheme);

        super.onCreate(savedInstanceState);
    }
}
