package com.example.onecoin;

public class model2 {

    String name;
    String amount;
    String time;
    String date;
    model2() {
    }

    public model2(String name, String amount, String time, String date) {
        this.name = name;
        this.amount = amount;
        this.time = time;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
