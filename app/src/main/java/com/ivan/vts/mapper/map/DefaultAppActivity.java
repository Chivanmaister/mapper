package com.ivan.vts.mapper.map;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import com.ivan.vts.mapper.R;
import com.ivan.vts.mapper.extended.Constants;

import java.util.Locale;

/**
 * Created by Chiefster on 4/5/2017.
 */

public abstract class DefaultAppActivity extends AppCompatActivity {

    protected int themeNo;
    protected int languageNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(getIntent());
        super.onCreate(savedInstanceState);
        setLanguage(getIntent());
    }

    private void setTheme(Intent intent) {
        themeNo = intent.getExtras().getInt(Constants.DEFAULT_THEME, 0);
        if (themeNo == 0)
            setTheme(R.style.LigthTheme);
        else if (themeNo == 1)
            setTheme(R.style.RetroTheme);
        else
            setTheme(R.style.DarkTheme);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        setTheme(intent);
        setLanguage(intent);
        super.onNewIntent(intent);
    }

    @SuppressWarnings("deprecated")
    private void setLanguage(Intent intent) {
        languageNo = intent.getExtras().getInt(Constants.DEFAULT_LANGUAGE, 0);
        Locale locale;
        if (languageNo == 0)
            locale = new Locale("en");
        else if (languageNo == 1)
            locale = new Locale("sr");
        else
            locale = new Locale("hu");
        Resources resources = getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, displayMetrics);
    }
}
