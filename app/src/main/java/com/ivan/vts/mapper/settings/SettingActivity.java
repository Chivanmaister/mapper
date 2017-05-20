package com.ivan.vts.mapper.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Button saveButton = (Button) findViewById(R.id.saveSettings);
        Spinner languageSpinner = (Spinner) findViewById(R.id.languageSelect);
        RadioGroup themeRadioGroup = (RadioGroup) findViewById(R.id.themeRadioGroup);

        ((RadioButton) themeRadioGroup.getChildAt(themeNo)).setChecked(true);
        languageSpinner.setSelection(languageNo);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedTheme = 0;
                int selectedLanguage = 0;
                if (themeRadioGroup.getCheckedRadioButtonId() == R.id.defaultTheme)
                    selectedTheme = 0;
                else if (themeRadioGroup.getCheckedRadioButtonId() == R.id.retroTheme)
                    selectedTheme = 1;
                else
                    selectedTheme = 2;
                if (languageSpinner.getSelectedItemPosition() == 0)
                    selectedLanguage = 0;
                else if (languageSpinner.getSelectedItemPosition() == 1)
                    selectedLanguage = 1;
                else
                    selectedLanguage = 2;

                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt(Constants.DEFAULT_THEME, selectedTheme);
                editor.putInt(Constants.DEFAULT_LANGUAGE, selectedLanguage);
                editor.apply();
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.DEFAULT_THEME, selectedTheme);
                bundle.putInt(Constants.DEFAULT_LANGUAGE, selectedLanguage);
                Toast.makeText(SettingActivity.this, "Settings are saved", Toast.LENGTH_SHORT).show();
                ActivityMenu.getInstance().switchActivity(SettingActivity.this, MapsActivity.class, bundle);
            }
        });
    }
}
