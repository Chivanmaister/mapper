package com.ivan.vts.mapper.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
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

public class SettingActivity extends DefaultAppActivity {

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
            if (themeRadioGroup.getCheckedRadioButtonId() == R.id.defaultTheme)
                themeNo = 0;
            else if (themeRadioGroup.getCheckedRadioButtonId() == R.id.retroTheme)
                themeNo = 1;
            else
                themeNo = 2;
            if (languageSpinner.getSelectedItemPosition() == 0)
                languageNo = 0;
            else if (languageSpinner.getSelectedItemPosition() == 1)
                languageNo = 1;
            else
                languageNo = 2;

            preferences.edit().putInt(Constants.DEFAULT_THEME, themeNo).putInt(Constants.DEFAULT_LANGUAGE, languageNo).apply();
            Bundle bundle = getIntent().getExtras();
            Setting setting = new Setting(themeNo, languageNo);
            bundle.putSerializable(Constants.SETTINGS, setting);
            ActivityMenu.getInstance().switchActivity(SettingActivity.this, MapsActivity.class, bundle);
        });
    }
}
