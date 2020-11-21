package com.TheRookies.kanteen.Model;

public class Users {
    private String imageurl,name,phone,email,academic,uid;
    public Users() {
    }





    public Users(String imageurl, String name, String phone, String email, String academic, String uid) {
        this.imageurl = imageurl;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.academic = academic;
        this.uid = uid;

    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAcademic() {
        return academic;
    }

    public void setAcademic(String academic) {
        this.academic = academic;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
