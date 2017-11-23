package com.greywanchuang.rackmonitor.domain;

public class Net {
    private String name;
    private String ipmode;
    private String ip;
    private String mac;
    private String netmask;
    private String gateway;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpmode() {
        return ipmode;
    }

    public void setIpmode(String ipmode) {
        this.ipmode = ipmode;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getNetmask() {
        return netmask;
    }

    public void setNetmask(String netmask) {
        this.netmask = netmask;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }
}
