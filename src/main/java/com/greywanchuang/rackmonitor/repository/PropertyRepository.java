package com.greywanchuang.rackmonitor.repository;

import com.greywanchuang.rackmonitor.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property,Integer> {

}
