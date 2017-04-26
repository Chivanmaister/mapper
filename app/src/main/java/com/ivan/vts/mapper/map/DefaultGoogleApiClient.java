package com.ivan.vts.mapper.map;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;
import com.ivan.vts.mapper.extended.Route;

import java.util.List;

/**
 * Created by Chiefster on 3/4/2017.
 */

public class DefaultGoogleApiClient extends DefaultAppListener implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    protected   LocationRequest mLocationRequest;
    protected   GoogleApiClient mGoogleApiClient;
    protected   Polyline        polyline;
    protected   PolylineOptions rectOptions;
    protected   Location        mLastLocation;

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLastLocation = null;

        try {
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        } catch (SecurityException e) {
        }

        if (mLastLocation != null) {
            //place marker at current position
            //mGoogleMap.clear();
            latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
        }

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(5000); //5 seconds
        mLocationRequest.setFastestInterval(3000); //3 seconds
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        //mLocationRequest.setSmallestDisplacement(0.1F); //1/10 meter

        try {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        } catch (SecurityException e) {
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    public void createPolylineDirection(Route route) {
        rectOptions = new PolylineOptions();
        rectOptions.add(latLng);
        if (route.getPoint() != null) {
            List<LatLng> list = PolyUtil.decode(route.getPoint());
            for (LatLng latLng : list) {
                rectOptions.add(latLng);
            }
        }

        // Get back the mutable Polyline
        mGoogleMap.clear();
        polyline = mGoogleMap.addPolyline(rectOptions);
    }
}