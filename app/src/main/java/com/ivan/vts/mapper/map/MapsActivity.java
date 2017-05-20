package com.ivan.vts.mapper.map;

import android.accounts.AccountManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.ivan.vts.mapper.R;
import com.ivan.vts.mapper.extended.Constants;
import com.ivan.vts.mapper.extended.GsonParser;
import com.ivan.vts.mapper.settings.helper.ActivityMenu;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;

import javax.net.ssl.HttpsURLConnection;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MapsActivity extends DefaultGoogleApiClient implements OnMapReadyCallback, View.OnClickListener {

    protected SupportMapFragment mFragment;
    protected static String     url = "https://maps.googleapis.com/maps/api/directions/json?";
    private Button clearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

//        Pop up for invalid account
        try {
            if (Arrays.asList(AccountManager.get(getApplicationContext()).getAccountsByType("com.google")).isEmpty()) {
                new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Account name")
                        .setContentText("Application requires Google account.")
                        .setConfirmText("Exit application")
                        .setConfirmClickListener(sDialog -> exitApplication())
                        .show();
            }
        } catch (SecurityException e) {
            exitApplication();
        }

        mFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mFragment.getMapAsync(this);


        clearButton = (Button) findViewById(R.id.clear);
        clearButton.setOnClickListener(this);
        clearButton.setClickable(false);
        clearButton.setVisibility(View.GONE);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getBundle(intent);
        setMapStyle();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.DEFAULT_THEME, themeNo);
        bundle.putInt(Constants.DEFAULT_LANGUAGE, languageNo);
        return ActivityMenu.getInstance().switchActivity(this, item, bundle);
    }

    private void getBundle(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null && bundle.containsKey(Constants.LAT) && bundle.containsKey(Constants.LNG)) {
            clearButton.setClickable(true);
            clearButton.setVisibility(View.VISIBLE);
            String googleUrl = url + "origin=" + latLng.latitude + "," + latLng.longitude;
            googleUrl += "&destination=" + bundle.getDouble(Constants.LAT) + "," + bundle.getDouble(Constants.LNG);
            new AsyncDataTransfer().execute(googleUrl);
        }
    }

    @Override
    public void onMapReady(GoogleMap gMap) {
        mGoogleMap = gMap;
        setMapStyle();
        try {
            mGoogleMap.setMyLocationEnabled(true);
        } catch (SecurityException e) {
        }

        buildGoogleApiClient();

        mGoogleApiClient.connect();
    }

    protected synchronized void buildGoogleApiClient() {

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    private void exitApplication() {
        this.finish();
        System.exit(0);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onClick(View v) {
        mGoogleMap.clear();
        clearButton.setClickable(false);
        clearButton.setVisibility(View.GONE);
    }

    private class AsyncDataTransfer extends AsyncTask<String, Void, String> {
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(MapsActivity.this);
            dialog.setProgress(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("Loading route, please wait");
            dialog.setIndeterminate(true);
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpsURLConnection connection = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpsURLConnection) url.openConnection();
                InputStream inputStream = new BufferedInputStream(connection.getInputStream());
                return IOUtils.toString(inputStream, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null)
                    connection.disconnect();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            route = GsonParser.getInstance().parseRoute(result);
            createPolylineDirection(route);
            dialog.dismiss();
        }
    }

    private void setMapStyle() {
        MapStyleOptions mapOptions = null;
        if (themeNo == 0)
            mapOptions = MapStyleOptions.loadRawResourceStyle(this, R.raw.standard_style);
        else if (themeNo == 1)
            mapOptions = MapStyleOptions.loadRawResourceStyle(this, R.raw.retro_style);
        else if (themeNo == 2)
            mapOptions = MapStyleOptions.loadRawResourceStyle(this, R.raw.night_style);
        mGoogleMap.setMapStyle(mapOptions);
    }
}
