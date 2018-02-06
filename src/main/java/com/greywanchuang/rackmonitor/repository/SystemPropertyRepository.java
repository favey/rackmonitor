package com.greywanchuang.rackmonitor.repository;

import com.greywanchuang.rackmonitor.entity.SystemProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SystemPropertyRepository extends JpaRepository<SystemProperty,Integer> {
    SystemProperty findByProperty(String propety);

    SystemProperty save(SystemProperty systemProperty);

    void batchUpdate(List<SystemProperty> systemProperties);

    @Query(value = "from system_property where property like 'pue_%' ",nativeQuery = true)
    List<SystemProperty> findAllByPropertyName();

    @Query(value = "update system_property set val = :value where prop = :property ",nativeQuery = true)
    void updateSystem(@Param("value")String value,@Param("property")String property);
}
