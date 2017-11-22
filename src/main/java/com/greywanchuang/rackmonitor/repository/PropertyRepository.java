package com.greywanchuang.rackmonitor.repository;

import com.greywanchuang.rackmonitor.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property,Integer> {

    Property findByTargetidAndNameAndTimestamp(int targetId,String name,int timestamp);

    List<Property> findAllByTimestamp(int timestamp);

    @Query(" from Property p where p.targetid= :targetid ")
    List<Property> findPropertiesByTargetid(@Param("targetid")int targetid);

    @Query(value = "select timestamp from property p group by p.timestamp order by p.timestamp DESC limit 1",nativeQuery = true)
    int findNewstTimstamp();
}
