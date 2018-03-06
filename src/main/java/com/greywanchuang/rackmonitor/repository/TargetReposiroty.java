package com.greywanchuang.rackmonitor.repository;

import com.greywanchuang.rackmonitor.entity.Target;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TargetReposiroty extends JpaRepository<Target, Integer> {
    Target getByName(String name);

    Target getById(int id);

    @Query(value = "SELECT id from target WHERE `name` LIKE '%power%'", nativeQuery = true)
    List<Integer> findPsuTargetIds();

    @Query(value = "SELECT id from target WHERE `name` LIKE '%cooling%'", nativeQuery = true)
    List<Integer> findFanTargetIds();

    @Query(value = "SELECT id from target WHERE `name` LIKE '%chassis1%'", nativeQuery = true)
    List<Integer> findServerTargetIds();

    @Query(value = "from target where name REGEXP 'system/cooling[0-9]*/$' ", nativeQuery = true)
    List<Target> findCoolings();

    @Query(value = "from target where id between :startId and :endId ", nativeQuery = true)
    List<Target> findTargetByRange(@Param("startId") int startId, @Param("endId") int endId);
}
