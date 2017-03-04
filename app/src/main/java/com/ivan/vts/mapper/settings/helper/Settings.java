package com.ivan.vts.mapper.settings.helper;

import android.content.Context;
import android.view.MenuItem;
import android.widget.Toast;

import com.ivan.vts.mapper.R;

/**
 *
 * @author Ivan Marovic
 * @version 1.0
 * 18/2/2017.
 */

public class Settings {

    MenuItem menuItem;

    private static Settings settings = new Settings();

    private Settings() {
    }

    public static Settings getInstance() {
        return settings;
    }

    public boolean helloWorld(Context context, MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.settings) {
            Toast.makeText(context, "hello world", Toast.LENGTH_LONG).show();
            return true;
        }

        return false;
    }

}
