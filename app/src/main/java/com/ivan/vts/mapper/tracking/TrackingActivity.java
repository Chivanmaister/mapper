package com.ivan.vts.mapper.tracking;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.ivan.vts.mapper.R;
import com.ivan.vts.mapper.extended.Constants;
import com.ivan.vts.mapper.map.DefaultAppActivity;
import com.ivan.vts.mapper.map.MapsActivity;
import com.ivan.vts.mapper.settings.helper.ActivityMenu;

public class TrackingActivity extends DefaultAppActivity {
    Switch tracking;
    Switch polyline;
    String routeName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);
        tracking = (Switch) findViewById(R.id.tracking);
        polyline = (Switch) findViewById(R.id.polyline);

        Bundle bundle = getIntent().getExtras();
        setSwitchButtons(bundle);
        tracking.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Title");
                builder.setMessage(R.string.clearMap);
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
                    routeName = input.getText().toString();
                });
                builder.setNegativeButton("Cancel", (dialog, which) -> {
                    dialog.cancel();
                    tracking.setChecked(false);
                });
                builder.setCancelable(false);

                builder.show();
            }
        });
        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(v -> {
            bundle.putBoolean(Constants.POLYLINE, polyline.isChecked());
            bundle.putBoolean(Constants.TRACKING, tracking.isChecked());
            bundle.putString(Constants.TRACKER_ID, routeName);
            ActivityMenu.getInstance().switchActivity(TrackingActivity.this, MapsActivity.class, bundle);
        });
    }

    public void setSwitchButtons(Bundle bundle) {
        tracking.setChecked(bundle.getBoolean(Constants.TRACKING, false));
        polyline.setChecked(bundle.getBoolean(Constants.POLYLINE, false));
    }
}
