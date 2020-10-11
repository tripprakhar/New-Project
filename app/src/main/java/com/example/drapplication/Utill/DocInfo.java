package com.example.drapplication.Utill;

public class DocInfo {

    private String fname;
    private String mname;
    private String lname;
    private String degree;
    private String speciality;
    private int experience;
    private String phoneNo;
    private String image;
    private String present;

    public DocInfo() {
    }

    public DocInfo(String fname, String mname, String lname, String degree, String speciality, int experience, String phoneNo, String image, String present) {
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.degree = degree;
        this.speciality = speciality;
        this.experience = experience;
        this.phoneNo = phoneNo;
        this.image = image;
        this.present = present;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPresent() {
        return present;
    }

    public void setPresent(String present) {
        this.present = present;
    }
}
