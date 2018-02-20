package com.greywanchuang.rackmonitor.repository;

import com.greywanchuang.rackmonitor.entity.CabinetType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CabinetTypeRepository extends JpaRepository<CabinetType, Integer> {
    List<CabinetType> findAll();

    CabinetType save(CabinetType cabinetType);

    void deleteById(int id);

}
