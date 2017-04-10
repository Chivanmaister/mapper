package com.ivan.vts.mapper.map;

import android.accounts.AccountManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.ivan.vts.mapper.R;
import com.ivan.vts.mapper.extended.DefaultGoogleApiClient;
import com.ivan.vts.mapper.settings.helper.Setting;
import java.util.Arrays;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class MapsActivity extends DefaultGoogleApiClient implements OnMapReadyCallback {

    protected SupportMapFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //Pop up for invalid account
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return Setting.getInstance().helloWorld(this, item);
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
}
