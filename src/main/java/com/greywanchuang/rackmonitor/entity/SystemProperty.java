package com.greywanchuang.rackmonitor.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "system_property")
@Data
public class SystemProperty {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private  int id;

    @Column
    private String prop;

    @Column
    private String val;
}
