package com.ivan.vts.mapper.extended;

import com.google.android.gms.maps.model.LatLng;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Chiefster on 8/4/2017.
 */

public class Route {
    private String status;
    private List<LatLng> endLocation = new LinkedList<>();
    private List<LatLng> startLocation = new LinkedList<>();
    private String point;
    private List<LatLng> points = new LinkedList<>();

    public List<LatLng> getEndLocation() {
        return endLocation;
    }

    public String getStatus() {
        return status;
    }

    public List<LatLng> getStartLocation() {
        return startLocation;
    }

    public String getPoint() {
        return point;
    }

    public List<LatLng> getPoints() {
        return points;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEndLocation(List<LatLng> endLocation) {
        this.endLocation = endLocation;
    }

    public void setStartLocation(List<LatLng> startLocation) {
        this.startLocation = startLocation;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public void setPoints(List<LatLng> points) {
        this.points = points;
    }
}

