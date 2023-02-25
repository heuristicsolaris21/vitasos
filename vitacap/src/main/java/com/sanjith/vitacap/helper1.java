package com.sanjith.vitacap;

public class helper1 {
    String editname,editnumber,editvehicle,editpassword,editlicense;
    public helper1(String name, String number, String vehicle, String password, String license){

    }

    public helper1(String editname, String editnumber, String editage, String editpassword, String editdia, String editbp, String editdrugs,String editemer) {
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

    public String getEditage() {
        return editvehicle;
    }

    public void setEditage(String editage) {
        this.editvehicle = editvehicle;
    }

    public String getEditpassword() {
        return editpassword;
    }

    public void setEditpassword(String editpassword) {
        this.editpassword = editpassword;
    }

    public String getEditdia() {
        return editlicense;
    }

    public void setEditdia(String editdia) {
        this.editlicense = editlicense;
    }

}
