package com.ivan.vts.mapper.extended.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chiefster on 14/8/2017.
 */

public class Tracker implements Serializable {

    private Boolean polylineSelected;
    private Boolean trackerSelected;
    private Integer trackId;
    private String trackName;
    private Date trackDate;

    public Tracker(Boolean polylineSelected, Boolean trackerSelected, Integer trackId, String trackName, Date trackDate) {
        this.polylineSelected = polylineSelected;
        this.trackerSelected = trackerSelected;
        this.trackId = trackId;
        this.trackName = trackName;
        this.trackDate = trackDate;
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

    public void setTrackDate(Date trackDate) {
        this.trackDate = trackDate;
    }

    public Date getTrackDate() {
        return trackDate;
    }
}
