package com.greywanchuang.rackmonitor.repository;

import com.greywanchuang.rackmonitor.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property,Integer> {

//    Property findByTargetidAndNameAndTimestamp(int targetId,String name,int timestamp);


//    List<Property> findAllByNameAndTimestampAndTargetid(String name,int timestamp,int targetId);

//    @Query(" from Property p where p.targetid= :targetid ")
//    List<Property> findPropertiesByTargetid(@Param("targetid")int targetid);

    @Query(value = "select timestamp from property p group by p.timestamp order by p.timestamp DESC limit :count",nativeQuery = true)
    List<Integer> findNewstTimstamp(@Param("count")int count);

    @Query(value = "select timestamp from property where targetid<1314 order by timestamp DESC limit 1",nativeQuery = true)
    Integer findNewestRMCTime();

    @Query(value = "from property where targetid between :startId AND :endId and config_id= :config_id and timestamp= :timastamp",nativeQuery = true)
    List<Property> findCoolsIdAndHealth(@Param("startId")int startId,@Param("endId")int endId,@Param("config_id")int config_id,@Param("timastamp")int timastamp);

//    @Query(" from Property p where p.targetid= :targetid and p.timestamp= :timestamp and name in ('EnTemp','ExTemp')")
//    List<Property> findPowerTemps(@Param("timestamp")int timestamp,@Param("targetid")int targetid);

//    @Query(value = "select count(value) from property where name='Power' and targetid between :startid AND :endid and timestamp = :timestamp",nativeQuery = true)
//    int countSingleComsumptionStatics(@Param("startid")int startid,@Param("endid")int endid,@Param("timestamp")int timestamp);

    //    List<Property> findAllByTimestampAndTargetid(int timestamp,int targetid);
}
