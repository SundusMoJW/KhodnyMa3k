package com.twins.sundus.osama.khodnyma3k.Classes;

/**
 * Created by Osama on 1/8/2018.
 */

public class DataTravel {
    private long date;
    private long time;
    private int number;
    private String fromTo;
    private String name;
    private String address;
    private int Slary;
    private int img;

    public DataTravel(long date, long time, int number, String fromTo, String name, String address, int slary, int img) {
        this.date = date;
        this.time = time;
        this.number = number;
        this.fromTo = fromTo;
        this.name = name;
        this.address = address;
        Slary = slary;
        this.img = img;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getFromTo() {
        return fromTo;
    }

    public void setFromTo(String fromTo) {
        this.fromTo = fromTo;
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
