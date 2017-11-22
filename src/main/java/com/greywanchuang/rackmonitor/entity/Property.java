package com.greywanchuang.rackmonitor.entity;

import javax.persistence.*;

@Entity
@Table(name = "property")
public class Property {
    @Id
    @GeneratedValue
    private  int id;

    @Column
    private int targetid;

    @Column
    private String name;

    @Column
    private String value;

    @Column
    private String timestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTargetid() {
        return targetid;
    }

    public void setTargetid(int targetid) {
        this.targetid = targetid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
