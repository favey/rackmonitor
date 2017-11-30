package com.greywanchuang.rackmonitor.domain;

import com.greywanchuang.rackmonitor.entity.Property;

import java.util.ArrayList;
import java.util.List;

public class Power {
    private String status;
    private String health;
    private String firmware;
    private int psuamount;
    private String controlmode;
    private int maxpower;
    private List<PSU> psuA = new ArrayList<>();
    private List<PSU> psuB = new ArrayList<>();

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

    public String getFirmware() {
        return firmware;
    }

    public void setFirmware(String firmware) {
        this.firmware = firmware;
    }

    public int getPsuamount() {
        return psuamount;
    }

    public void setPsuamount(int psuamount) {
        this.psuamount = psuamount;
    }

    public String getControlmode() {
        return controlmode;
    }

    public void setControlmode(String controlmode) {
        this.controlmode = controlmode;
    }

    public int getMaxpower() {
        return maxpower;
    }

    public void setMaxpower(int maxpower) {
        this.maxpower = maxpower;
    }

    public List<PSU> getPsuA() {
        return psuA;
    }

    public void setPsuA(List<PSU> psuA) {
        this.psuA = psuA;
    }

    public List<PSU> getPsuB() {
        return psuB;
    }

    public void setPsuB(List<PSU> psuB) {
        this.psuB = psuB;
    }

    private void addPsuA(PSU psu) {
        this.psuA.add(psu);
    }

    private void addPsuB(PSU psu) {
        this.psuB.add(psu);
    }

    /**
     * 组装server自身属性
     *
     * @param properties
     */
    public void composeServer(List<Property> properties) {
        properties.forEach(property -> {
            if ("Firmware".equals(property.getName())) {
                this.setFirmware(property.getValue());

            } else if ("Status".equals(property.getName())) {
                this.setStatus(property.getValue());
            } else if ("Health".equals(property.getName())) {
                this.setHealth(property.getValue());
            } else if ("PSUAmount".equals(property.getName())) {
                this.setPsuamount(Integer.parseInt(property.getValue()));
            } else if ("MaxPower".equals(property.getName())) {
                this.setMaxpower(Integer.parseInt(property.getValue()));
            } else if ("PSUControlMode".equals(property.getName())) {
                this.setControlmode(property.getValue());
            }
        });
    }

    /**
     * 组装PSU属性
     *
     * @param properties
     */
    public void composePUSs(List<Property> properties, String targetName) {
        PSU psu = new PSU();
        properties.forEach(property -> {
            if ("Health".equals(property.getName())) {
                psu.setHealth(property.getValue());
            } else if ("OutputVoltage".equals(property.getName())) {
                psu.setVoltage(Float.parseFloat(property.getValue()));
            } else if ("OutputCurrent".equals(property.getName())) {
                psu.setCurrent(Float.parseFloat(property.getValue()));
            }
        });
        if (targetName.contains("A")) {
            addPsuA(psu);
        } else {
            addPsuB(psu);
        }
    }
}
