package com.ivan.vts.mapper.history;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
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
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        routeName = input.getText().toString();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        tracking.setChecked(false);
                    }
                });
                builder.setCancelable(false);

                builder.show();
            }
        });
        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(v -> {
            bundle.putBoolean(Constants.POLYLINE, polyline.isChecked());
            bundle.putBoolean(Constants.TRACKING, tracking.isChecked());
            bundle.putString(Constants.ROUTE_NAME, routeName);
            Log.d("TRACKING", String.valueOf(tracking.isChecked()));
            ActivityMenu.getInstance().switchActivity(TrackingActivity.this, MapsActivity.class, bundle);
        });
    }

    public void setSwitchButtons(Bundle bundle) {
        tracking.setChecked(bundle.getBoolean(Constants.TRACKING, false));
        polyline.setChecked(bundle.getBoolean(Constants.POLYLINE, false));
    }
}
