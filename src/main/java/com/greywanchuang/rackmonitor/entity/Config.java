package com.greywanchuang.rackmonitor.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(of = {"id"})
public class Config {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String ip;

    @Column
    private String user;

    @Column
    private String pwd;

    //数据获取方式，RMC(0), IPMI (1)
    @Column
    private String category;

    //待监控设备类型，服务器(1)、机柜(2)
    @Column
    private int deviceType;

    @Column
    private int deviceId;

//    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.ALL}, mappedBy = "config")
//    @org.hibernate.annotations.ForeignKey(name = "none")
//    private Set<Property> properties = new HashSet<>();
}
