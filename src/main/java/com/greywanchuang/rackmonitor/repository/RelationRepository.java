package com.greywanchuang.rackmonitor.repository;

import com.greywanchuang.rackmonitor.entity.Relation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RelationRepository extends JpaRepository<Relation,Integer> {

    List<Relation> findByParent(int parentid);
}
