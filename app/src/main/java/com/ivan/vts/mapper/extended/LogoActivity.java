package com.ivan.vts.mapper.extended;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.ivan.vts.mapper.map.MapsActivity;
import com.ivan.vts.mapper.settings.helper.ActivityMenu;

import java.util.Arrays;
import java.util.List;

public class LogoActivity extends AppCompatActivity {
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        new SplashScreen().execute();
        super.onCreate(savedInstanceState);
        try {
            List<Account> accounts = Arrays.asList(AccountManager.get(getApplicationContext()).getAccountsByType(Constants.ACCOUNT_TYPE));
            if (accounts.isEmpty()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Invalid email");
                builder.setMessage("");
                builder.setPositiveButton("Exit", (dialog, which) -> finish());
                builder.setCancelable(false);
                builder.show();
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
//
        } catch (SecurityException e) {
            finish();
        }
        ActivityMenu.getInstance().switchActivity(LogoActivity.this, MapsActivity.class, bundle);
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
            bundle.putInt(Constants.DEFAULT_THEME, theme);
            bundle.putInt(Constants.DEFAULT_LANGUAGE, language);
        }
    }
}
