package com.greywanchuang.rackmonitor.repository;

import com.greywanchuang.rackmonitor.entity.Modifycfg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModifycfgRepository extends JpaRepository<Modifycfg,Integer> {
    @Override
    Modifycfg findOne(Integer integer);

    @Override
    List<Modifycfg> findAll();

    Modifycfg save(Modifycfg modifycfg);


}
