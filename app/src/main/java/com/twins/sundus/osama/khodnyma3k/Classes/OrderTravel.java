package com.twins.sundus.osama.khodnyma3k.Classes;

/**
 * Created by Osama on 1/9/2018.
 */

public class OrderTravel {
    private String date;
    private String time;
    private int number;
    private String fromTo;
    private int Slary;
    private boolean select;
    private int direction;

    public OrderTravel(String date, String time, int number, String fromTo, int slary, boolean select, int direction) {
        this.date = date;
        this.time = time;
        this.number = number;
        this.fromTo = fromTo;
        Slary = slary;
        this.select = select;
        this.direction = direction;
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

    public String getFromTo() {
        return fromTo;
    }

    public void setFromTo(String fromTo) {
        this.fromTo = fromTo;
    }

    public int getSlary() {
        return Slary;
    }

    public void setSlary(int slary) {
        Slary = slary;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
