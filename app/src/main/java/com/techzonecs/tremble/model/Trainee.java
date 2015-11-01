package com.techzonecs.tremble.model;

/**
 * Created by Gaffas on 27/10/2015.
 */
public class Trainee {

    private String firstname;
    private int sisid;
    private String mobile;
    private String email;
    private String grade;
    private String subject;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getSisid() {
        return sisid;
    }

    public void setSisid(int sisid) {
        this.sisid = sisid;
    }
}
