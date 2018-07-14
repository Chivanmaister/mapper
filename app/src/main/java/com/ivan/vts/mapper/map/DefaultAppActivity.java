package com.ivan.vts.mapper.map;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import com.ivan.vts.mapper.R;
import com.ivan.vts.mapper.extended.Constants;
import com.ivan.vts.mapper.extended.entities.Setting;

import java.util.Locale;

/**
 * Created by Chiefster on 4/5/2017.
 */

public abstract class DefaultAppActivity extends AppCompatActivity {

    protected int themeNo;
    protected int languageNo;
    protected Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLanguageAndTheme();
        super.onCreate(savedInstanceState);
        bundle = getIntent().getExtras();
    }

    private void setTheme(Setting setting) {
        themeNo = setting.getThemeNo();
        if (themeNo == 0)
            setTheme(R.style.LigthTheme);
        else if (themeNo == 1)
            setTheme(R.style.RetroTheme);
        else
            setTheme(R.style.DarkTheme);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setLanguageAndTheme();
        super.onNewIntent(intent);
    }

    @SuppressWarnings("all")
    private void setLanguage(Setting setting) {
        languageNo = setting.getLanguageNo();
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

    private void setLanguageAndTheme() {
        SharedPreferences preference = getSharedPreferences(Constants.APP_NAME,MODE_PRIVATE);
        Setting setting = new Setting(preference.getInt(Constants.DEFAULT_THEME, 0), preference.getInt(Constants.DEFAULT_LANGUAGE, 0));
        setTheme(setting);
        setLanguage(setting);
    }
}
