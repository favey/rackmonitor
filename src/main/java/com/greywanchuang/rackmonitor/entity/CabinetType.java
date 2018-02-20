package com.greywanchuang.rackmonitor.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Calendar;

@Entity
@Data
public class CabinetType {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @Column
    private String memo;

    @Column
    private int timestamp= (int) (System.currentTimeMillis()/1000);
}
