package com.ivan.vts.mapper.settings.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.maps.model.LatLng;
import com.ivan.vts.mapper.R;
import com.ivan.vts.mapper.extended.Constants;
import com.ivan.vts.mapper.navigation.NavigationActivity;

/**
 *
 * @author Ivan Marovic
 * @version 1.0
 * 18/2/2017.
 */

public class ActivityMenu {

    private static ActivityMenu activityMenu = new ActivityMenu();
    private Intent intent = null;

    private ActivityMenu() {
    }

    public static ActivityMenu getInstance() {
        return activityMenu;
    }

    public boolean switchActivity(Activity activityFrom, MenuItem menuItem, Object parameter) {
        int id = menuItem.getItemId();

        if (id == R.id.settings) {
            intent = new Intent(activityFrom, null);

        }
        if (id == R.id.autocomplete_fragment) {
            intent = new Intent(activityFrom, NavigationActivity.class);
        }
        activityFrom.startActivity(intent);

        return false;
    }

    public boolean switchActivity(Context context, Class targetClass, LatLng parameter) {
        intent = new Intent(context, targetClass);
        Bundle bundle = new Bundle();
        bundle.putDouble(Constants.LAT, parameter.latitude);
        bundle.putDouble(Constants.LNG, parameter.longitude);
        intent.putExtras(bundle);
        context.startActivity(intent);
        return false;
    }

}