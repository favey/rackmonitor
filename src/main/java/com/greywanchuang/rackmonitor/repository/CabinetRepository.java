package com.greywanchuang.rackmonitor.repository;

import com.greywanchuang.rackmonitor.entity.Cabinet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CabinetRepository extends JpaRepository<Cabinet,Integer> {
    Cabinet findById(int id);

    @Transactional
    void deleteById(int id);

    Cabinet save(Cabinet cabinet);


    List<Cabinet> findAllByCgroupId(int id);
}
