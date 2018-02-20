package com.greywanchuang.rackmonitor.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "property")
@Data
public class Property {
    @Id
    @GeneratedValue
    private int id;

    @JoinColumn(name = "targetid", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, optional = true)
    private Target target;

//    @Column
//    private String name;

    @Column
    private String value;

    @Column
    private int timestamp;

    @JoinColumn(name = "config_id", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, optional = true)
    private Config config;


}
