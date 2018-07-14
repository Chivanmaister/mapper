package com.ivan.vts.mapper.history;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.ivan.vts.mapper.R;
import com.ivan.vts.mapper.extended.Constants;
import com.ivan.vts.mapper.extended.GsonParser;
import com.ivan.vts.mapper.map.DefaultAppActivity;
import com.ivan.vts.mapper.map.MapsActivity;
import com.ivan.vts.mapper.settings.helper.ActivityMenu;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class HistoryActivity extends DefaultAppActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Calendar calendar = Calendar.getInstance();
        EditText startDate = (EditText) findViewById(R.id.startDate);
        EditText endDate = (EditText) findViewById(R.id.endDate);
        TableLayout tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        Button searchButton = (Button) findViewById(R.id.searchButton);

        DatePickerDialog.OnDateSetListener startDatePicker = (view, year, month, dayOfMonth) -> {
            setCalendar(calendar, year, month, dayOfMonth);
            String myFormat = "yyyy/MM/dd";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            startDate.setText(sdf.format(calendar.getTime()));
        };

        DatePickerDialog.OnDateSetListener endDatePicker = (view, year, month, dayOfMonth) -> {
            setCalendar(calendar, year, month, dayOfMonth);
            String myFormat = "yyyy/MM/dd";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            endDate.setText(sdf.format(calendar.getTime()));
        };

        startDate.setOnClickListener(v -> new DatePickerDialog(HistoryActivity.this, startDatePicker, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show());
        endDate.setOnClickListener(v -> new DatePickerDialog(HistoryActivity.this, endDatePicker, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show());

        searchButton.setOnClickListener(mock -> {
            mockTable(tableLayout);
        });
    }

    private void setCalendar(Calendar calendar, int year, int month, int day) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        Toast.makeText(HistoryActivity.this, String.valueOf(calendar.getTimeInMillis()), Toast.LENGTH_SHORT).show();
    }

    private void mockTable(TableLayout tableLayout) {
        for (int i = 0; i < 10; i++) {
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            for (int j = 0; j < 3; j++) {
                TextView view = new TextView(this);
                view.setText(String.valueOf(j));
                row.addView(view);
            }
            Button button = new Button(this);
            button.setText("View");
            button.setOnClickListener(v -> {
//                Bundle bundle = new Bundle();
                List<LatLng> coordinateList = new LinkedList<>();
                LatLng latLng = new LatLng(46.102577, 19.655765);
                coordinateList.add(latLng);
                latLng = new LatLng(46.103125, 19.655151);
                coordinateList.add(latLng);
                latLng = new LatLng(46.102770, 19.654164);
                coordinateList.add(latLng);
                latLng = new LatLng(46.102606, 19.653432);
                coordinateList.add(latLng);
                latLng = new LatLng(46.102554, 19.653016);
                coordinateList.add(latLng);
                latLng = new LatLng(46.102658, 19.651812);
                coordinateList.add(latLng);
                latLng = new LatLng(46.102846, 19.649701);
                coordinateList.add(latLng);
                String json = GsonParser.toJson(coordinateList);
                bundle.putString(Constants.HISTORY, json);
                ActivityMenu.getInstance().switchActivity(this, MapsActivity.class, bundle);
            });

            row.addView(button);
            tableLayout.addView(row, new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }
}
