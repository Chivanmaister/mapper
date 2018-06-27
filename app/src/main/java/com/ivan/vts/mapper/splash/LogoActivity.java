package com.ivan.vts.mapper.splash;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ivan.vts.mapper.extended.Constants;
import com.ivan.vts.mapper.extended.GsonParser;
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
            Bundle bundle = new Bundle();
            if (userId == 0) {
//                findUserAccount(bundle);
            }
            Setting setting = new Setting(theme, language);
            bundle.putSerializable(Constants.SETTINGS, setting);
            bundle.putInt(Constants.USER_ID, userId);
            ActivityMenu.getInstance().switchActivity(LogoActivity.this, MapsActivity.class, bundle);
        }
    }

    private void findUserAccount(Bundle bundle) {
        try {
            List<Account> accounts = Arrays.asList(AccountManager.get(getApplicationContext()).getAccountsByType(Constants.ACCOUNT_TYPE));
            if (accounts.isEmpty()) {
                showDialog();
            }
        } catch (SecurityException e) {
            finish();
        }
        final Integer[] userId = new Integer[1];
        RequestQueue findUserQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest findUserRequest = new StringRequest(Request.Method.POST, null,
                findUserResponse -> {
                    userId[0] = GsonParser.parseUserId(findUserResponse);
                },
                error -> {

                });
        findUserQueue.add(findUserRequest);
        if (userId[0] == null) {
            RequestQueue addUserQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest addUserRequest = new StringRequest(Request.Method.GET, null, addUserResponse -> {
                bundle.putInt(Constants.USER_ID, GsonParser.parseUserId(addUserResponse));
            }, error -> {

            });
            addUserQueue.add(addUserRequest);
        } else {
            bundle.putInt(Constants.USER_ID, userId[0]);
        }
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
