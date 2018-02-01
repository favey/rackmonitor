package com.greywanchuang.rackmonitor.repository;

import com.greywanchuang.rackmonitor.entity.CabinetGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CabinetGroupRepository extends JpaRepository<CabinetGroup,Integer> {

    @Transactional
    void deleteById(int id);

    CabinetGroup save(CabinetGroup cabinetGroup);

    CabinetGroup findById(int id);

}
