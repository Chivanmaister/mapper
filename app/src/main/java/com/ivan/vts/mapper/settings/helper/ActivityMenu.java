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
import com.ivan.vts.mapper.tracking.TrackingActivity;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ivan Marovic
 * @version 1.0
 * 18/2/2017.
 */

public class ActivityMenu {

    private static ActivityMenu activityMenu = new ActivityMenu();
    private Map<Integer, Class> activityMap = new HashMap<Integer, Class>() {
        {
            put(R.id.settings, SettingActivity.class);
            put(R.id.navigation, NavigationActivity.class);
            put(R.id.history, HistoryActivity.class);
            put(R.id.tracking, TrackingActivity.class);
        }
    };

    private ActivityMenu() {
    }

    public static ActivityMenu getInstance() {
        return activityMenu;
    }

    @SuppressWarnings("unchecked")
    public boolean switchActivity(Activity activityFrom, MenuItem menuItem, Bundle bundle) {
        Integer id = menuItem.getItemId();
        switchActivity(activityFrom, activityMap.get(id), bundle);
        return false;
    }

    public <T> void switchActivity(Activity activityFrom, Class<T> targetClass, Bundle bundle) {
        Intent intent = new Intent(activityFrom, targetClass);
        intent.putExtras(bundle);
        activityFrom.startActivity(intent);
        if (targetClass == MapsActivity.class) {
            activityFrom.finish();
        }
    }
}
