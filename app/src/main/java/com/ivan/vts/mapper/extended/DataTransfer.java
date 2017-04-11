package com.ivan.vts.mapper.extended;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by Chiefster on 10/4/2017.
 */

public class DataTransfer {

    private Route route;
    private Road road;

    public Route googleApiResponseToRoute(Context context, Integer method, String url) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                route = GsonParser.getInstance().parseRoute(response);
                Log.d(route.getStatus(), "status");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        queue.add(request);
        return route;
    }

    public Road googleApiResponseToRoad(Context context, Integer method, String url) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                road = GsonParser.getInstance().parseRoad(response);
            }
        }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
                }
        });
        queue.add(request);
        return road;
    }
}
