package com.greywanchuang.rackmonitor.repository;

import com.greywanchuang.rackmonitor.entity.SystemProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SystemPropertyRepository extends JpaRepository<SystemProperty,Integer> {
    SystemProperty findByProp(String propety);

    SystemProperty save(SystemProperty systemProperty);

    List<SystemProperty> save(List<SystemProperty> systemProperties);

    @Query(value = "select * from system_property where prop like :prop ",nativeQuery = true)
    List<SystemProperty> findAllByPropertyName(@Param("prop") String prop);

    @Modifying
    @Transactional
    @Query(value = "update system_property set val = :val where prop = :prop ",nativeQuery = true)
    void excute(@Param("prop")String property,@Param("val")String value);
}
