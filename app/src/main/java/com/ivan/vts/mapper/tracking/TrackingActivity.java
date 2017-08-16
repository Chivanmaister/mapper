package com.ivan.vts.mapper.tracking;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.ivan.vts.mapper.R;
import com.ivan.vts.mapper.extended.Constants;
import com.ivan.vts.mapper.extended.entities.Track;
import com.ivan.vts.mapper.map.DefaultAppActivity;
import com.ivan.vts.mapper.map.MapsActivity;
import com.ivan.vts.mapper.settings.helper.ActivityMenu;

public class TrackingActivity extends DefaultAppActivity {
    Switch tracking;
    Switch polyline;
    TextView routeName;
    Integer routeTrackerId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);
        tracking = (Switch) findViewById(R.id.tracking);
        polyline = (Switch) findViewById(R.id.polyline);
        routeName = (TextView) findViewById(R.id.routeTrackingName);


        Bundle bundle = getIntent().getExtras();
        setSwitchButtons(bundle);
        tracking.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Title");
                builder.setMessage(R.string.trackingName);
                final EditText input = new EditText(this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);
                builder.setPositiveButton("OK", (dialog, which) -> {

                    //TODO uncomment and set server response for trackerId
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
        //        queue.add(request)
                    routeName.setText(input.getText().toString());
                });
                builder.setNegativeButton("Cancel", (dialog, which) -> {
                    dialog.cancel();
                    tracking.setChecked(false);
                    routeName.setText("");
                });
                builder.setCancelable(false);

                builder.show();
            } else {
                tracking.setChecked(false);
                routeName.setText("");
            }
        });
        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(v -> {
            Track tracker = new Track(polyline.isChecked(), tracking.isChecked(), null, routeName.getText().toString());
            bundle.putSerializable(Constants.ROUTE_TRACKER, tracker);
            ActivityMenu.getInstance().switchActivity(TrackingActivity.this, MapsActivity.class, bundle);
        });
    }

    public void setSwitchButtons(Bundle bundle) {
        Track track = (Track) bundle.getSerializable(Constants.ROUTE_TRACKER);
        if (track == null) {
            tracking.setChecked(false);
            polyline.setChecked(false);
            routeTrackerId = null;
            routeName.setText("");
        }
        else {
            if (track.getTrackerSelected() == null) {
                tracking.setChecked(false);
            } else {
                tracking.setChecked(track.getTrackerSelected());
            }
            if (track.getPolylineSelected() != null) {
                polyline.setChecked(track.getPolylineSelected());
            } else {
                polyline.setChecked(false);
            }
            routeTrackerId = track.getTrackId();
        }
    }

    public void editTrackName(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit title");
        builder.setMessage(R.string.editTrackingName);
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("OK", (dialog, which) -> {
            routeName.setText(input.getText().toString());
        });
        builder.show();
    }
}
