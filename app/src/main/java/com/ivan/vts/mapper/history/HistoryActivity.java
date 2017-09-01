package com.ivan.vts.mapper.history;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.ivan.vts.mapper.R;
import com.ivan.vts.mapper.map.DefaultAppActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class HistoryActivity extends DefaultAppActivity {

    EditText startDate;
    EditText endDate;
    Calendar calendar;
    TableLayout tableLayout;
    private static int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        calendar = Calendar.getInstance();
        startDate = (EditText) findViewById(R.id.startDate);
        endDate = (EditText) findViewById(R.id.endDate);
        tableLayout = (TableLayout) findViewById(R.id.tableLayout);

        DatePickerDialog.OnDateSetListener startDatePicker = (view, year, month, dayOfMonth) -> {
            setCalendar(year, month, dayOfMonth);
            String myFormat = "yyyy/MM/dd";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            startDate.setText(sdf.format(calendar.getTime()));
        };

        DatePickerDialog.OnDateSetListener endDatePicker = (view, year, month, dayOfMonth) -> {
            setCalendar(year, month, dayOfMonth);
            String myFormat = "yyyy/MM/dd";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            endDate.setText(sdf.format(calendar.getTime()));
        };

        startDate.setOnClickListener(v -> new DatePickerDialog(HistoryActivity.this, startDatePicker, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show());
        endDate.setOnClickListener(v -> new DatePickerDialog(HistoryActivity.this, endDatePicker, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show());


        for (int i = 0; i < 10; i++) {
            TableRow row = new TableRow(HistoryActivity.this);
            row.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            for (int j = 0; j < 3; j++) {
                TextView view = new TextView(HistoryActivity.this);
                view.setText(String.valueOf(j));
                row.addView(view);
            }
            tableLayout.addView(row, new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }

    private void setCalendar(int year, int month, int day) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        Toast.makeText(HistoryActivity.this, String.valueOf(calendar.getTimeInMillis()), Toast.LENGTH_SHORT).show();
    }
}
