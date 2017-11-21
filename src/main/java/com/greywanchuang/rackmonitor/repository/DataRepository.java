package com.greywanchuang.rackmonitor.repository;

import com.greywanchuang.rackmonitor.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<Property,Integer> {

}
