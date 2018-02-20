package com.greywanchuang.rackmonitor.domain;

import com.greywanchuang.rackmonitor.entity.Property;

import java.util.List;

public class PowerComsumption {
    private int input;
    private int output;
    private int effeciency;
    private String timestamp;

    public int getInput() {
        return input;
    }

    public void setInput(int input) {
        this.input = input;
    }

    public int getOutput() {
        return output;
    }

    public void setOutput(int output) {
        this.output = output;
    }

    public int getEffeciency() {
        return effeciency;
    }

    public void setEffeciency(int effeciency) {
        this.effeciency = effeciency;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void compose(List<Property> properties) {
//        properties.forEach(property -> {
//            if ("Input".equals(property.getName())) {
//                this.setInput(Integer.parseInt(property.getValue()));
//            } else if ("Output".equals(property.getName())) {
//                this.setOutput(Integer.parseInt(property.getValue()));
//            } else if ("Efficiency".equals(property.getName())) {
//                this.setEffeciency(Integer.parseInt(property.getValue()));
//            }
//        });
    }
}
