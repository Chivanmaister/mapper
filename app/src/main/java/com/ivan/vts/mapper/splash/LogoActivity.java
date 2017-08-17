package com.ivan.vts.mapper.splash;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.ivan.vts.mapper.extended.Constants;
import com.ivan.vts.mapper.extended.entities.Setting;
import com.ivan.vts.mapper.map.MapsActivity;
import com.ivan.vts.mapper.settings.helper.ActivityMenu;

import java.util.Arrays;
import java.util.List;

public class LogoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        new SplashScreen().execute();
        super.onCreate(savedInstanceState);
    }

    private class SplashScreen extends AsyncTask<Void, Void, SharedPreferences> {

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
            int userId = preferences.getInt(Constants.USER_ID, 0);
            if (userId == 0) {
                userId = findUserAccount();
            }
            Setting setting = new Setting(theme, language);
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constants.SETTINGS, setting);
            bundle.putInt(Constants.USER_ID, userId);
            ActivityMenu.getInstance().switchActivity(LogoActivity.this, MapsActivity.class, bundle);
        }
    }

    private Integer findUserAccount() {
        try {
            List<Account> accounts = Arrays.asList(AccountManager.get(getApplicationContext()).getAccountsByType(Constants.ACCOUNT_TYPE));
            if (accounts.isEmpty()) {
                showDialog();
            }
        } catch (SecurityException e) {
            finish();
        }
        // TODO: uncomment and set server response for userId
//            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
//            StringRequest request = new StringRequest(Request.Method.POST, null,
//                    response -> {
//                        bundle.putInt(Constants.USER_ID, GsonParser.getInstance().parseUserId(response));
//                    },
//                    error -> {
//
//            });
//            queue.add(request);
        return 0;
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Invalid email");
        builder.setMessage("");
        builder.setPositiveButton("Exit", (dialog, which) -> finish());
        builder.setCancelable(false);
        builder.show();
    }
}
