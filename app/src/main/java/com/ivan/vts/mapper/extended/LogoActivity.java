package com.ivan.vts.mapper.extended;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ivan.vts.mapper.map.MapsActivity;
import com.ivan.vts.mapper.settings.helper.ActivityMenu;

public class LogoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        new SplashScreen().execute();
        super.onCreate(savedInstanceState);
    }

    private class SplashScreen extends AsyncTask<Void, Void, SharedPreferences> {

//        @Override
//        protected void onPreExecute() {
//        }

        @Override
        protected SharedPreferences doInBackground(Void... params) {
            SharedPreferences preferences;
            preferences = getSharedPreferences(Constants.APP_NAME, MODE_PRIVATE);
            return preferences;
        }

        @Override
        protected void onPostExecute(SharedPreferences preferences) {
            int theme = preferences.getInt(Constants.DEFAULT_THEME, 0);
            int language = preferences.getInt(Constants.DEFAULT_LANGUAGE, 0);
            Bundle bundle = new Bundle();
            bundle.putInt(Constants.DEFAULT_THEME, theme);
            bundle.putInt(Constants.DEFAULT_LANGUAGE, language);
            ActivityMenu.getInstance().switchActivity(LogoActivity.this, MapsActivity.class, bundle);
        }
    }
}
