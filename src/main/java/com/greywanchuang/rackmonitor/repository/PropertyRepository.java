package com.greywanchuang.rackmonitor.repository;

import com.greywanchuang.rackmonitor.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property,Integer> {

    Property findByTargetidAndNameAndTimestamp(int targetId,String name,int timestamp);

    List<Property> findAllByTimestampAndTargetid(int timestamp,int targetid);

    List<Property> findAllByNameAndTimestampAndTargetid(String name,int timestamp,int targetId);

    @Query(" from Property p where p.targetid= :targetid ")
    List<Property> findPropertiesByTargetid(@Param("targetid")int targetid);

    @Query(value = "select timestamp from property p group by p.timestamp order by p.timestamp DESC limit :count",nativeQuery = true)
    List<Integer> findNewstTimstamp(@Param("count")int count);

    @Query(" from Property p where p.targetid= :targetid and p.timestamp= :timestamp and name in ('EnTemp','ExTemp')")
    List<Property> findPowerTemps(@Param("timestamp")int timestamp,@Param("targetid")int targetid);

//    @Query("select count(value) from property where name='Power' and targetid between 1012 AND 1051 and timestamp in (select timestamp from property group by timestamp order by timestamp DESC)")
//    int countTotalFanComsumption();
//
//    @Query("select count(value) from property where name='Power' and targetid between 972 AND 1011 and timestamp in (select timestamp from property group by timestamp order by timestamp DESC)")
//    int countTotalServerComsumption();
//
//    @Query("select count(value) from property where name='Power' and targetid between 1053 AND 1062 and timestamp in (select timestamp from property group by timestamp order by timestamp DESC)")
//    int countTotalPSUComsumption();

    @Query(value = "select count(value) from property where name='Power' and targetid between :startid AND :endid and timestamp = :timestamp",nativeQuery = true)
    int countSingleComsumptionStatics(@Param("startid")int startid,@Param("endid")int endid,@Param("timestamp")int timestamp);
}
