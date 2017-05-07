package com.ivan.vts.mapper.settings.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.maps.model.LatLng;
import com.ivan.vts.mapper.R;
import com.ivan.vts.mapper.extended.Constants;
import com.ivan.vts.mapper.history.HistoryActivity;
import com.ivan.vts.mapper.navigation.NavigationActivity;
import com.ivan.vts.mapper.settings.SettingActivity;

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

    public boolean switchActivity(Activity activityFrom, MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.settings) {
            intent = new Intent(activityFrom, SettingActivity.class);
        }
        if (id == R.id.navigation) {
            intent = new Intent(activityFrom, NavigationActivity.class);
        }
        if (id == R.id.history) {
            intent = new Intent(activityFrom, HistoryActivity.class);
        }
        activityFrom.startActivity(intent);

        return false;
    }

    public void switchActivity(Activity activityFrom, Class targetClass) {
        intent = new Intent(activityFrom, targetClass);
        activityFrom.startActivity(intent);
    }

    public void switchActivity(Context context, Class targetClass, LatLng parameter) {
        intent = new Intent(context, targetClass);
        Bundle bundle = new Bundle();
        bundle.putDouble(Constants.LAT, parameter.latitude);
        bundle.putDouble(Constants.LNG, parameter.longitude);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

}
