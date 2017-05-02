package com.ivan.vts.mapper.map;

import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.ivan.vts.mapper.R;
import com.ivan.vts.mapper.extended.Constants;
import com.ivan.vts.mapper.extended.GsonParser;
import com.ivan.vts.mapper.settings.helper.ActivityMenu;

import java.util.Arrays;

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
                        .setConfirmClickListener(sDialog -> sDialog.dismissWithAnimation())
                        .show();
                exitApplication();
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return ActivityMenu.getInstance().switchActivity(this, item);
    }

    private void getBundle(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            clearButton.setClickable(true);
            clearButton.setVisibility(View.VISIBLE);
            String googleUrl = url + "origin=" + latLng.latitude + "," + latLng.longitude;
            googleUrl += "&destination=" + bundle.getDouble(Constants.LAT) + "," + bundle.getDouble(Constants.LNG);
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            StringRequest request = new StringRequest(Request.Method.GET, googleUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    route = GsonParser.getInstance().parseRoute(response);
                    createPolylineDirection(route);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            queue.add(request);
        }
    }

    @Override
    public void onMapReady(GoogleMap gMap) {
        mGoogleMap = gMap;
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
}
