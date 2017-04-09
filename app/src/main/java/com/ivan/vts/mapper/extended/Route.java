package com.ivan.vts.mapper.extended;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chiefster on 8/4/2017.
 */

public class Route {
    private String status;
    private List<LatLng> endLocation;
    private List<LatLng> startLocation;

    public Route() {
        status = "";
        endLocation = new ArrayList<>();
        startLocation = new ArrayList<>();
    }

    public List<LatLng> getEndLocation() {
        return endLocation;
    }

    public String getStatus() {
        return status;
    }

    public List<LatLng> getStartLocation() {
        return startLocation;
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

}

