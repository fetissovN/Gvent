package com.nick.gvent.entity.json;

import org.json.simple.JSONObject;


public class MarkerSortingJSON {

    private JSONObject latLng;

    private JSONObject boundaries;

    public MarkerSortingJSON() {
    }

    public JSONObject getLatLng() {
        return latLng;
    }

    public void setLatLng(JSONObject latLng) {
        this.latLng = latLng;
    }

    public JSONObject getBoundaries() {
        return boundaries;
    }

    public void setBoundaries(JSONObject boundaries) {
        this.boundaries = boundaries;
    }
}
