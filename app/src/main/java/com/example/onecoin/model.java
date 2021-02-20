package com.example.onecoin;

public class model {

    String name;
    String imageURL;
    String email;
    String balance;
    String acno;
    String uid;
    model() {
    }

    public model(String name, String imageURL, String email, String balance, String acno, String uid) {
        this.name = name;
        this.imageURL = imageURL;
        this.email = email;
        this.balance = balance;
        this.acno = acno;
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getAcno() {
        return acno;
    }

    public void setAcno(String acno) {
        this.acno = acno;
    }
}
