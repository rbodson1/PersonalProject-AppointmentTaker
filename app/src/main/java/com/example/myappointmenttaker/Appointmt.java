package com.example.myappointmenttaker;

public class Appointmt {
    private int appointmtID;
    private String appointeeName;
    private String streetAddress;
    private String time;
    private String style;
    private String price;
    private String phoneNumber;
    private String estimateTime;


    public Appointmt() {
        appointmtID = -1;
    }

    public int getAppointmtID() {
        return appointmtID;
    }
    public void setAppointmtID(int i) {
        appointmtID = i;
    }
    public String getAppointeeName() {
        return appointeeName;
    }
    public void setAppointeeName(String s) {
        appointeeName = s;
    }
    public String getStreetAddress() {
        return streetAddress;
    }
    public void setStreetAddress(String s) {
        streetAddress = s;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String s) { time= s; }
    public String getStyle() {
        return style;
    }
    public void setStyle(String s) {
        style= s;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String s) { price = s; }
    public void setPhoneNumber(String s) {
        phoneNumber = s;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setEstimateTime(String s) {
        estimateTime = s;
    }
    public String getEstimateTime() {
        return estimateTime;
    }




}
