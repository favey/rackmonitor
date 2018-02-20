package com.greywanchuang.rackmonitor.domain;

import com.greywanchuang.rackmonitor.entity.Property;

import java.util.List;

public class Rack {
    private StringBuffer model = new StringBuffer("");
    private String sn;
    private String health;
    private String location;
    private int entemp;
    private String mac;
    private String ip;
    private String netmask;
    private String ipmode;
    private String firmware;

    public StringBuffer getModel() {
        return model;
    }

    public void setModel(StringBuffer model) {
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

    /**
     * 组装rack对象
     *
     * @param properties
     * @return
     */
    public Rack compose(List<Property> properties,Rack rack ) {
//        properties.forEach(property -> {
//            if ("Manufacturer".equals(property.getName()) || "PartNumber".equals(property.getName())) {
//                rack.getModel().append(property.getValue());
//            } else if ("Location".equals(property.getName())) {
//                rack.setLocation(property.getValue());
//            } else if ("Health".equals(property.getName())) {
//                rack.setHealth(property.getValue());
//            } else if ("SN".equals(property.getName())) {
//                rack.setSn(property.getValue());
//            } else if ("EnTemp".equals(property.getName())) {
//                rack.setEntemp(Integer.parseInt(property.getValue()));
//            } else if ("IP".equals(property.getName())) {
//                rack.setIp(property.getValue());
//            } else if ("IPMode".equals(property.getName())) {
//                rack.setIpmode(property.getValue());
//            } else if ("NetMask".equals(property.getName())) {
//                rack.setNetmask(property.getValue());
//            } else if ("Firmware".equals(property.getName())) {
//                rack.setFirmware(property.getValue());
//            } else if ("MAC".equals(property.getName())) {
//                rack.setMac(property.getValue());
//            }
//        });
        return rack;
    }

}
