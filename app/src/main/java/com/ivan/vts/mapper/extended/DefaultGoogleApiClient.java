package com.ivan.vts.mapper.extended;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.ivan.vts.mapper.R;

/**
 * Created by Chiefster on 3/4/2017.
 */

public class DefaultGoogleApiClient extends DefaultAppActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    protected   LocationRequest mLocationRequest;
    protected   GoogleApiClient mGoogleApiClient;
    protected   Route           route;
    protected   Polyline        polyline;
    protected static String     url = "https://maps.googleapis.com/maps/api/directions/json?";
    protected PolylineOptions rectOptions;
    protected Location mLastLocation;

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

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                String googleUrl = url + "origin=" + latLng.latitude + "," + latLng.longitude;
                googleUrl += "&destination=" + place.getLatLng().latitude + "," + place.getLatLng().longitude;
                //TODO implement DataTransfer.class
            }

            @Override
            public void onError(Status status) {
            }
        });
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
        for (LatLng latLng : route.getEndLocation()) {
            rectOptions.add(latLng);
        }

        // Get back the mutable Polyline
        mGoogleMap.clear();
        polyline = mGoogleMap.addPolyline(rectOptions);
    }
}
