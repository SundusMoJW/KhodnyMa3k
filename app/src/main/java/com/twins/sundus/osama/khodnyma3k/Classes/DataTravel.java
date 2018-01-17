package com.twins.sundus.osama.khodnyma3k.Classes;

/**
 * Created by Osama on 1/8/2018.
 */

public class DataTravel {
    private String date;
    private String time;
    private int number;
    private String from;
    private String to;
    private String name;
    private String address;
    private int Slary;
    private int img;

    public DataTravel() {
    }

    public DataTravel(String date, String time, int number, String from, String to, String name, String address, int slary, int img) {
        this.date = date;
        this.time = time;
        this.number = number;
        this.from = from;
        this.to = to;
        this.name = name;
        this.address = address;
        Slary = slary;
        this.img = img;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSlary() {
        return Slary;
    }

    public void setSlary(int slary) {
        Slary = slary;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
