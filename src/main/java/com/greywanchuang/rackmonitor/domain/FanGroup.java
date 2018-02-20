package com.greywanchuang.rackmonitor.domain;

import com.greywanchuang.rackmonitor.entity.Property;

import java.util.ArrayList;
import java.util.List;

public class FanGroup {
    private int extemp;
    private int power;
    private String health;
    private List<Fan> fans = new ArrayList<>();

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

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public List<Fan> getFans() {
        return fans;
    }

    public void setFans(List<Fan> fans) {
        this.fans = fans;
    }

    public void addFan(Fan fan) {
        this.fans.add(fan);
    }

    /**
     * 组装风扇组信息
     *
     * @param properties
     */
    public void composeGroup(List<Property> properties) {
//        properties.forEach(property -> {
//            if ("ExTemp".equals(property.getName())) {
//                this.setExtemp(Integer.parseInt(property.getValue()));
//            } else if ("Health".equals(property.getName())) {
//                this.setHealth(property.getValue());
//            } else if ("Power".equals(property.getName())) {
//                this.setPower(Integer.parseInt(property.getValue()));
//            }
//        });
    }

    /**
     * 组装风扇信息
     *
     * @param properties
     */
    public void conposeFan(List<Property> properties) {
//        Fan fan = new Fan();
//        properties.forEach(property -> {
//            if ("ID".equals(property.getName())) {
//                fan.setId(Integer.parseInt(property.getValue()));
//            } else if ("Health".equals(property.getName())) {
//                fan.setHealth(property.getValue());
//            } else if ("Speed".equals(property.getName())) {
//                fan.setSpeed(Integer.parseInt(property.getValue()));
//            }
//        });
//        this.addFan(fan);
    }

}
