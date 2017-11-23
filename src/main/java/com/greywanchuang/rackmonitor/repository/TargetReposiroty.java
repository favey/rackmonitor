package com.greywanchuang.rackmonitor.repository;

import com.greywanchuang.rackmonitor.entity.Target;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TargetReposiroty extends JpaRepository<Target,Integer>{
    Target getByName(String name);
    Target getById(int id);

    @Query(value = "SELECT id from target WHERE `name` LIKE '%power%'",nativeQuery = true)
    List<Integer> findPsuTargetIds();

    @Query(value = "SELECT id from target WHERE `name` LIKE '%cooling%'",nativeQuery = true)
    List<Integer> findFanTargetIds();

    @Query(value = "SELECT id from target WHERE `name` LIKE '%chassis1%'",nativeQuery = true)
    List<Integer> findServerTargetIds();
}
