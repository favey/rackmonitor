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
    private double width;

    @Column
    private String ipmi;

    @Column
    private String userName;

    @Column
    private String password;

    @JoinColumn(name = "cabinet_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cabinet cabinet;



}
