package com.greywanchuang.rackmonitor.repository;

import com.greywanchuang.rackmonitor.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property,Integer> {
    List<Property> findByTargetidAndName(int targetId,String name);

    @Query(" from Property p where p.targetid= :targetid ")
    List<Property> findPropertiesByTargetid(@Param("targetid")int targetid);


}
