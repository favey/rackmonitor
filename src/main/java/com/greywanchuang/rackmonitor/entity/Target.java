package com.greywanchuang.rackmonitor.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "target")
@Data
@EqualsAndHashCode(of = {"id"})
public class Target {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @Column
    private String type;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.ALL}, mappedBy = "target")
    @org.hibernate.annotations.ForeignKey(name = "none")
    private Set<Target> childrenTargets = new HashSet<>();

    @JoinColumn(name = "parent_id", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, optional = true)
    private Target target;
}
