package com.ivan.vts.mapper.extended;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.maps.android.PolyUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Chiefster on 8/4/2017.
 */

public class GsonParser {

    @SuppressWarnings("all")
    public static Route parseRoute(String json) {
        Route route = new Route();
        List<LatLng> startList = new ArrayList<>();
        List<LatLng> endList = new ArrayList<>();
        List<LatLng> points = new ArrayList<>();
        Map<String, Object> jsonMap = new Gson().fromJson(json, Map.class);
        route.setStatus((String) jsonMap.get("status"));
        if (route.getStatus().equalsIgnoreCase("ok")) {
            Map<String, Object> routes = (Map<String, Object>) ((ArrayList) jsonMap.get("routes")).get(0);
            Map<String, Object> overviewPolyline = (Map<String, Object>) routes.get("overview_polyline");
            Map<String, ArrayList> steps = (Map<String, ArrayList>) ((ArrayList) routes.get("legs")).get(0);
            for (Object step : steps.get("steps")) {
                Map<String, Object> startLocation = (Map<String, Object>) ((Map<String, Object>) step).get("start_location");
                Map<String, Object> endLocation = (Map<String, Object>) ((Map<String, Object>) step).get("end_location");
                Map<String, Object> polyline = (Map<String, Object>) ((Map<String, Object>) step).get("polyline");
                String codedPoints = (String) polyline.get("points");
                points.addAll(PolyUtil.decode(codedPoints));
                double lat = (double) startLocation.get("lat");
                double lng = (double) startLocation.get("lng");
                startList.add(new LatLng(lat, lng));
                lat = (double) endLocation.get("lat");
                lng = (double) endLocation.get("lng");
                endList.add(new LatLng(lat, lng));
            }
            route.setStartLocation(startList);
            route.setEndLocation(endList);
            route.setPoint((String) overviewPolyline.get("points"));
            route.setPoints(points);
        }

        return route;
    }

    @SuppressWarnings("unchecked")
    public static Integer parseUserId(String json) {
        Map<String, Object> jsonMap = new Gson().fromJson(json, Map.class);
        return (Integer) jsonMap.get("id");
    }

    @SuppressWarnings("unchecked")
    public Integer parseRouteId(String json) {
        Map<String, Object> jsonMap = new Gson().fromJson(json, Map.class);
        return (Integer) jsonMap.get("");
    }
}
