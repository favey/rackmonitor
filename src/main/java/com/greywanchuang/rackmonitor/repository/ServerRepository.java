package com.greywanchuang.rackmonitor.repository;

import com.greywanchuang.rackmonitor.entity.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepository extends JpaRepository<Server,Integer> {

    Server findById(int id);

    void removeById(int id);

    Server save(Server server);


}
