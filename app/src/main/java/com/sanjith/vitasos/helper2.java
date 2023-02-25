package com.sanjith.vitasos;

public class helper2 {
    String phoneno,latitude,longitude;
    public helper2(){

    }

    public helper2(String phoneno, String latitude, String longitude) {
        this.phoneno = phoneno;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
