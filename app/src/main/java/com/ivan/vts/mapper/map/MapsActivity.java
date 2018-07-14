package com.ivan.vts.mapper.map;

import android.app.AlertDialog;
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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.ivan.vts.mapper.R;
import com.ivan.vts.mapper.extended.Constants;
import com.ivan.vts.mapper.extended.GsonParser;
import com.ivan.vts.mapper.extended.entities.Setting;
import com.ivan.vts.mapper.settings.helper.ActivityMenu;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import static com.ivan.vts.mapper.extended.GsonParser.parseRoute;

public class MapsActivity extends DefaultGoogleApiClient implements OnMapReadyCallback {

    protected SupportMapFragment mFragment;
    protected static String url = "https://maps.googleapis.com/maps/api/directions/json?";
    private Button clearRouteButton;
    private Button clearTrackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mFragment.getMapAsync(this);

        clearRouteButton = (Button) findViewById(R.id.clearRoute);
        clearTrackButton = (Button) findViewById(R.id.clearTrack);

        clearRouteButton.setClickable(false);
        clearRouteButton.setVisibility(View.GONE);
        clearTrackButton.setClickable(false);
        clearTrackButton.setVisibility(View.GONE);

        clearRouteButton.setOnClickListener(view -> {
            routePolyline.remove();
            clearRouteButton.setClickable(false);
            clearRouteButton.setVisibility(View.GONE);
            bundle.remove(Constants.LAT);
            bundle.remove(Constants.LNG);
        });

        clearTrackButton.setOnClickListener(view -> {
            trackerPolyline.remove();
            clearTrackButton.setClickable(false);
            clearTrackButton.setVisibility(View.GONE);
            bundle.remove(Constants.HISTORY);
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getBundle(intent);
        setMapStyle();
        clearRouteButton.setOnClickListener(view -> {
            routePolyline.remove();
            clearRouteButton.setClickable(false);
            clearRouteButton.setVisibility(View.GONE);
            bundle.remove(Constants.LAT);
            bundle.remove(Constants.LNG);
        });

        clearTrackButton.setOnClickListener(view -> {
            trackerPolyline.remove();
            clearTrackButton.setClickable(false);
            clearTrackButton.setVisibility(View.GONE);
            bundle.remove(Constants.HISTORY);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Setting setting = new Setting(themeNo, languageNo);
        bundle.putSerializable(Constants.SETTINGS, setting);
        return ActivityMenu.getInstance().switchActivity(this, item, bundle);
    }

    private void getBundle(Intent intent) {
        bundle = intent.getExtras();
        if (bundle != null) {
            if (bundle.containsKey(Constants.LAT) && bundle.containsKey(Constants.LNG)) {
                String googleUrl = url + "origin=" + latLng.latitude + "," + latLng.longitude;
                googleUrl += "&destination=" + bundle.getDouble(Constants.LAT) + "," + bundle.getDouble(Constants.LNG);
                new AsyncDataTransfer().execute(googleUrl);
                bundle.remove(Constants.LAT);
                bundle.remove(Constants.LNG);
            }
            if (bundle.containsKey(Constants.HISTORY)) {
                clearTrackButton.setVisibility(View.VISIBLE);
                List<LatLng> latLngList = GsonParser.parseTrackers(bundle.getString(Constants.HISTORY));
                trackerPolyline = mGoogleMap.addPolyline(new PolylineOptions().addAll(latLngList));
                bundle.remove(Constants.HISTORY);
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap gMap) {
        mGoogleMap = gMap;
        setMapStyle();
        try {
            mGoogleMap.setMyLocationEnabled(true);
        } catch (SecurityException ignored) {
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

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @SuppressWarnings("all")
    private class AsyncDataTransfer extends AsyncTask<String, Void, String> {
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(MapsActivity.this);
            dialog.setProgress(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage(getApplicationContext().getResources().getString(R.string.loadingRoute));
            dialog.setIndeterminate(true);
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            InputStream inputStream = null;
            String result = null;
            HttpsURLConnection connection = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpsURLConnection) url.openConnection();
//                inputStream = new BufferedInputStream(connection.getInputStream());
                inputStream = connection.getInputStream();
                result = IOUtils.toString(inputStream, "UTF-8");
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null)
                    connection.disconnect();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            route = parseRoute(result);
            createPolylineDirection(route);
            if (route.getStatus().equalsIgnoreCase("ok")) {
                clearRouteButton.setClickable(true);
                clearRouteButton.setVisibility(View.VISIBLE);
            }
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

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.exit)
                .setMessage(R.string.exit_text)
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    sendData = false;
                    MapsActivity.this.finish();
                }).create().show();

    }
}
