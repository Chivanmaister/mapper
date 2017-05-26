package com.ivan.vts.mapper.history;

import android.os.Bundle;
import android.widget.DatePicker;

import com.ivan.vts.mapper.R;
import com.ivan.vts.mapper.map.DefaultAppActivity;

public class HistoryActivity extends DefaultAppActivity {
    Long mockMinTime = 1495627200000L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
//        datePicker.setMaxDate(new Date().getTime());
//        datePicker.setMinDate(mockMinTime);
    }
}
