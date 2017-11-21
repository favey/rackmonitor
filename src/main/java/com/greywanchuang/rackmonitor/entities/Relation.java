package com.greywanchuang.rackmonitor.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Relation {

    @Id
    @GeneratedValue
    private  int id;

    @Column
    private int parent;

    @Column
    private  int chiled;

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

    public int getChiled() {
        return chiled;
    }

    public void setChiled(int chiled) {
        this.chiled = chiled;
    }
}
