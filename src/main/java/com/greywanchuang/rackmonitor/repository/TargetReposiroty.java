package com.greywanchuang.rackmonitor.repository;

import com.greywanchuang.rackmonitor.entity.Target;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TargetReposiroty extends JpaRepository<Target,Integer>{
    Target getByName(String name);
}
