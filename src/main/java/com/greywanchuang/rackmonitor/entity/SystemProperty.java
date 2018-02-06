package com.greywanchuang.rackmonitor.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;


@Entity
@Table(name = "system_property")
@Data
public class SystemProperty {
    @Id
    @GeneratedValue
    private  int id;

    @Column
    private String prop;

    @Column
    private String val;
}
