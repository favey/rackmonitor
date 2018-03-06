package com.greywanchuang.rackmonitor.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "property",indexes = {
        @Index(name="targetid_idx",columnList = "targetid",unique = false),
        @Index(name="timestamp_idx",columnList = "timestamp",unique = false),
        @Index(name="config_id_idx",columnList = "config_id",unique = false)})
@Data
public class Property {
    @Id
    @GeneratedValue
    private int id;

//    @JoinColumn(name = "targetid", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, optional = true)
//    private Target target;

    @Column(name = "targetid",nullable = false)
    private int targetid;

    @Column
    private String value;

    @Column(name = "timestamp",nullable = false)
    private int timestamp;


    @Column(name = "config_id",nullable = false)
    private int config_id;
//    @JoinColumn(name = "config_id", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, optional = true)
//    private Config config;


}
