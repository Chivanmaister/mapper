package com.ivan.vts.mapper.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ivan.vts.mapper.R;
import com.ivan.vts.mapper.extended.Constants;
import com.ivan.vts.mapper.map.DefaultAppActivity;
import com.ivan.vts.mapper.map.MapsActivity;
import com.ivan.vts.mapper.settings.helper.ActivityMenu;

public class SettingActivity extends DefaultAppActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences = getSharedPreferences(Constants.APP_NAME, MODE_PRIVATE);
//        int language = preferences.getInt("primaryLanguage", 0);
        int theme = preferences.getInt("primaryTheme", 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Button saveButton = (Button) findViewById(R.id.saveSettings);
//        Spinner languageSpiner = (Spinner) findViewById(R.id.languageSelect);
        RadioGroup themeRadioGrop = (RadioGroup) findViewById(R.id.themeRadioGroup);

        ((RadioButton) themeRadioGrop.getChildAt(theme)).setChecked(true);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedTheme = 0;
//                String selectedLanguage = (String) languageSpiner.getSelectedItem();
                if (themeRadioGrop.getCheckedRadioButtonId() == R.id.defaultTheme)
                    selectedTheme = 0;
                if (themeRadioGrop.getCheckedRadioButtonId() == R.id.retroTheme)
                    selectedTheme = 1;
                else if (themeRadioGrop.getCheckedRadioButtonId() == R.id.nightTheme)
                    selectedTheme = 2;

                SharedPreferences.Editor editor = preferences.edit();
//                editor.putString("primaryLanguage", selectedLanguage);
                editor.putInt("primaryTheme", selectedTheme);
                editor.commit();
                Toast.makeText(SettingActivity.this, "Settings are saved", Toast.LENGTH_SHORT).show();
                ActivityMenu.getInstance().switchActivity(SettingActivity.this, MapsActivity.class);
            }
        });

    }
}
