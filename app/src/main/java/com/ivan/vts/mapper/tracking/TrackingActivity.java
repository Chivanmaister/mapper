package com.ivan.vts.mapper.tracking;

import android.app.AlertDialog;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.ivan.vts.mapper.R;
import com.ivan.vts.mapper.extended.Constants;
import com.ivan.vts.mapper.extended.entities.Tracker;
import com.ivan.vts.mapper.map.DefaultAppActivity;
import com.ivan.vts.mapper.map.MapsActivity;
import com.ivan.vts.mapper.settings.helper.ActivityMenu;

import java.util.UUID;

public class TrackingActivity extends DefaultAppActivity {
    Switch tracking;
    Switch polyline;
    TextView routeName;
    Integer routeTrackerId;

    @SuppressWarnings("all")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);
        tracking = (Switch) findViewById(R.id.tracking);
        polyline = (Switch) findViewById(R.id.polyline);
        routeName = (TextView) findViewById(R.id.routeTrackingName);

        Bundle bundle = getIntent().getExtras();
        setSwitchButtons(bundle);
        routeName.setPaintFlags(routeName.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        tracking.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Title");
                builder.setMessage(R.string.trackingName);
                final EditText input = new EditText(this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);
                builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {

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
                    String inputName = input.getText().toString();
                    if (inputName.isEmpty()) {
                        routeName.setText(UUID.randomUUID().toString().substring(0, 8));
                    } else {
                        routeName.setText(input.getText().toString());
                    }

                });
                builder.setNegativeButton("Cancel", (dialog, which) -> {
                    dialog.cancel();
                    tracking.setChecked(false);
                    routeName.setText("");
                });
                builder.setCancelable(false);
                builder.show();
            } else {
                routeName.setText("");
            }
            routeName.setClickable(isChecked);
        });
        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(v -> {
            Tracker tracker = new Tracker(polyline.isChecked(), tracking.isChecked(), routeTrackerId, routeName.getText().toString(), null);
            bundle.putSerializable(Constants.ROUTE_TRACKER, tracker);
            ActivityMenu.getInstance().switchActivity(TrackingActivity.this, MapsActivity.class, bundle);
        });
    }

    public void setSwitchButtons(Bundle bundle) {
        Tracker track = (Tracker) bundle.getSerializable(Constants.ROUTE_TRACKER);
        if (track == null) {
            tracking.setChecked(false);
            polyline.setChecked(false);
            routeTrackerId = null;
            routeName.setText("");
        } else {
            if (!track.getTrackerSelected()) {
                tracking.setChecked(false);
            } else {
                tracking.setChecked(track.getTrackerSelected());
                routeName.setText(track.getTrackName());
            }
            if (track.getPolylineSelected()) {
                polyline.setChecked(track.getPolylineSelected());
            } else {
                polyline.setChecked(false);
            }
            routeTrackerId = track.getTrackId();
        }
    }

    public void editTrackName(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.editTrackTitle);
        builder.setMessage(R.string.editTrackingName);
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {
            routeName.setText(input.getText().toString());
        });
        builder.show();
    }
}
