package com.greywanchuang.rackmonitor.repository;

import com.greywanchuang.rackmonitor.entity.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ServerRepository extends JpaRepository<Server,Integer> {

    Server findById(int id);

    @Transactional
    void removeById(int id);

    Server save(Server server);

}
