package com.twins.sundus.osama.khodnyma3k.Classes;

/**
 * Created by Osama on 1/9/2018.
 */

public class OrderTravel {
    private long date;
    private long time;
    private int number;
    private String fromTo;
    private int Slary;
    private boolean select;
    private int direction;

    public OrderTravel(long date, long time, int number, String fromTo, int slary, boolean select, int direction) {
        this.date = date;
        this.time = time;
        this.number = number;
        this.fromTo = fromTo;
        Slary = slary;
        this.select = select;
        this.direction = direction;
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
