package com.greywanchuang.rackmonitor.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cabinet_group")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CabinetGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String label;

    @Column
    private int position;

    @OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.REMOVE},mappedBy = "cabinetGroup")
    private Set<Cabinet> cabinets=new HashSet<>();


}
