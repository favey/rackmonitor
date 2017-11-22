package com.greywanchuang.rackmonitor.domain;

public class Server {
    private String status;
    private String health;
    private int entemp;
    private int extemp;
    private int power;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public int getEntemp() {
        return entemp;
    }

    public void setEntemp(int entemp) {
        this.entemp = entemp;
    }

    public int getExtemp() {
        return extemp;
    }

    public void setExtemp(int extemp) {
        this.extemp = extemp;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
