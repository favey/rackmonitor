package com.greywanchuang.rackmonitor.repository;

import com.greywanchuang.rackmonitor.entity.Cabinet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CabinetRepository extends JpaRepository<Cabinet,Integer> {
    Cabinet findById(int id);

    void deleteById(int id);

    Cabinet save(Cabinet cabinet);

}
