package com.nick.gvent.entity.json;

import org.json.simple.JSONObject;


public class MarkerSortingJSON {

    private JSONObject latLng;

    private Integer zoom;

    public MarkerSortingJSON() {
    }

    public JSONObject getLatLng() {
        return latLng;
    }

    public void setLatLng(JSONObject latLng) {
        this.latLng = latLng;
    }

    public Integer getZoom() {
        return zoom;
    }

    public void setZoom(Integer zoom) {
        this.zoom = zoom;
    }
}
