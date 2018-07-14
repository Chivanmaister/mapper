package com.ivan.vts.mapper.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.ivan.vts.mapper.R;
import com.ivan.vts.mapper.extended.Constants;
import com.ivan.vts.mapper.extended.entities.Setting;
import com.ivan.vts.mapper.map.DefaultAppActivity;
import com.ivan.vts.mapper.map.MapsActivity;
import com.ivan.vts.mapper.settings.helper.ActivityMenu;

import java.util.HashMap;
import java.util.Map;

public class SettingActivity extends DefaultAppActivity {

    @SuppressWarnings("all")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(Constants.APP_NAME, MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Button saveButton = (Button) findViewById(R.id.saveSettings);
        Spinner languageSpinner = (Spinner) findViewById(R.id.languageSelect);
        RadioGroup themeRadioGroup = (RadioGroup) findViewById(R.id.themeRadioGroup);

        ((RadioButton) themeRadioGroup.getChildAt(themeNo)).setChecked(true);
        languageSpinner.setSelection(languageNo);

        saveButton.setOnClickListener(view -> {
            Map<Integer, Integer> themeMap = new HashMap<>();
            themeMap.put(R.id.defaultTheme, 0);
            themeMap.put(R.id.retroTheme, 1);
            themeMap.put(R.id.nightTheme, 2);

            preferences.edit().putInt(Constants.DEFAULT_THEME, themeMap.get(themeRadioGroup.getCheckedRadioButtonId())).putInt(Constants.DEFAULT_LANGUAGE, languageSpinner.getSelectedItemPosition()).apply();
            Bundle bundle = getIntent().getExtras();
            Setting setting = new Setting(themeMap.get(themeRadioGroup.getCheckedRadioButtonId()), languageSpinner.getSelectedItemPosition());
            bundle.putSerializable(Constants.SETTINGS, setting);
            ActivityMenu.getInstance().switchActivity(SettingActivity.this, MapsActivity.class, bundle);
        });
    }
}
