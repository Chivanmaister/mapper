package com.ivan.vts.mapper.map;

import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ivan.vts.mapper.extended.Constants;
import com.ivan.vts.mapper.extended.GsonParser;
import com.ivan.vts.mapper.extended.Route;
import com.ivan.vts.mapper.extended.entities.Tracker;

import java.util.List;

import static com.ivan.vts.mapper.extended.Constants.HISTORY;

/**
 * Created by Ivan Marovic on 2/4/2017.
 */

public class DefaultAppListener extends DefaultAppActivity implements LocationListener {

    protected static LatLng latLng;
    protected GoogleMap mGoogleMap;
    protected Route route;
    private static final String mapperUrl = "";
    protected static boolean animateCamera = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onLocationChanged(Location location) {
        CameraPosition cameraPosition = null;
        latLng = new LatLng(location.getLatitude(), location.getLongitude());

        if (bundle.getSerializable(HISTORY) != null) {
            List<LatLng> history = GsonParser.parseTrackers(bundle.getString(HISTORY));
            if (animateCamera)
                cameraPosition = new CameraPosition.Builder().zoom(18).target(history.get(0)).build();
            animateCamera = false;
        } else {
            cameraPosition = new CameraPosition.Builder().zoom(18).target(latLng).build();
            animateCamera = true;
        }
        if (animateCamera)
            mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


        //TODO: uncomment and set sever to update user movement
//        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
//        StringRequest request = new StringRequest(Request.Method.POST, mapperUrl, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                route = GsonParser.getInstance().parseRoute(response);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(), "Unable to connect to Mapper server", Toast.LENGTH_SHORT).show();
//            }
//        });
//        queue.add(request);

        Tracker tracker = (Tracker) bundle.getSerializable(Constants.ROUTE_TRACKER);
        if (tracker != null && tracker.getPolylineSelected()) {
            mGoogleMap.addMarker(new MarkerOptions().position(latLng));
        }
    }
}
