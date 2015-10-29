package com.techzonecs.tremble.model;

/**
 * Created by Gaffas on 27/10/2015.
 */
public class Location {

    private String location;
    private String gps;
    private Zone zone;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

}
