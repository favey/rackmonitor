package com.greywanchuang.rackmonitor.domain;

public class Rack {
    private String model;
    private String sn;
    private String health;
    private String location;
    private int entemp;
    private String mac;
    private String ip;
    private String netmask;
    private String ipmode;
    private String firmware;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getEntemp() {
        return entemp;
    }

    public void setEntemp(int entemp) {
        this.entemp = entemp;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNetmask() {
        return netmask;
    }

    public void setNetmask(String netmask) {
        this.netmask = netmask;
    }

    public String getIpmode() {
        return ipmode;
    }

    public void setIpmode(String ipmode) {
        this.ipmode = ipmode;
    }

    public String getFirmware() {
        return firmware;
    }

    public void setFirmware(String firmware) {
        this.firmware = firmware;
    }
}
