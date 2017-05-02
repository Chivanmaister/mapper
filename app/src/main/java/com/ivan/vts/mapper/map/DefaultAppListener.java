package com.ivan.vts.mapper.map;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.ivan.vts.mapper.extended.Route;

/**
 * Created by Ivan Marovic on 2/4/2017.
 */

public class DefaultAppListener extends AppCompatActivity implements LocationListener {

    protected Marker currLocationMarker;
    protected static LatLng latLng;
    protected GoogleMap mGoogleMap;
    protected Route route;
    private static final String mapperUrl = "";

    @Override
    public void onLocationChanged(Location location) {
        if (currLocationMarker != null) {
            currLocationMarker.remove();
        }
        latLng = new LatLng(location.getLatitude(), location.getLongitude());

        //zoom to current position:
        CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(14).build();

        mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

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
    }
}
