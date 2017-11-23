package com.greywanchuang.rackmonitor.entity;

import javax.persistence.*;

@Entity
@Table(name = "relation")
public class Relation {

    @Id
    @GeneratedValue
    private  int id;

    @Column
    private int parent;

    @Column
    private  int child;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getChild() {
        return child;
    }

    public void setChild(int child) {
        this.child = child;
    }
}
