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
    private  String label;


    @Column
    private String typeName; //类型名称

    @Column
    private String modelNumer;

    @Column
    private String serialNumber;

    @Column
    private String smpAddres;

    @Column
    private int space;

    @Column
    private double weight;

    /**
     * 用于标记删除状态，0-正常，1-删除，但实际上并不删除
     */
    @Column
    private int status=0;

    @Column
    private int computedPower;

    @Column
    private String doorSensorIP;

    @Column
    private int cgroupId;

//    @JoinColumn(name = "cgroup_id")
//    @ManyToOne(fetch = FetchType.LAZY)
//    private CabinetGroup cabinetGroup;

//    @OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.REMOVE},mappedBy = "cabinet")
//    private Set<Server> servers=new HashSet<>();


}
