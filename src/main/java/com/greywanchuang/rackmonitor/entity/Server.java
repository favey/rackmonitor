package com.greywanchuang.rackmonitor.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "server")
@EqualsAndHashCode(of = {"id"})
@Data
public class Server {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String label;

    @Column
    private String serialNo;

    @Column
    private String ip;

    @Column
    private String hostname;

    @Column
    private String description;

    @Column
    private int position;

    @Column
    private int height;

    @Column
    private double weight;

    @Column
    private String ipmi;

    @Column
    private String userName;

    @Column
    private String password;

    /**
     * 用于标记删除状态，0-正常，1-删除，但实际上并不删除
     */
    @Column
    private int status=0;

    @JoinColumn(name = "cabinet_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cabinet cabinet;



}
