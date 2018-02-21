package com.greywanchuang.rackmonitor.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Modifycfg {

    @Id
    @GeneratedValue
    private int id;

    //只允许两种操作：删除地址（0），添加地址（1）
    @Column
    private String operate;

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

    @Column
    private int timestamp;
}