package com.techzonecs.tremble.model;

/**
 * Created by Gaffas on 27/10/2015.
 */
public class Session {

    private String date;
    private String location;
    private String className;
    private String courseName;
    private String locationGps;
    private String zone;
    private String trainerName;


    public Session(){

    }

    public Session(String newDate, String newLocation, String newClassName, String newCourseName, String newLocationGps, String newZone, String newTrainerName){
        this.date = newDate;
        this.location = newLocation;
        this.className = newClassName;
        this.courseName = newCourseName;
        this.locationGps = newLocationGps;
        this.zone = newZone;
        this.trainerName = newTrainerName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getLocationGps() {
        return locationGps;
    }

    public void setLocationGps(String locationGps) {
        this.locationGps = locationGps;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }
}
