package com.ivan.vts.mapper.extended;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Chiefster on 3/5/2017.
 */

public class ThreadDataTransfer {

    private static ThreadDataTransfer mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    private ThreadDataTransfer(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized ThreadDataTransfer getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new ThreadDataTransfer(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

}
