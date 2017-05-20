package com.ivan.vts.mapper.settings.helper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.ivan.vts.mapper.R;
import com.ivan.vts.mapper.history.HistoryActivity;
import com.ivan.vts.mapper.map.MapsActivity;
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

    public boolean switchActivity(Activity activityFrom, MenuItem menuItem, Bundle bundle) {
        int id = menuItem.getItemId();

        if (id == R.id.settings) {
//            intent = new Intent(activityFrom, SettingActivity.class);
            switchActivity(activityFrom, SettingActivity.class, bundle);
        }
        if (id == R.id.navigation) {
//            intent = new Intent(activityFrom, NavigationActivity.class);
            switchActivity(activityFrom, NavigationActivity.class, bundle);
        }
        if (id == R.id.history) {
//            intent = new Intent(activityFrom, HistoryActivity.class);
            switchActivity(activityFrom, HistoryActivity.class, bundle);
        }
        return false;
    }

    public <T> void switchActivity(Activity activityFrom, Class<T> targetClass) {
        intent = new Intent(activityFrom, targetClass);
        activityFrom.startActivity(intent);
    }

    public <T> void switchActivity(Activity activityFrom, Class<T> targetClass, Bundle bundle) {
        intent = new Intent(activityFrom, targetClass);
        intent.putExtras(bundle);
        activityFrom.startActivity(intent);
        if (targetClass == MapsActivity.class) {
            activityFrom.finish();
        }
    }

}
