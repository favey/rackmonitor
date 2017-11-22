package com.greywanchuang.rackmonitor.repository;

import com.greywanchuang.rackmonitor.entity.Relation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelationRepository extends JpaRepository<Relation,Integer> {
    Relation findByParent(int parentid);
}
