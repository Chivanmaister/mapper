package com.ivan.vts.mapper.extended.entities;

import java.io.Serializable;

/**
 * Created by Chiefster on 14/8/2017.
 */

public class Track implements Serializable {

    private Boolean polylineSelected;
    private Boolean trackerSelected;
    private Integer trackId;
    private String trackName;

    public Track() {

    }

    public Track(Boolean polylineSelected, Boolean trackerSelected, Integer trackId, String trackName) {
        this.polylineSelected = polylineSelected;
        this.trackerSelected = trackerSelected;
        this.trackId = trackId;
        this.trackName = trackName;
    }

    public void setPolylineSelected(Boolean polylineSelected) {
        this.polylineSelected = polylineSelected;
    }

    public void setTrackerSelected(Boolean trackerSelected) {
        this.trackerSelected = trackerSelected;
    }

    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public Boolean getPolylineSelected() {
        return polylineSelected;
    }

    public Boolean getTrackerSelected() {
        return trackerSelected;
    }

    public Integer getTrackId() {
        return trackId;
    }

    public String getTrackName() {
        return trackName;
    }
}
