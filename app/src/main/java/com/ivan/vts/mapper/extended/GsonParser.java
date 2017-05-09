package com.ivan.vts.mapper.extended;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Chiefster on 8/4/2017.
 */

public class GsonParser {
    private static GsonParser gsonParser = new GsonParser();

    private GsonParser() {
    }

    public static GsonParser getInstance() {
     return gsonParser;
    }

    @SuppressWarnings("all")
    public Route parseRoute(String json) {
        List<LatLng> startList = new ArrayList<>();
        List<LatLng> endList = new ArrayList<>();
        Map<String, Object> jsonMap = new Gson().fromJson(json, Map.class);
        Map<String, Object> routes = (Map<String, Object>) ((ArrayList) jsonMap.get("routes")).get(0);
        Map<String, Object> overviewPolyline = (Map<String, Object>) routes.get("overview_polyline");
        Map<String, ArrayList> steps = (Map<String, ArrayList>) ((ArrayList) routes.get("legs")).get(0);
        for (Object step : steps.get("steps")) {
            Map<String, Object> startLocation = (Map<String, Object>) ((Map<String, Object>) step).get("start_location");
            Map<String, Object> endLocation = (Map<String, Object>) ((Map<String, Object>) step).get("end_location");
            double lat = (double) startLocation.get("lat");
            double lng = (double) startLocation.get("lng");
            startList.add(new LatLng(lat, lng));
            lat = (double) endLocation.get("lat");
            lng = (double) endLocation.get("lng");
            endList.add(new LatLng(lat, lng));
        }
        Route route = new Route();
        route.setStatus((String) jsonMap.get("status"));
        route.setStartLocation(startList);
        route.setEndLocation(endList);
        route.setPoint((String) overviewPolyline.get("points"));

        return route;
    }
}
