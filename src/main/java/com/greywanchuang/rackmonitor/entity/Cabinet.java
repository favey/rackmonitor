package com.greywanchuang.rackmonitor.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cabinet")
@EqualsAndHashCode(of = {"id"})
@Data
public class Cabinet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String groupName;

    @Column
    private String typeName;

    @Column
    private String modelNumer;

    @Column
    private String serialNumber;

    @Column
    private String smpAddres;

    @Column
    private int space;

    @Column
    private int weight;

    @Column
    private double computedPower;

    @Column
    private String doorSensorIP;

    @JoinColumn(name = "cgroup_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CabinetGroup cabinetGroup;

    @OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.REMOVE},mappedBy = "cabinet")
    private Set<Server> servers=new HashSet<>();


}