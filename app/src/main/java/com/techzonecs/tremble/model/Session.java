package com.techzonecs.tremble.model;

/**
 * Created by Gaffas on 27/10/2015.
 */
public class Session {

    private String[] date;
    private String location;
    private String className;
    private String courseName;
    private String locationGps;
    private String zone;
    private String trainerName;
    private String id_class;
    private String id_session;
    private String isEvaluationDoneFlag;


    public Session(String[] date, String location, String className, String courseName, String locationGps, String zone, String trainerName, String id_class, String id_session, String isEvaluationDoneFlag) {
        this.date = date;
        this.location = location;
        this.className = className;
        this.courseName = courseName;
        this.locationGps = locationGps;
        this.zone = zone;
        this.trainerName = trainerName;
        this.id_class = id_class;
        this.id_session = id_session;
        this.isEvaluationDoneFlag = isEvaluationDoneFlag;
    }

    public Session(){

    }

    public String getIsEvaluationDoneFlag() {
        return isEvaluationDoneFlag;
    }

    public void setIsEvaluationDoneFlag(String isEvaluationDoneFlag) {
        this.isEvaluationDoneFlag = isEvaluationDoneFlag;
    }

    public String[] getDate() {
        return date;
    }

    public void setDate(String[] date) {
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

    public String getId_class() {
        return id_class;
    }

    public void setId_class(String id_class) {
        this.id_class = id_class;
    }

    public String getId_session() {
        return id_session;
    }

    public void setId_session(String id_session) {
        this.id_session = id_session;
    }
}
