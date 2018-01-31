package com.greywanchuang.rackmonitor.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "server")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
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
    private double wright;

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
