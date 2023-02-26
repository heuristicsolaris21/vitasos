package com.sanjith.vitcap2;

public class helper1 {
    String editname,editnumber,editvehicle,editpassword,editlicense;
    public helper1(){

    }

    public helper1(String editname, String editnumber, String editvehicle, String editpassword, String editlicense) {
        this.editname = editname;
        this.editnumber = editnumber;
        this.editvehicle = editvehicle;
        this.editpassword = editpassword;
        this.editlicense = editlicense;
    }

    public String getEditname() {
        return editname;
    }

    public void setEditname(String editname) {
        this.editname = editname;
    }

    public String getEditnumber() {
        return editnumber;
    }

    public void setEditnumber(String editnumber) {
        this.editnumber = editnumber;
    }

    public String getEditvehicle() {
        return editvehicle;
    }

    public void setEditvehicle(String editvehicle) {
        this.editvehicle = editvehicle;
    }

    public String getEditpassword() {
        return editpassword;
    }

    public void setEditpassword(String editpassword) {
        this.editpassword = editpassword;
    }

    public String getEditlicense() {
        return editlicense;
    }

    public void setEditlicense(String editlicense) {
        this.editlicense = editlicense;
    }
}
